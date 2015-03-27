/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.util.ArrayList;

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
            parseBeadForMidi(tmp);
            
            //System.out.println(tmp);
            //mf.noteOn(0, i, i);
        }
        
        mf.noteOn(0, 7, 60, 126);
        mf.pitchBend(20, 7, 0, 126);
        mf.noteOff (SEMIBREVE, 7, 60);
        mf.writeToFile ("test1.mid");        
        
    }

    private void parseBeadForMidi(Bead b) {
        /* This method takes a bead, and parse it
            So that it converts the data that could fit into Midi Protocol
            
        Precondition:
            A bead is 50 ms = 0.05 sec
            A page can hold 20 beads = 1 sec
            Bead has constant size
            Tracks have constant size.                    
        */
        
        // Track = Channel
        int channel = b.track;
        // Frequency convert.
        int frequency = b.getFrequency();
        int midiPitch = getMidiPitch(frequency);
        int bending = getMidiBending(frequency, midiPitch);
        // Intensity
        int intensity = b.getIntensity();     
        
        
        
        
        int page = b.page;
        
        int xpos = b.getX();
        
        
        
        if (b.connectedTo != null){
            Bead connection = b.connectedTo;
            int cx = connection.getX();
            int cy = connection.getY();
        }
        
        
        
        
        
           
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

    private int getMidiBending(int frequency, int pitch) {
        // Bending Value
        double pitchFreq = 440.0*Math.pow(2,(pitch-69)/12.0);        
        return (int) Math.round(8192+4096*12*log2((double)frequency/pitchFreq));
    }        
    
    private static double log2( double x )
    {
        // Math.log is base e, natural log, ln
        return Math.log( x ) / Math.log( 2 );
    }
    
    
}
