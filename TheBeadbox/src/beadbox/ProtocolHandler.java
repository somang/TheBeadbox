/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

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
        // get beads from beadPlayer1.beads and create a midi file

        
        MidiFile mf = new MidiFile();
        // Test 1 — play a C major chord

        // Turn on all three notes at start-of-track (delta=0) 
        mf.noteOn (0, 60, 127);
        mf.noteOn (0, 64, 127);
        mf.noteOn (0, 67, 127);

        // Turn off all three notes after one minim. 
        // NOTE delta value is cumulative — only _one_ of
        //  these note-offs has a non-zero delta. The second and
        //  third events are relative to the first
        mf.noteOff (MINIM, 60);
        mf.noteOff (0, 64);
        mf.noteOff (0, 67);

        // Test 2 — play a scale using noteOnOffNow
        //  We don't need any delta values here, so long as one
        //  note comes straight after the previous one 

        mf.noteOnOffNow (QUAVER, 60, 127);
        mf.noteOnOffNow (QUAVER, 62, 127);
        mf.noteOnOffNow (QUAVER, 64, 127);
        mf.noteOnOffNow (QUAVER, 65, 127);
        mf.noteOnOffNow (QUAVER, 67, 127);
        mf.noteOnOffNow (QUAVER, 69, 127);
        mf.noteOnOffNow (QUAVER, 71, 127);
        mf.noteOnOffNow (QUAVER, 72, 127);

        // Test 3 — play a short tune using noteSequenceFixedVelocity
        //  Note the rest inserted with a note value of -1
        
        int[] sequence = new int[]
        {
        60, QUAVER + SEMIQUAVER,
        65, SEMIQUAVER,
        70, CROTCHET + QUAVER,
        69, QUAVER,
        65, QUAVER / 3,
        62, QUAVER / 3,
        67, QUAVER / 3,
        72, MINIM + QUAVER,
        -1, SEMIQUAVER,
        72, SEMIQUAVER,
        76, MINIM,
        };
        
        // What the heck — use a different instrument for a change
        mf.progChange (10);

        mf.noteSequenceFixedVelocity (sequence, 127);

        mf.writeToFile ("test1.mid");
    }
    
}
