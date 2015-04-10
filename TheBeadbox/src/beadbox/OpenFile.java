/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

/**
 *
 * @author imdc
 */
public class OpenFile {
    static Sequence sequence;
    
    public OpenFile(File file, BeadPlayer bplayer) throws MidiUnavailableException, InvalidMidiDataException, IOException{
        //String	strFilename = "testo.mid";
	//file = new File(strFilename);
        
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        long curTick = 0;
        int NOTE_ON_START = 0x90;
        int NOTE_ON_END = 0x9F;
        int NOTE_OFF_START = 0x80;
        int NOTE_OFF_END = 0x8F;
        sequence = MidiSystem.getSequence(file);
        
        int trackNumber = 0;
            for (Track track :  sequence.getTracks()) {
                trackNumber++;
                System.out.println("\n\nTrack " + trackNumber + "\n----------");//": size = " + track.size());
                System.out.println();
                for (int i=0; i < track.size(); i++) { 
                    MidiEvent event = track.get(i);
                    System.out.print("@" + event.getTick() + " ");
                    curTick = event.getTick();
                    MidiMessage message = event.getMessage();
                    if (message instanceof ShortMessage) {
                        ShortMessage sm = (ShortMessage) message;                       
                        System.out.print("Channel: " + sm.getChannel() + " ");
                        //When a key is on
                        if (sm.getCommand() >= NOTE_ON_START && sm.getCommand() <= NOTE_ON_END) {
                            //compute coresponding values
                            int key = sm.getData1();
                            int velocity = sm.getData2();
                            if(curTick!=0){
                                System.out.println("Note on ->  Frequency:" +key+ " Intensity:"+velocity);                           
                                Bead tmpBead = new Bead();
                                int yLoc = (trackNumber-1)*bplayer.TRACKHEIGHT+5;
                                int xLoc = (int) curTick%1000;
                                bplayer.setBead(xLoc, yLoc, tmpBead);
                                tmpBead.page = (int)(curTick/1000)+1;
                            }
                        } 
                        //when the key if off
                        else if (sm.getCommand() >= NOTE_OFF_START && sm.getCommand() <= NOTE_OFF_END) {
                            //compute coresponding values
                            int key = sm.getData1();
                            int velocity = sm.getData2();
                            if(curTick!=0){
                                System.out.println("Note off ->  Frequency:" +key+ " Intensity:"+velocity);
                            }
                        } else {
                            //System.out.println("Command:" + sm.getCommand());
                        }
                    } else {
                        //System.out.println("Other message: " + message.getClass());
                    }
                }
            }     
    }
}
