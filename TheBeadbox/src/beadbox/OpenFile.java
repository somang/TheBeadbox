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
    
    public OpenFile(File file, VibcompUI ui) throws MidiUnavailableException, InvalidMidiDataException, IOException{
        
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
        int PITCH_BEND = 0xE0;
        int LYRIC = 5;
        int xLoc = 0, yLoc = 0;
        sequence = MidiSystem.getSequence(file);
        
        String con = "";      
        Bead activeBead =null, tmpBead =null;
        int key=0, velocity;
        // make sure composition is cleared first
        ui.beadPlayer1.beads.clear();
        ui.beadPlayer1.removeAll();
        
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
                            key = sm.getData1()-43;
                            velocity = sm.getData2();
                            if(curTick!=0){
                                System.out.print("@" + event.getTick() + " ");
                                System.out.println("Note on ->   Key:" +key+ " Intensity:"+velocity);                           
                                activeBead = new Bead();  
                                activeBead.vibcompUI = ui;
                                activeBead.setIntensity(velocity);
                                yLoc = (trackNumber-1)*ui.beadPlayer1.TRACKHEIGHT+5;
                                xLoc = (int) curTick%1100;
                            }
                        }
                        // bead frequency info (pitch bend)
                        else if (sm.getCommand() == PITCH_BEND){
                            String data = ""+sm.getData1()+sm.getData2(); 
                            int bendVal = Integer.parseInt(data);
                            key = convertPitchToFreq(key);//+bendVal;
                            activeBead.setFrequency(key);
                            System.out.println("\tFrequency ->  " +key);
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
                            System.out.println("\tNote Index ->  " +activeBead.index);   
                        }
                        // bead connection index info (control change)
                        else if (sm.getCommand() == NOTE_CTRLCNG){
                            String data = ""+sm.getData1()+sm.getData2(); 
                            if(!data.equals("00")){
                                int connectIndex = Integer.parseInt(data);
                                activeBead.connectIndex = connectIndex;
                                System.out.println("\tNote Connected Index ->  " +connectIndex);
                                
                                con+= connectIndex+":"+activeBead.index+"\n";
                            }
                        }
                        //when the key if off
                        else if (sm.getCommand() >= NOTE_OFF_START && sm.getCommand() <= NOTE_OFF_END) {
                            if(curTick!=0){
                                System.out.print("@" + event.getTick() + " ");
                                System.out.println("Note off ->  Bead Created\n");                               
                                activeBead.setSize(55, 55);
                                int tmpIndex = activeBead.index;
                                ui.beadPlayer1.setBead(xLoc, yLoc, activeBead);
                                activeBead.index = tmpIndex;
                                activeBead.page = (int)((curTick)/1100)+1;
                                ui.beadPlayer1.repaint();
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
                            ui.beadPlayer1.maxPage = maxpage;
                            ui.pageScroll.setMaximum(maxpage+1);
                            ui.rightJPanel1.beadlight1.setLocation(Integer.parseInt(splitArray[1]), Integer.parseInt(splitArray[2]));
                            ui.rightJPanel1.beadlight2.setLocation(Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[4]));
                            ui.rightJPanel1.beadlight3.setLocation(Integer.parseInt(splitArray[5]), Integer.parseInt(splitArray[6]));
                            ui.rightJPanel1.beadlight4.setLocation(Integer.parseInt(splitArray[7]), Integer.parseInt(splitArray[8]));
                            ui.rightJPanel1.beadlight5.setLocation(Integer.parseInt(splitArray[9]), Integer.parseInt(splitArray[10]));
                            ui.rightJPanel1.beadlight6.setLocation(Integer.parseInt(splitArray[11]), Integer.parseInt(splitArray[12]));
                            ui.rightJPanel1.beadlight7.setLocation(Integer.parseInt(splitArray[13]), Integer.parseInt(splitArray[14]));
                            ui.rightJPanel1.beadlight8.setLocation(Integer.parseInt(splitArray[15]), Integer.parseInt(splitArray[16]));
                        }
             
                    }
                }
            } 
            
        //set connections
            for (int i = 0; i<ui.beadPlayer1.map.size(); i++){               
                Bead a = ui.beadPlayer1.getBeadAtIndex(i);
                int beadIndex = a.index;
                int connectedIndex = a.connectIndex;
                if(connectedIndex != -1){
                    ui.beadPlayer1.getBeadAtIndex(beadIndex).setConnection(ui.beadPlayer1.getBeadAtIndex(a.connectIndex));
                    System.out.println("Connecting Beads "+i+": "+beadIndex+"&"+connectedIndex);
                }
            }
            
    }
    
    
    private int convertPitchToFreq(int pitchVal){
        int pitchFreq = 0;
        pitchFreq = (int) (440.0f * (float)Math.pow(2.0f, (pitchVal - 69f) / 12.0f));
        return pitchFreq;                
    }
}
