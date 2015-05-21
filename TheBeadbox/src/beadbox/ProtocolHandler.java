/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.io.UnsupportedEncodingException;
import static javafx.application.Platform.exit;
import javax.sound.midi.InvalidMidiDataException;

/**
 * This class is the VIDI - MIDI mapping protocol conversion. However, it has
 * some restrictions and limitations. It is uniquely set for the Emoti Chair, or
 * the vibrotactile system with 8 track.
 *
 * It takes maximum 9 tracks.
 *
 * @author somang
 */
class ProtocolHandler {

    public final static float LOG2 = 0.6931472f;
    
    public void saveFile(BeadPlayer beadPlayer1, rightJPanel rightJPanel1) throws Exception {
        /**
         * Initialization*
         */
        VibroMidiFile mf = new VibroMidiFile();
        // Initiate the tracks
        for (int i = 0; i < beadPlayer1.tracksize; i++) { // 0-7
            mf.noteOn(0, i, 60, 60);
            mf.noteOff(0, i, 0);
        }
        /**
         * Get beads from beadPlayer1.beads and create a midi file All the beads
         * will be stored in a matrix. VIDI protocol is an application layer on
         * top of the MIDI The information it needs to transmit includes:
         *
         * 1. Coordinates for the Beadlights(voicecoil visualization) 
         * 2. Beadfrequency: Max value 1000 
         * 3. Bead intensity: Max value 100 
         * 4. position(page): Max value infinite 
         * 5. Own Index 
         * 6. connected Bead Index
         *
         */
        beadPlayer1.refreshIndex();

        /**
         * for Matrix M:
         *
         * col * row
         *
         * where col.size can be upto 0-9998 [9999 beads] and row.size can be
         * upto 0-127 [128 cols]
         */
        if (beadPlayer1.map.size() < 1269873) {//maximum capacity
            for (int i = 0; i < beadPlayer1.map.size(); i++) {
                Bead tmp = beadPlayer1.map.get(i);
                parseBead(mf, tmp);
            }
        } else {
            System.out.println("Exceed capacity.");
            exit();
        }

        /*Add the maximum page number, and beadlight coordinates in Lyrics Meta message. */
        String p = "" + beadPlayer1.maxPage;
        for (int i = 0; i < rightJPanel1.getComponentCount(); i++) {
            Beadlight tmpBL = (Beadlight) rightJPanel1.getComponents()[i];
            p = p + "," + tmpBL.getX() + "," + tmpBL.getY();
        }
        
        mf.addLyrics(0, p);
        mf.writeToFile("testo.mid");
    }

    private void parseBead(VibroMidiFile mf, Bead b) throws InvalidMidiDataException, UnsupportedEncodingException {
        /* This method takes a bead, and parse it
         So that it converts the data that could fit into Midi Protocol
            
         Precondition:
         A bead is 50 ms = 0.05 sec
         A page can hold 20 beads = 1 sec
         Bead has constant size
         Tracks have constant size.                    
         */

        // Track
        int track = b.track - 1;

        // Frequency convert.
        // Then find the closest Midi pitch value + the filler for the rest.
        // frequency = pitchVal + bendingVal
        // i.e. 501 = 71(493.88Hz) + 9207(7.2Hz)
        int frequency = b.getFrequency();
        int pitchVal = getMidiPitch(frequency); // 126-83 to avoid negative from log2
        Tuple bendingVal = parseMessageData(frequency-convertPitchToFreq(pitchVal)); // pass in the difference to fill in with the bending value;
        // Intensity
        int intensity = b.getIntensity();
        // Time position, Delta time in MIDI (the time difference), the x position.
        // Assumed that a single page handles 20 beads = 1100 pix
        long position = (long) (1100 * (b.page - 1) + b.getX());

        /*Parse Index number of this Bead*/
        int row = 1;
        Tuple indexVal;
        indexVal = new Tuple(0,0);
        if ((b.index%9999 == 0) && (b.index!=0)){
            row+=1;
        }
        indexVal = parseMessageData(b.index);
        
        /*Connected Bead Information fetch.*/
        int cbrow = 1;
        Tuple cntBeadIndex = new Tuple(0, 0);
        if (b.connectedTo != null) {
            if ((b.index%9999 == 0) && (b.index!=0)){
                cbrow+=1;
            }
            cntBeadIndex = parseMessageData(b.connectedTo.index);
        }

        mf.noteOn(position, track, pitchVal, intensity); // 0x90, frequency track intensity
        mf.pitchBend(position, track, bendingVal.left, bendingVal.right); //0xE0 filler for the rest of frequency given from bead.
        mf.polyPress(position, track, indexVal.left, indexVal.right); // Bead Index
        mf.progChange(position, track, row);
        mf.controlChange(position, track, cntBeadIndex.left, cntBeadIndex.right);// Connected Bead Index
        mf.progChange(position, track, cbrow);
        mf.noteOff(position + (long) 55, track, pitchVal); // 0x80
    }

