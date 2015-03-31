/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.util.ArrayList;
import javax.sound.midi.InvalidMidiDataException;

/**
 *
 * @author somang
 */
class ProtocolHandler {

    static final int SEMIQUAVER = 4;
    static final int QUAVER = 8;
    static final int CROTCHET = 16;
    static final int MINIM = 32;
    static final int SEMIBREVE = 64;
    
    public void saveFile(BeadPlayer beadPlayer1) throws Exception{
        VibroMidiFile mf = new VibroMidiFile();
        
        
        // get beads from beadPlayer1.beads and create a midi file
        ArrayList <Bead> beadArray = beadPlayer1.beads;
        for (int i=0;i<beadArray.size();i++){
            Bead tmp = beadArray.get(i);
            parseBeadForMidi(mf, tmp);
            
            //System.out.println(tmp);
            //mf.noteOn(0, i, i);
        }
        
        mf.writeToFile ("test1.mid");        
        
    }

    private void parseBeadForMidi(VibroMidiFile mf, Bead b) throws InvalidMidiDataException {
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
        System.out.println(track);
        // Frequency convert.
        // Then find the closest Midi pitch value        
        // And the filler for the rest of frequency.
        // frequency = pitchVal + bendingVal
        // i.e. 501 = 71(493.88) + 9207(7.2)
        int frequency = b.getFrequency();
        int pitchVal = getMidiPitch(frequency)+43; // 126-83 to avoid negative from log2
        Tuple bendingVal = getMidiBending(frequency, pitchVal);
        // Intensity
        int intensity = b.getIntensity();     
        
        
        int page = b.page;        
        int xpos = b.getX();
        
        if (b.connectedTo != null){
            Bead connection = b.connectedTo;
            int cx = connection.getX();
            int cy = connection.getY();
        }
        
        
        mf.noteOn(0, track, pitchVal, intensity);
        mf.pitchBend(track, bendingVal.left, bendingVal.right);       
        mf.noteOff (64, track, 0);        
        
        mf.noteOn(2, 2, 65, intensity);
        mf.pitchBend(2, bendingVal.left, bendingVal.right);       
        mf.noteOff (64, 2, 0);
        
        mf.noteOn(4, 3, 70, intensity);
        mf.pitchBend(3, bendingVal.left, bendingVal.right);       
        mf.noteOff (64, 3, 0);
        
        
        
           
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
    
    public class Tuple<Left, Right> { 
        public final int left; 
        public final int right; 
        public Tuple(int left, int right) { 
            this.left = left; 
            this.right = right; 
        } 
    } 
    
}
