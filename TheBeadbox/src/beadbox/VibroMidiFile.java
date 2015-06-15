/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.sound.midi.*;

/**
 * 
 * 
 * @author somang
 */
public class VibroMidiFile {
    Sequence sequence=null;
    MidiFileWriter mfw;
    static int CHANNEL = 0;
    
    public VibroMidiFile() throws InvalidMidiDataException{
        this.sequence = new Sequence(Sequence.PPQ, 120, 8); //120 ticks per quarter note, 8 tracks.
        
        for (int i=0;i<8;i++){
            Track curTrack = sequence.getTracks()[i];      
        }
    }
    
    
    /**
   * Sends midi message NoteOn
   * 
   * Generates a vector with four components,
   * Converts the channel to add onto the NoteOn Message.
   * This message will have the Frequency info converted by pitch
   * And will need to add the PitchBend Message info to transfer the
   * full frequency info.
   * 
   * @param lTick Time Measure unit
   * @param track Track number (0-15)
   * @param frequency Pitch number (0-126)
   * @param intensity Intensity (0-100)
   */
    public void noteOn(long lTick, int track, int frequency, int intensity) throws InvalidMidiDataException {        
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.NOTE_ON, frequency, intensity, lTick));
    }
    
    /**
   * Produces a pitchBend midi Message.
   * Simply to fill in extra frequency to add to previous NoteOn Message
   * 
   * @param lTick Time Measure unit
   * @param track Track number (0-15)
   * @param lsb Least Significant Byte [0-126]
   * @param msb Most Significant Byte [0-126]
   */
    public void pitchBend(long lTick, int track, int lsb, int msb) throws InvalidMidiDataException {
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.PITCH_BEND, lsb, msb, lTick));
    }
    
    /**
     * Aftertouch message (0xA0, or 160)
     * This is for displaying connected Bead location. (where this bead's connected bead is at.
     * polyPress and controlChange methods together represents the bead's connected bead's info.
     * Track, position, and so on.
     * 
     * polyPress displays the delta time between two beads.
     * If 0, then the bead has no connected Bead.
     * Else, then the delta time gets parsed into two section data1 and data2
     * Where, data1 has [0-100] data2 [0-100] allows total place holder for [xx xx] 
     * Data1 carrys: Thousands, Hundreds
     * Data2 carrys: Tens, Ones
     * 
     * @param lTick
     * @param track
     * @param data1
     * @param data2
     * @throws javax.sound.midi.InvalidMidiDataException
     */    
    public void polyPress(long lTick, int track, int data1, int data2) throws InvalidMidiDataException {
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.POLY_PRESSURE, data1, data2, lTick));
    }
    
    
    /**
     * This is to store the track information of the connected bead. [0-126]
     * 0xC0
     * @param lTick
     * @param track
     * @param data1
     * @throws InvalidMidiDataException 
     */
    public void progChange(long lTick, int track, int data1) throws InvalidMidiDataException{
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.PROGRAM_CHANGE, data1, 0, lTick));
    }    
    

    public void noteOff(long lTick, int track, int frequency) throws InvalidMidiDataException {
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.NOTE_OFF, frequency, 0, lTick));
    }    
    /*0xB0*/
    public void controlChange(long lTick, int track, int data1, int data2) throws InvalidMidiDataException{        
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.CONTROL_CHANGE, data1, data2, lTick));
    }    
    private static MidiEvent createNoteEvent(int nCommand, int data1, int data2, long lTick) throws InvalidMidiDataException{
	ShortMessage message = new ShortMessage();        
        message.setMessage(nCommand, CHANNEL, data1, data2);
	MidiEvent event = new MidiEvent(message, lTick);
	return event;
    }
    
    
    
    
    
    

    public void setVolume(Track track, int vol) throws InvalidMidiDataException{
        track.add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 7, vol),0)); //7 is the volume control.
    }
    
    public void setTimeSignature(Track curTrack) throws InvalidMidiDataException {
        /**
         * The Default Time signature is 4/4
         * 
        */
        MetaMessage mm = new MetaMessage();
            byte[] b = new byte[]{
                (byte)0x04, // numerator 4
                (byte)0x02, // denominator, the power to the number by 2, so this is 4
                (byte)0x18, // the metronome will click once every 24 MIDI clocks
                (byte)0x08 // there are eight 32nd notes per beat. 
            };            
        mm.setMessage(88, b, 4);
        MidiEvent ev = new MidiEvent(mm,0);
        curTrack.add(ev);
    }
    
    public void setTempo(int curTrack, int tempo) throws InvalidMidiDataException{
        /**
         * The Default Tempo bpm is 120
         */
        
        
        MetaMessage mm = new MetaMessage();
            
            byte[] b = new byte[]{
                (byte)tempo, 
            };            
        mm.setMessage(51, b, 1);
        MidiEvent ev = new MidiEvent(mm,0);
        sequence.getTracks()[curTrack].add(ev);
    }
    
    public void addLyrics(int track, String metacontent) throws InvalidMidiDataException, UnsupportedEncodingException {
        /**
         * addlyrics
         */
        MetaMessage mm = new MetaMessage();
        byte[] b = metacontent.getBytes();
        mm.setMessage(5, b, b.length);
        MidiEvent ev = new MidiEvent(mm,0);
        sequence.getTracks()[track].add(ev);
    
    }


    public void writeToFile(String filename) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        
        
        //MidiDataOutputStream fos = new MidiDataOutputStream(filename);
        
        mfw = new MidiFileWriter();
        
        if (mfw!=null){
            mfw.write(sequence, 1, fos); //Sequence in, int fileType (1-multitrack, or 0-single), File out)
        }
        
    }



}
