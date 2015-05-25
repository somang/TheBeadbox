/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.io.UnsupportedEncodingException;
import static javafx.application.Platform.exit;
import javax.sound.midi.InvalidMidiDataException;

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
    
    public void saveFile(BeadPlayer beadPlayer1, rightJPanel rightJPanel1) throws Exception {
        /**
         * Initialization*
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
        beadPlayer1.refreshIndex();

        /**
         * for Matrix M:
         *
         * col * row
         *
         * where col.size can be upto 0-9998 [9999 beads] and row.size can be
         * upto 0-127 [128 cols]
         */
        if (beadPlayer1.map.size() < 1269873) {//maximum capacity
            for (int i = 0; i < beadPlayer1.map.size(); i++) {
                Bead tmp = beadPlayer1.map.get(i);
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
        
        mf.addLyrics(0, p);
        mf.writeToFile("testo.mid");
    }

    

    /**
     * page will be a number. it can be 0 - [126 26] This method will parse them
     * into two data variable Tuple. So that it can fit in
     *
     * if page.length less then 3, then data1 =0, data2 = page else if
     * page.length == 3, then data1 takes the hundreds number, rest in data2
     * else if page.length == 4, then data1 takes the thousands, hundres, else
     * if page > 100 00, then data1 is 1 [00-26] data2 is [00-26]
     *
     *
     * @param page
     * @return <data1,data2>
     */
    /*
     private Tuple parsePage(int page) {
     String data1="0";
     String data2= Integer.toString(page);
        
     if (Integer.toString(page).length()>3 && Integer.toString(page).length()<6 ){
     switch(Integer.toString(page).length()){
     case 3: //i.e. page = 345
     data1 = Integer.toString(page).substring(0,1); //3
     data2 = Integer.toString(page).substring(1,3); //45
     break;
     case 4: //i.e. page = 1045
     data1 = Integer.toString(page).substring(0,2); //10
     data2 = Integer.toString(page).substring(2,4); //45
     break;                
     case 5: //i.e. page = 12626
     data1 = Integer.toString(page).substring(0,3); //126
     data2 = Integer.toString(page).substring(3,5); // 26
     if (Integer.parseInt(data1)>126){
     System.out.println("Parsing Error, Data bigger than limitation.");
     }
     if (Integer.parseInt(data2)>26){
     System.out.println("Parsing Error, Data bigger than limitation.");
     }
     break;
     }
     }
        
     int p1 = Integer.parseInt(data1);
     int p2 = Integer.parseInt(data2);
        
     Tuple pageTotal = new Tuple(p1,p2);
     return pageTotal;
     }*/
    
        /*
    private Tuple getMidiBending(int frequency, int pitch) {
        // Bending Value 
         The two bytes of the pitch bend message form a 14 bit number, 
         0 to 16383. The value 8192 (sent, LSB first, as 0x00 0x40), is centered, or "no pitch bend." 
         The value 0 (0x00 0x00) means, "bend as low as possible," and, similarly, 
         16383 (0x7F 0x7F) is to "bend as high as possible."  
         i.e. 1100000  0000000
         // msb      lsb
         
        double pitchFreq = 440.0 * Math.pow(2, (pitch - 69) / 12.0);
        int bendingValue = (int) Math.round(8192 + 4096 * 12 * log2((double) frequency / pitchFreq));

        String binaryBending = Integer.toBinaryString(bendingValue); //0x0000 to 0x3FFF
        int lsb = Integer.parseInt(binaryBending.substring(0, 7), 2);
        int msb = Integer.parseInt(binaryBending.substring(7, 14), 2);

        Tuple p = new Tuple(lsb, msb);

        return p; // because message format data1 = lsb, data2 = msb.
    }
    
    
    private static double log2(double x) {
        // Math.log is base e, natural log, ln
        return Math.log(x) / Math.log(2);
    }

    */

}
