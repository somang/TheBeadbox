/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
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
    
    public OpenFile(File file, BeadPlayer bplayer, rightJPanel rpanel, VibcompUI ui) throws MidiUnavailableException, InvalidMidiDataException, IOException{
        //String	strFilename = "testo.mid";
	//file = new File(strFilename);
        
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        long curTick = 0;
        int NOTE_ON_START = 0x90;
        int NOTE_ON_END = 0x9F;
        int NOTE_OFF_START = 0x80;
        int NOTE_OFF_END = 0x8F;
        int NOTE_POLYPRESS = 0xA0;
        int NOTE_PROGCNG = 0xC0;
        int NOTE_CTRLCNG = 0xB0;
        int LYRIC = 5;
        int xLoc = 0, yLoc = 0;
        sequence = MidiSystem.getSequence(file);
        
        Bead activeBead =null, tmpBead =null;
        // make sure composition is cleared first
        bplayer.beads.clear();
        bplayer.removeAll();
        
        //read bead data
            int trackNumber = 0;
            for (Track track :  sequence.getTracks()) {
                trackNumber++;
                System.out.println("\n\nTrack " + trackNumber + "\n----------");//": size = " + track.size());
                System.out.println();
                for (int i=0; i < track.size(); i++) { 
                    MidiEvent event = track.get(i);                   
                    curTick = event.getTick();
                    MidiMessage message = event.getMessage();                    
                    if (message instanceof ShortMessage) {
                        ShortMessage sm = (ShortMessage) message;                       
                        //System.out.print("Channel: " + sm.getChannel() + " ");                       
                        //When a key is on
                        if (sm.getCommand() >= NOTE_ON_START && sm.getCommand() <= NOTE_ON_END) {
                            int key = sm.getData1();
                            int velocity = sm.getData2();
                            if(curTick!=0){
                                System.out.print("@" + event.getTick() + " ");
                                System.out.println("Note on ->   Frequency:" +key+ " Intensity:"+velocity);                           
                                activeBead = new Bead();  
                                activeBead.setIntensity(velocity);
                                yLoc = (trackNumber-1)*bplayer.TRACKHEIGHT+5;
                                xLoc = (int) curTick%1100;
                            }
                        }
                        // bead index info (poly press)
                        else if (sm.getCommand() == NOTE_POLYPRESS){
                            String data = ""+sm.getData1()+sm.getData2(); 
                            activeBead.index = Integer.parseInt(data);                           
                        }
                        // bead index multiplier (program change)
                        else if (sm.getCommand() == NOTE_PROGCNG){
                            String data = ""+sm.getData1();
                            activeBead.index = activeBead.index*Integer.parseInt(data);
                            System.out.print("@" + event.getTick() + " ");
                            System.out.println("Note Index ->  " +activeBead.index);   
                        }
                        // bead connection index info (control change)
                        else if (sm.getCommand() == NOTE_CTRLCNG){
                            String data = ""+sm.getData1()+sm.getData2(); 
                            if(!data.equals("00")){
                                activeBead.connectIndex = Integer.parseInt(data);
                                System.out.print("@" + event.getTick() + " ");
                                System.out.println("Note Connected Index ->  " +activeBead.connectIndex);   
                            }
                        }
                        //when the key if off
                        else if (sm.getCommand() >= NOTE_OFF_START && sm.getCommand() <= NOTE_OFF_END) {
                            //compute coresponding values
                            int key = sm.getData1();
                            int velocity = sm.getData2();
                            if(curTick!=0){
                                System.out.print("@" + event.getTick() + " ");
                                System.out.println("Note off ->  Bead Created\n");                               
                                activeBead.setSize(55, 55);
                                bplayer.setBead(xLoc, yLoc, activeBead);
                                activeBead.page = (int)((curTick-55)/1100)+1;
                                bplayer.repaint();
                            }
                        }                         
                        else {
                            //System.out.println("Command:" + sm.getCommand());
                        }
                    } 
                    // set max page and right panel info
                    else if(trackNumber==1 && message instanceof MetaMessage){
                        MetaMessage mm = (MetaMessage) message;
                        //System.out.println("Other message: " + message.getClass());
                        if (mm.getType() == LYRIC){                          
                            String data = new String(mm.getData());
                            System.out.println("Lyrics: "+data);
                            String[] splitArray = data.split(",");
                            int maxpage = Integer.parseInt(splitArray[0]);
                            bplayer.maxPage = maxpage;
                            ui.pageScroll.setMaximum(maxpage+1);
                            rpanel.beadlight1.setLocation(Integer.parseInt(splitArray[1]), Integer.parseInt(splitArray[2]));
                            rpanel.beadlight2.setLocation(Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[4]));
                            rpanel.beadlight3.setLocation(Integer.parseInt(splitArray[5]), Integer.parseInt(splitArray[6]));
                            rpanel.beadlight4.setLocation(Integer.parseInt(splitArray[7]), Integer.parseInt(splitArray[8]));
                            rpanel.beadlight5.setLocation(Integer.parseInt(splitArray[9]), Integer.parseInt(splitArray[10]));
                            rpanel.beadlight6.setLocation(Integer.parseInt(splitArray[11]), Integer.parseInt(splitArray[12]));
                            rpanel.beadlight7.setLocation(Integer.parseInt(splitArray[13]), Integer.parseInt(splitArray[14]));
                            rpanel.beadlight8.setLocation(Integer.parseInt(splitArray[15]), Integer.parseInt(splitArray[16]));
                        }
             
                    }
                }
            } 
            
        //set connections
            for (int i = 0; i<bplayer.map.size(); i++){               
                Bead a = bplayer.getBeadAtIndex(i);
                a.setConnection(bplayer.getBeadAtIndex(a.connectIndex));
                System.out.println("Connecting Beads "+i+": "+i+"&"+a.connectIndex);
                        
                /*int beadIndex = bplayer.map.get(i).index;
                int connectedIndex = bplayer.getBeadAtIndex(beadIndex).connectIndex;
                
                if(connectedIndex != 0){
                    bplayer.getBeadAtIndex(beadIndex).setConnection(bplayer.getBeadAtIndex(connectedIndex));
                    System.out.println("Connecting Beads "+i+": "+beadIndex+"&"+connectedIndex);
                }
                        */
                bplayer.repaint();
            }
            
    }
}