    /**
     * This is to map the value for the connected Bead into two data
     * tuple. Given a number NNNN with condition of maximum four digit integers,
     *
     * if NNNN.length < 3; then data1 = 0 and data2 = NN/N
     * if NNNN.length == 3; then data1 = N, data2 = NN 
     * if NNNN.length == 4; then data1 = NN, data2 = NN
     *
     * Given 4 digits Integer, then 
     * data1 takes 1000s and 1000s,
     * data2 takes 10s and 1s.
     *
     * @param 
     * @return Tuple<data1, data2>
     */
    private Tuple parseMessageData(int number) {
        int cx = (int) Math.abs(number);
        int cy = 0;
        String cxStr = Integer.toString(cx);
        String data1, data2;
        switch (cxStr.length()) {
            case 3: // 345
                data1 = Integer.toString(cx).substring(0, 2); //3
                data2 = Integer.toString(cx).substring(2, 3); //45
                break;
            case 4: // 1045
                data1 = Integer.toString(cx).substring(0, 2); //10
                data2 = Integer.toString(cx).substring(2, 4); //45
                break;
            default:
                data1 = "0";
                data2 = Integer.toString(cx);
                break;
        }
        cx = Integer.parseInt(data1);
        cy = Integer.parseInt(data2);
        Tuple dataTuple = new Tuple(cx, cy);
        return dataTuple;
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
        int pitchMidiVal = (int) Math.max(0f, (float)Math.log(frequency / 440.0f) / LOG2 * 12f + 69f);
        return pitchMidiVal;
    }
    
    private int convertPitchToFreq(int pitchVal){
        int pitchFreq = 0;
        pitchFreq = (int) (440.0f * (float)Math.pow(2.0f, (pitchVal - 69f) / 12.0f));
        return pitchFreq;                
    }
    
    public class Tuple<Left, Right> {

        public final int left;
        public final int right;

        public Tuple(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * page will be a number. it can be 0 - [126 26] This method will parse them
     * into two data variable Tuple. So that it can fit in
     *
     * if page.length less then 3, then data1 =0, data2 = page else if
     * page.length == 3, then data1 takes the hundreds number, rest in data2
     * else if page.length == 4, then data1 takes the thousands, hundres, else
     * if page > 100 00, then data1 is 1 [00-26] data2 is [00-26]
     *
     *
     * @param page
     * @return <data1,data2>
     */
    /*
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
     }*/
    
        /*
    private Tuple getMidiBending(int frequency, int pitch) {
        // Bending Value 
         The two bytes of the pitch bend message form a 14 bit number, 
         0 to 16383. The value 8192 (sent, LSB first, as 0x00 0x40), is centered, or "no pitch bend." 
         The value 0 (0x00 0x00) means, "bend as low as possible," and, similarly, 
         16383 (0x7F 0x7F) is to "bend as high as possible."  
         i.e. 1100000  0000000
         // msb      lsb
         
        double pitchFreq = 440.0 * Math.pow(2, (pitch - 69) / 12.0);
        int bendingValue = (int) Math.round(8192 + 4096 * 12 * log2((double) frequency / pitchFreq));

        String binaryBending = Integer.toBinaryString(bendingValue); //0x0000 to 0x3FFF
        int lsb = Integer.parseInt(binaryBending.substring(0, 7), 2);
        int msb = Integer.parseInt(binaryBending.substring(7, 14), 2);

        Tuple p = new Tuple(lsb, msb);

        return p; // because message format data1 = lsb, data2 = msb.
    }
    
    
    private static double log2(double x) {
        // Math.log is base e, natural log, ln
        return Math.log(x) / Math.log(2);
    }

    */

}
