/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import static beadbox.ProtocolHandler.LOG2;
import java.io.UnsupportedEncodingException;
import javax.sound.midi.InvalidMidiDataException;

/**
 *
 * @author somang
 */
public class beadInfoParser {
    
    public static VibroMidiFile parseBead(VibroMidiFile mf, Bead b, boolean copy) throws InvalidMidiDataException, UnsupportedEncodingException {
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

        /************
         * July 19th, 2015
         * This is by coordinate.
         * given connectedBead C, the protocol aims to send its x coordinate AND track.
         * 
         */     
        Tuple conBeadXCoord = new Tuple(0, 0);
        Tuple conBeadTrack = new Tuple(0, 0);
        int negativeSign = 0;
        if (b.connectedTo != null){
            long conbeadposition = (long) (1100 * (b.connectedTo.page - 1) + b.connectedTo.getX());        
            long distance_btwn = conbeadposition - position;
            if (distance_btwn < 0){ // when B->A 
                negativeSign = 1;
            }
            conBeadXCoord = parseMessageData((int) distance_btwn);
            System.out.println(conBeadXCoord.left + "," + conBeadXCoord.right);
            conBeadTrack = parseMessageData(b.connectedTo.track-1);
        }        
        mf.noteOn(position, track, pitchVal, intensity); // 0x90, frequency track intensity
        mf.pitchBend(position, track, bendingVal.left, bendingVal.right); //0xE0 filler for the rest of frequency given from bead.
        mf.polyPress(position, track, conBeadXCoord.left, conBeadXCoord.right); // Connected Bead's X position
        mf.progChange(position, track, negativeSign);
        mf.controlChange(position, track, conBeadTrack.left, conBeadTrack.right); // Connected Bead's Y position
        mf.noteOff(position + (long) 55, track, pitchVal); // 0x80
        
        /********************************************************************************************************************************
         * 
         * 
         * Parse Index number of this Bead
         */
        /*
        int row = 1;        
        if ((b.index%9999 == 0) && (b.index!=0)){
            row+=1;
        }
        Tuple indexVal = parseMessageData(b.index);  
        
        /*Connected Bead Information fetch.
        int cbrow = 1;
        Tuple cntBeadIndex = new Tuple(0, 0);
        if (b.connectedTo != null) {
            if ((b.index%9999 == 0) && (b.index!=0)){
                cbrow+=1;
            }
            cntBeadIndex = parseMessageData(b.connectedTo.index);
        }
        
        if (copy){
            position = (long) (b.getX());
            mf.noteOn(position, track, pitchVal, intensity); // 0x90, frequency track intensity
            mf.pitchBend(position, track, bendingVal.left, bendingVal.right); //0xE0 filler for the rest of frequency given from bead.
            mf.polyPress(position, track, indexVal.left, indexVal.right); // Bead Index
            mf.progChange(position, track, row);
            mf.controlChange(position, track, cntBeadIndex.left, cntBeadIndex.right);// Connected Bead Index
            mf.progChange(position, track, cbrow);
            mf.noteOff(position + (long) 55, track, pitchVal); // 0x80

        }else {
            mf.noteOn(position, track, pitchVal, intensity); // 0x90, frequency track intensity
            mf.pitchBend(position, track, bendingVal.left, bendingVal.right); //0xE0 filler for the rest of frequency given from bead.
            mf.polyPress(position, track, indexVal.left, indexVal.right); // Bead Index
            mf.progChange(position, track, row);
            mf.controlChange(position, track, cntBeadIndex.left, cntBeadIndex.right);// Connected Bead Index
            mf.progChange(position, track, cbrow);
            mf.noteOff(position + (long) 55, track, pitchVal); // 0x80
        }
        *****************************************************************************************************************************/
                
                
        return mf;
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
    private static Tuple parseMessageData(int number) {
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

    private static int getMidiPitch(int frequency) {
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
    
    private static int convertPitchToFreq(int pitchVal){
        int pitchFreq = 0;
        pitchFreq = (int) (440.0f * (float)Math.pow(2.0f, (pitchVal - 69f) / 12.0f));
        return pitchFreq;                
    }
    
    public static class Tuple<Left, Right> {

        public final int left;
        public final int right;

        public Tuple(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
