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
        MidiFile mf = new MidiFile();
        // get beads from beadPlayer1.beads and create a midi file
        ArrayList <Bead> beadArray = beadPlayer1.beads;
        for (int i=0;i<beadArray.size();i++){
            Bead tmp = beadArray.get(i);
            parseBeadForMidi(tmp);
            
            //System.out.println(tmp);
            //mf.noteOn(0, i, i);
        }
        /*
        // Test 1 — play a C major chord
        mf.noteOn (0, 60, 126);
        mf.pitchBend(20, 0, 126);
        // Turn off all three notes after one minim. 
        // NOTE delta value is cumulative — only _one_ of
        //  these note-offs has a non-zero delta. The second and
        //  third events are relative to the first
        mf.noteOff (SEMIBREVE, 60);
        //mf.noteOff (0, 67);        
        mf.writeToFile ("test1.mid");
        */
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
        int page = b.page;
        int duration = b.duration;
        int xpos = b.getX();
        int ypos = b.getY();
        int track = b.track;
        int frequency = b.getFrequency();
        int intensity = b.getIntensity();
        
        if (b.connectedTo != null){
            Bead connection = b.connectedTo;
            int cx = connection.getX();
            int cy = connection.getY();
        }
        
        // Given that 0 < x < Beadsize*20
        int midiPitch = getMidiPitch(frequency);
        int bending = getMidiBending(frequency, midiPitch);
        
        // To Hex value
        String pitchHex = Integer.toHexString(midiPitch);
        String bendingHex = Integer.toHexString(bending);
        String intensityHex = Integer.toHexString(intensity);
        
        
        System.out.println(frequency);
        System.out.println(intensity);
        System.out.println("Midi Pitch is : " + midiPitch); // The Pitch Value                
        System.out.println("Bending value is : " + bending);
        System.out.println("Note On message would be : " + "t=0 0x90 " + pitchHex + " " + intensityHex);
        System.out.println("Bending message would be : " + "t=0 0xE0 " + " LSB[0-126] " + bendingHex);
        
        
        
        for (frequency=10;frequency>-1;frequency--){
            System.out.println(frequency);
            midiPitch = getMidiPitch(frequency);
            System.out.println(midiPitch+43);
            System.out.println(getMidiBending(frequency, midiPitch));
        }
        
        //Case when 1000, MAX CAP
        midiPitch = getMidiPitch(1000);
        System.out.println(midiPitch+43);
        System.out.println(getMidiBending(1000,midiPitch));
        
        
        
    }
    
    
    private static double log2( double x )
    {
        // Math.log is base e, natural log, ln
        return Math.log( x ) / Math.log( 2 );
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
        
        //if (frequency < 27.5){
            // If frequency is less than 27.5, then there is no mapped pitch value.
            
            
            
        //}else{
            double f = (double)frequency/440.0;        
            pitchMidiVal = (int) Math.round(69+12*log2(f));
            
            
            
            
        //}
        
        return pitchMidiVal;
    }
    


    private int getMidiBending(int frequency, int pitch) {
        // Bending Value
        double pitchFreq = 440.0*Math.pow(2,(pitch-69)/12.0);        
        return (int) Math.round(8192+4096*12*log2((double)frequency/pitchFreq));
    }
        
    
    
}
