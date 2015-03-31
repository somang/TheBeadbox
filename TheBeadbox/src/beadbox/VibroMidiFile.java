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
        this.sequence = new Sequence(Sequence.PPQ, 16, 8); //16 ticks per quarter note, 8tracks.
        
        for (int i=0;i<8;i++){
            Track curTrack = sequence.getTracks()[i];            
            //setTimeSignature(curTrack);
            setTempo(curTrack);
        }
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

    
    public void setTempo(Track curTrack) throws InvalidMidiDataException{
        MetaMessage mm = new MetaMessage();
            byte[] b = new byte[]{
                (byte)0x07, 
                (byte)0xA1, 
                (byte)0x20
            };            
        mm.setMessage(81, b, 3);
        MidiEvent ev = new MidiEvent(mm,0);
        curTrack.add(ev);
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
   * @param delta Time Measure unit
   * @param track Track number (0-15)
   * @param frequency Pitch number (0-126)
   * @param velocity Intensity (0-100)
   */
    public void noteOn(long delta, int track, int frequency, int intensity) throws InvalidMidiDataException {        
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.NOTE_ON, frequency, intensity, delta));
    }
    
    /**
   * Produces a pitchBend midi Message.
   * Simply to fill in extra frequency to add to previous NoteOn Message
   * 
   * @param delta Time Measure unit
   * @param track Track number (0-15)
   * @param lsb Least Significant Byte [0-126]
   * @param msb Most Significant Byte [0-126]
   */
    public void pitchBend(int track, int lsb, int msb) throws InvalidMidiDataException {
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.PITCH_BEND, lsb, msb, 0));
    }

    
    public void noteOff(int delta, int track, int frequency) throws InvalidMidiDataException {
        sequence.getTracks()[track].add(createNoteEvent(ShortMessage.NOTE_OFF, frequency, 0, delta));
    }
    
    private static MidiEvent createNoteEvent(int nCommand, int frequency, int intensity, long lTick) throws InvalidMidiDataException{
	ShortMessage message = new ShortMessage();        
        message.setMessage(nCommand, CHANNEL, frequency, intensity);
	MidiEvent event = new MidiEvent(message, lTick);
	return event;
    }
    


    public void writeToFile(String filename) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        
        mfw = new MidiFileWriter();
        
        if (mfw!=null){
            mfw.write(sequence, 1, fos); //Sequence in, int fileType (1-multitrack, or 0-single), File out)
        }
        
    }


}
