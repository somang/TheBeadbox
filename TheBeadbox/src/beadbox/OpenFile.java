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

    public OpenFile(File file, VibcompUI ui, boolean paste) throws MidiUnavailableException, InvalidMidiDataException, IOException {

        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        long curTick = 0;
        int NOTE_ON_START = 0x90;
        int NOTE_ON_END = 0x9F;
        int NOTE_OFF_START = 0x80;
        int NOTE_OFF_END = 0x8F;
        int NOTE_POLYPRESS = 0xA0;
        int NOTE_CTRLCNG = 0xB0;
        int NOTE_PROGCNG = 0xC0;
        int PITCH_BEND = 0xE0;
        int TEMPO = 51;
        int LYRIC = 5;
        int xLoc = 0, yLoc = 0;
        int BEADHEIGHT = 55;
        sequence = MidiSystem.getSequence(file);

        Bead activeBead = null;
        int key = 0, velocity;
        int actualValue = 0;
        int negSign = 1;

        //boolean conBead = false;
        if (!paste) {
            // make sure composition is cleared first
            ui.beadPlayer1.beads.clear();
            ui.beadPlayer1.removeAll();
        }

        //read bead data
        int trackNumber = 0;
        for (Track track : sequence.getTracks()) {
            trackNumber++;
            System.out.println("\n\nTrack " + trackNumber + "\n----------");//": size = " + track.size());
            System.out.println();
            for (int i = 0; i < track.size(); i++) {
                MidiEvent event = track.get(i);
                curTick = event.getTick();
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                        //System.out.print("Channel: " + sm.getChannel() + " ");                       
                    //When a key is on
                    if (sm.getCommand() >= NOTE_ON_START && sm.getCommand() <= NOTE_ON_END) {
                        key = sm.getData1();
                        velocity = sm.getData2();
                        if (curTick != 0) {
                            System.out.print("@" + event.getTick() + " ");
                            System.out.println("Note on ->   Key:" + key + " Intensity:" + velocity);
                            activeBead = new Bead();
                            activeBead.vibcompUI = ui;
                            activeBead.setIntensity(velocity);
                            yLoc = (trackNumber - 1) * ui.beadPlayer1.TRACKHEIGHT + 5;
                            xLoc = (int) curTick % 1100 + (BEADHEIGHT / 2);
                            if (paste) {
                                xLoc = (int) curTick % 1100 + (25);
                            }
                        }
                    } // bead frequency info (pitch bend)
                    else if (sm.getCommand() == PITCH_BEND) {
                        String data = "" + sm.getData1() + sm.getData2();
                        int bendVal = Integer.parseInt(data);
                        key = convertPitchToFreq(key) + bendVal;
                        activeBead.setFrequency(key);
                        System.out.println("\tFrequency ->  " + key);
                    } // bead index info (poly press)
                    else if (sm.getCommand() == NOTE_POLYPRESS) {
                        String data = "" + sm.getData1() + sm.getData2();
                        System.out.println("\tConnectedTo Gap: " + data);
                        actualValue = Integer.parseInt(data);                        
                    } // connected bead distance delta sign (program change)
                    else if (sm.getCommand() == NOTE_PROGCNG) {
                        String data = "" + sm.getData1();
                        if (Integer.parseInt(data) == 1){
                            negSign = -1;
                        }else{
                            negSign = 1;
                        }
                        int realConBXPos = (actualValue*negSign) + xLoc;
                        System.out.println("\tConnectedTo Xpos: " + realConBXPos);
                        activeBead.connectPosX = realConBXPos;
                    }
                    // bead connection index info (control change)
                    else if (sm.getCommand() == NOTE_CTRLCNG) {
                        String data = "" + sm.getData1() + sm.getData2();
                        System.out.println(" \tYpos:" + data);
                        if (!data.equals("00")) {
                            activeBead.connectPosY = Integer.parseInt(data);
                        }
                    } //when the bead is off
                    else if (sm.getCommand() >= NOTE_OFF_START && sm.getCommand() <= NOTE_OFF_END) {
                        if (curTick != 0) {
                            System.out.print("@" + event.getTick() + " ");
                            System.out.println("Note off ->  Bead Created\n");
                            activeBead.setSize(BEADHEIGHT, BEADHEIGHT);
                            ui.beadPlayer1.setBead(xLoc, yLoc, activeBead);
                            activeBead.page = (int) ((curTick) / 1100) + 1;
                            if (paste) {
                                activeBead.page = ui.beadPlayer1.page;
                            }
                            ui.beadPlayer1.repaint();
                        }
                    } else {
                        //System.out.println("Command:" + sm.getCommand());
                    }
                } // set max page and right panel info
                else if (trackNumber == 1 && message instanceof MetaMessage) {
                    MetaMessage mm = (MetaMessage) message;
                    //System.out.println("Other message: " + message.getClass());
                    if (mm.getType() == LYRIC) {
                        String data = new String(mm.getData());
                        System.out.println("Lyrics: " + data);
                        String[] splitArray = data.split(",");
                        int maxpage = Integer.parseInt(splitArray[0]);
                        ui.beadPlayer1.maxPage = maxpage;
                        ui.pageScroll.setMaximum(maxpage + 1);
                        ui.rightJPanel1.beadlight1.setLocation(Integer.parseInt(splitArray[1]), Integer.parseInt(splitArray[2]));
                        ui.rightJPanel1.beadlight2.setLocation(Integer.parseInt(splitArray[3]), Integer.parseInt(splitArray[4]));
                        ui.rightJPanel1.beadlight3.setLocation(Integer.parseInt(splitArray[5]), Integer.parseInt(splitArray[6]));
                        ui.rightJPanel1.beadlight4.setLocation(Integer.parseInt(splitArray[7]), Integer.parseInt(splitArray[8]));
                        ui.rightJPanel1.beadlight5.setLocation(Integer.parseInt(splitArray[9]), Integer.parseInt(splitArray[10]));
                        ui.rightJPanel1.beadlight6.setLocation(Integer.parseInt(splitArray[11]), Integer.parseInt(splitArray[12]));
                        ui.rightJPanel1.beadlight7.setLocation(Integer.parseInt(splitArray[13]), Integer.parseInt(splitArray[14]));
                        ui.rightJPanel1.beadlight8.setLocation(Integer.parseInt(splitArray[15]), Integer.parseInt(splitArray[16]));
                    } else if (mm.getType() == TEMPO) {
                        int speed = mm.getData()[0];
                        System.out.println("Tempo: " + speed);
                        ui.beadPlayer1.SPEED = speed;
                        ui.speedControl.setValue(100 - speed);
                    }
                }
            }
        }

        // Set connections
        for (Bead b : ui.beadPlayer1.beads) {
            if (b.connectPosY != -1 && b.connectedTo == null) {
                System.out.println("bead connection");
                int xPos = b.connectPosX % 1100;
                int yPos = b.connectPosY * ui.beadPlayer1.TRACKHEIGHT;
                int page = (b.connectPosX / 1100) + 1;
                b.connectedTo = ui.beadPlayer1.getBeadAt(xPos, yPos+40, page);
                
            }
        }

    }

    private int convertPitchToFreq(int pitchVal) {
        int pitchFreq = 0;
        pitchFreq = (int) (440.0f * (float) Math.pow(2.0f, (pitchVal - 69f) / 12.0f));
        return pitchFreq;
    }
}
