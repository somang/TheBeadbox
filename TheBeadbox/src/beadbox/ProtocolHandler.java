/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import static javafx.application.Platform.exit;

/**
 * This class is the VIDI - MIDI mapping protocol conversion. However, it has
 * some restrictions and limitations. It is uniquely set for the Emoti Chair, or
 * the vibrotactile system with 8 track.
 *
 * It takes maximum 9 tracks.
 *
 * @author somang
 */
class ProtocolHandler {

    public final static float LOG2 = 0.6931472f;
    
    public void saveFile(BeadPlayer beadPlayer1, rightJPanel rightJPanel1, String fName) throws Exception {
        /**
         * Initialization
         */
        VibroMidiFile mf = new VibroMidiFile();
        // Initiate the tracks
        for (int i = 0; i < beadPlayer1.tracksize; i++) { // 0-7
            mf.noteOn(0, i, 60, 60);
            mf.noteOff(0, i, 0);
        }
        /**
         * Get beads from beadPlayer1.beads and create a midi file All the beads
         * will be stored in a matrix. VIDI protocol is an application layer on
         * top of the MIDI The information it needs to transmit includes:
         *
         * 1. Coordinates for the Beadlights(voicecoil visualization) 
         * 2. Beadfrequency: Max value 1000 
         * 3. Bead intensity: Max value 100 
         * 4. position(page): Max value infinite 
         * 5. Own Index 
         * 6. connected Bead Index
         *
         */

        /**
         * for Matrix M:
         *
         * col * row
         *
         * where col.size can be upto 0-9998 [9999 beads] and row.size can be
         * upto 0-127 [128 cols]
         */
        if (beadPlayer1.beads.size() < 1269873) {//maximum capacity
            for (int i = 0; i < beadPlayer1.beads.size(); i++) {
                Bead tmp = beadPlayer1.beads.get(i);
                mf = beadInfoParser.parseBead(mf, tmp, false);
            }
        } else {
            System.out.println("Exceed capacity.");
            exit();
        }

        /*Add the maximum page number, and beadlight coordinates in Lyrics Meta message. */
        String p = "" + beadPlayer1.maxPage;
        for (int i = 0; i < rightJPanel1.getComponentCount(); i++) {
            Beadlight tmpBL = (Beadlight) rightJPanel1.getComponents()[i];
            p = p + "," + tmpBL.getX() + "," + tmpBL.getY();
        }
        mf.setTempo(0, beadPlayer1.SPEED);
        mf.addLyrics(0, p);
        mf.writeToFile(fName);
    }


}
