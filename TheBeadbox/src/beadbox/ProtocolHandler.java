/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.sound.midi.InvalidMidiDataException;

/**
 * This class is the VIDI - MIDI mapping protocol conversion.
 * However, it has some restrictions and limitations.
 * It is uniquely set for the Emoti Chair, or the vibrotactile system with 8 track.
 
 * It takes maximum 9 tracks.
 * 
 * @author somang
 */
class ProtocolHandler {

    
    public void saveFile(BeadPlayer beadPlayer1, rightJPanel rightJPanel1) throws Exception{
        VibroMidiFile mf = new VibroMidiFile();
        
        /**Initialization**/
        for (int i=0;i<8;i++){
            mf.noteOn(0, i, 60, 60); 
            mf.noteOff (0, i, 0); 
        }
        
        // get beads from beadPlayer1.beads and create a midi file
        ArrayList <Bead> beadArray = beadPlayer1.beads;
        
        for (int i=0;i<beadArray.size();i++){
            Bead tmp = beadArray.get(i);
            parseBeadForMidi(mf, tmp);
        }
        
        String p = "{maxpage:"+beadPlayer1.maxPage;
        
        for (int i=0;i<rightJPanel1.getComponentCount();i++){
            Beadlight tmpBL = (Beadlight) rightJPanel1.getComponents()[i];
            p = p+", BL"+(i+1)+":["+tmpBL.getX()+","+tmpBL.getY()+"]";
        }
        p = p+"}";
        //System.out.println(p);
        
        //mf.addLyrics(0, 0, p);        
        mf.writeToFile ("testo.mid");        
        
        
        
    }

    private void parseBeadForMidi(VibroMidiFile mf, Bead b) throws InvalidMidiDataException, UnsupportedEncodingException {
        /* This method takes a bead, and parse it
            So that it converts the data that could fit into Midi Protocol
            
        Precondition:
            A bead is 50 ms = 0.05 sec
            A page can hold 20 beads = 1 sec
            Bead has constant size
            Tracks have constant size.                    
        */
        
        // Track
        int track = b.track-1;
        
        // Frequency convert.
        // Then find the closest Midi pitch value + the filler for the rest.
        // frequency = pitchVal + bendingVal
        // i.e. 501 = 71(493.88Hz) + 9207(7.2Hz)
        int frequency = b.getFrequency();
        int pitchVal = getMidiPitch(frequency)+43; // 126-83 to avoid negative from log2
        Tuple bendingVal = getMidiBending(frequency, pitchVal);
        // Intensity
        int intensity = b.getIntensity();
        // Time position, Delta time (the time difference), the x position.
        // Assumed that a single page handles 20 beads = 1100 pix
        long position = (long) 1100*(b.page-1) + (long) b.getX();
        
        
        
        
        Tuple cntBead = new Tuple(0,0);
        Tuple cbpage = new Tuple(0,0);
        
        if (b.connectedTo != null){
            Bead connection = b.connectedTo;
            cntBead = cnctBeads(connection);
            
            if (Integer.toString(connection.page).length() < 6){
                cbpage = parsePage(connection.page);
            }else{
                System.out.println("Too big for the page parse.");
            }
        }
        
        mf.noteOn(position, track, pitchVal, intensity); // 0x90, frequency track intensity
        mf.pitchBend(position, track, bendingVal.left, bendingVal.right); //0xE0 filler for the rest of frequency given from bead.
        mf.polyPress(position, track, cntBead.left, cntBead.right); // 0xA0, connect bead location.
        mf.controlChange(position, track, cbpage.right, cbpage.left); // Notice that I switched data1 and data2, cause of the order restriction
        mf.noteOff (position+(long) 55, track, pitchVal); // 0x80
        
        
           
    }
    
    /**
     * This is to map the position coordinates for the connected Bead.
     * Assume,
     * xpos: 1100 and track: 8
     * Then we send, 
     * 
     * data1: 110
     * data2: [0,1]08
     * 
     * @param connectionBead
     * @return Tuple<data1, data2>
     */
    private Tuple cnctBeads(Bead connection){
        int cx = connection.getX();
        int cnctBtrack = connection.track;           
        String cxStr = Integer.toString(cx);
        String data1, data2;

        switch (cxStr.length()){                
            case 3: // 345
                data1 = Integer.toString(cx).substring(0,2); //34
                data2 = Integer.toString(cx).substring(2,3); //5
                break;
            case 4: // 1045
                data1 = Integer.toString(cx).substring(0,3); //104
                data2 = Integer.toString(cx).substring(3,4); //5
                break;
            default:
                data1 = "0";
                data2 = Integer.toString(cx);
                break;
        }
        data2 = data2+cnctBtrack;
        cx = Integer.parseInt(data1);
        Tuple cBeads = new Tuple(cx,cnctBtrack);
        return cBeads;
    }
    
    
    private int getMidiPitch(int frequency) {
        // pitch value from frequency given.
        /* 
        In normal Keyboard -> A0(21) is the lowest, B7(107) is the highest.
        Middle C, C4 = 60 and 440 Hz gives 69
        The quantity log2 (ƒ / 440 Hz) is the number of octaves above the 440-Hz concert A 
        (it is negative if the frequency is below that pitch). 
        Multiplying it by 12 gives the number of semitones above that frequency. 
        Adding 69 gives the number of semitones above the C five octaves below middle C.
        */  
        
        int pitchMidiVal=0;
        double f = (double)frequency/440.0;        
        pitchMidiVal = (int) Math.round(69+12*log2(f));
        
        return pitchMidiVal;
    }    

    private Tuple getMidiBending(int frequency, int pitch) {
        // Bending Value 
        /* The two bytes of the pitch bend message form a 14 bit number, 
           0 to 16383. The value 8192 (sent, LSB first, as 0x00 0x40), is centered, or "no pitch bend." 
           The value 0 (0x00 0x00) means, "bend as low as possible," and, similarly, 
           16383 (0x7F 0x7F) is to "bend as high as possible."  
           i.e. 1100000  0000000
                // msb      lsb
        */
        double pitchFreq = 440.0*Math.pow(2,(pitch-69)/12.0);             
        int bendingValue = (int) Math.round(8192+4096*12*log2((double)frequency/pitchFreq)); 
        
        String binaryBending = Integer.toBinaryString(bendingValue); //0x0000 to 0x3FFF
        int lsb = Integer.parseInt(binaryBending.substring(0, 7), 2);
        int msb = Integer.parseInt(binaryBending.substring(7,14), 2); 
        
        Tuple p = new Tuple(lsb,msb);
        
        return p; // because message format data1 = lsb, data2 = msb.
    }        
    
    private static double log2( double x )
    {
        // Math.log is base e, natural log, ln
        return Math.log( x ) / Math.log( 2 );
    }

    /**
     * page will be a number.
     * it can be 0 - [126 26]
     * This method will parse them into two data variable Tuple.
     * So that it can fit in 
     * 
     * if page.length less then 3, then data1 =0, data2 = page
     * else if page.length == 3, then data1 takes the hundreds number, rest in data2
     * else if page.length == 4, then data1 takes the thousands, hundres, 
     * else if page > 100 00, then data1 is 1 [00-26] data2 is [00-26]
     * 
     * 
     * @param page
     * @return <data1,data2>
     */
    private Tuple parsePage(int page) {
        String data1="0";
        String data2= Integer.toString(page);
        
        if (Integer.toString(page).length()>3 && Integer.toString(page).length()<6 ){
            switch(Integer.toString(page).length()){
                case 3: //i.e. page = 345
                    data1 = Integer.toString(page).substring(0,1); //3
                    data2 = Integer.toString(page).substring(1,3); //45
                    break;
                case 4: //i.e. page = 1045
                    data1 = Integer.toString(page).substring(0,2); //10
                    data2 = Integer.toString(page).substring(2,4); //45
                    break;                
                case 5: //i.e. page = 12626
                    data1 = Integer.toString(page).substring(0,3); //126
                    data2 = Integer.toString(page).substring(3,5); // 26
                    if (Integer.parseInt(data1)>126){
                       System.out.println("Parsing Error, Data bigger than limitation.");
                    }
                    if (Integer.parseInt(data2)>26){
                       System.out.println("Parsing Error, Data bigger than limitation.");
                    }
                    break;
            }
        }
        
        int p1 = Integer.parseInt(data1);
        int p2 = Integer.parseInt(data2);
        
        Tuple pageTotal = new Tuple(p1,p2);
        return pageTotal;
    }

    
    public class Tuple<Left, Right> { 
        public final int left; 
        public final int right; 
        public Tuple(int left, int right) { 
            this.left = left; 
            this.right = right; 
        } 
    } 
    
}
