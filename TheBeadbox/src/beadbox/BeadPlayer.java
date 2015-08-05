/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JTextPane;

/**
 *
 * @author Albert@imdc
 */
public class BeadPlayer extends javax.swing.JPanel {
    int tracksize=8; // 8 because this is for the EmotiChair (8 channels,16speakers)
    int TRACKHEIGHT = getHeight() / tracksize;
    int BEADHEIGHT = 50;
    int barPosition = 10, MAXBARPOS = 1100;
    int page = 1;
    static int maxPage = 2;
    ArrayList<Bead> beads = new ArrayList();
    
    HashSet hs;

    VibcompUI vibcompUI = null;
    Thread thread;
    int SPEED = 50;
    boolean inandout = true;
    JTextPane jTP;
    Runnable playerTickTock;

    /**
     * Creates new form BeadPlayer
     */
    public BeadPlayer() {
        
        this.playerTickTock = () -> {
            while (true) {
                if (VibcompUI.playing) {
                    if (barPosition < MAXBARPOS) {
                        barPosition += 50;
                    } else {
                        barPosition = 0;
                        page++;
                        if (page > maxPage) {
                            page = 1;
                        }
                        vibcompUI.pageScroll.setValue(page);
                        jTP.setText("Page: "+page);
                    }
                }
                try {
                    thread.sleep(SPEED);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BeadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        initComponents();
        Thread td = new Thread(playerTickTock);
        td.start();
        
        
        if(VibcompUI.server) ServerCheck.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        TRACKHEIGHT = getHeight() / 8;
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        //create vertical grid lines
        g2d.setColor(new Color(240,240,240));
        g2d.setStroke(new BasicStroke(1));
        for (int i=0; i<20; i++){
            g2d.drawLine(i*55, 0, i*55, getHeight());
        }
        //create horizontal staff lines
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(0, TRACKHEIGHT, getWidth(), TRACKHEIGHT);
        g2d.drawLine(0, TRACKHEIGHT * 2, getWidth(), TRACKHEIGHT * 2);
        g2d.drawLine(0, TRACKHEIGHT * 3, getWidth(), TRACKHEIGHT * 3);
        g2d.drawLine(0, TRACKHEIGHT * 4, getWidth(), TRACKHEIGHT * 4);
        g2d.drawLine(0, TRACKHEIGHT * 5, getWidth(), TRACKHEIGHT * 5);
        g2d.drawLine(0, TRACKHEIGHT * 6, getWidth(), TRACKHEIGHT * 6);
        g2d.drawLine(0, TRACKHEIGHT * 7, getWidth(), TRACKHEIGHT * 7);
        //create vertical moving bar
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(getWidth() * barPosition / MAXBARPOS, 0, getWidth() * barPosition / MAXBARPOS, getHeight());

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        int yOfset = 2;
        
        if (beads.size() > 0) {
            vibcompUI.playerOverview1.clearAll();
        }
        for (Bead curBead : beads) {
            //.show();
            //Draw beads here 
            if (curBead.getPage() == page) {
                curBead.setVisible(true);
                if (curBead.connectedTo != null) {
                    //Draw Connection lines here.
                    if (curBead.connectedTo.page == curBead.page) {
                        g2d.drawLine(curBead.getX() + (BEADHEIGHT / 2), curBead.getY() + yOfset,
                                curBead.connectedTo.getX() + (BEADHEIGHT / 2), curBead.connectedTo.getY() + yOfset);
                        g2d.drawLine(curBead.getX() + (BEADHEIGHT / 2), curBead.getY() + yOfset + BEADHEIGHT,
                                curBead.connectedTo.getX() + (BEADHEIGHT / 2), curBead.connectedTo.getY() + yOfset + BEADHEIGHT);
                    }
                }
            } else {
                curBead.setVisible(false);//.hide();
            }
            vibcompUI.playerOverview1.setBead(curBead.page, curBead.track, true);                       
            /**
             * Below is to play the actual audio output.
             *
             * 1. A single bead gets to play 50 ms. default duration. with given
             * frequency and intensity 2. A connected bead in different track,
             * plays distinctively. (fade out from first track-fade in from
             * second track) 3. A connected bead in the same track, still gets
             * to play distinctively with different intensity&frequency of
             * start-end bead.
             */
            if (VibcompUI.playing) {
                int start = curBead.getX() + (curBead.page * getWidth());
                int end = curBead.getX() + (curBead.page * getWidth()) + curBead.getWidth();

                if (curBead.connectedTo != null) { // if there is a connected Bead
                    int mid = (start + curBead.connectedTo.getX() + (curBead.page * getWidth())) / 2;
                    double gap; //initiate
                    int curIn = curBead.getIntensity();
                    int conIn = curBead.connectedTo.getIntensity();
                    int dif = Math.abs(curIn - conIn);

                    int curfreq = curBead.getFrequency();
                    int confreq = curBead.connectedTo.getFrequency();
                    int diffreq = Math.abs(curfreq - confreq);
                    
                    if ((curBead.connectedTo.track != curBead.track)) {//two beads in dif track
                        /*
                        Then, fade out first bead, and fade in second bead.
                        */
                        if (start < (getBarIUPosition() + (page * getWidth()))
                                && mid > (getBarIUPosition() + (page * getWidth()))) {
                            gap = (-1.0 * ((getBarIUPosition() + page * getWidth()) - mid) / (mid - start));
                            
                            curBead.playBead((int) (gap * curIn), curfreq); //, (int) (gap*curfreq));
                        }
                        start = mid;
                        end = curBead.connectedTo.getX() + (curBead.connectedTo.page * getWidth());
                        if (start < (getBarIUPosition() + (page * getWidth()))
                                && end > (getBarIUPosition() + (page * getWidth()))) {
                            gap = (-1.0 * (mid - (getBarIUPosition() + page * getWidth())) / (end - mid));
                            curBead.connectedTo.playBead((int) (gap * conIn), confreq);//, (int) (gap*confreq));
                        }
                        
                        
                    } else {
                        //when it's in the same track
                        end = curBead.connectedTo.getX() + (curBead.connectedTo.page * getWidth());
                        if (start < (getBarIUPosition() + (page * getWidth()))
                                && end > (getBarIUPosition() + (page * getWidth()))) {
                            gap = (1.0 * (end - (getBarIUPosition() + page * getWidth())) / (end - start));
                            
                            if (curIn > conIn) {
                                dif = (int) (dif * gap + conIn);
                            } else if (curIn < conIn) {
                                dif = (int) (dif * (1 - gap) + curIn);
                            } else { // When both intensities are the same.
                                dif = curIn;
                            }
                            
                            if (curfreq > confreq) {
                                diffreq = (int) (diffreq * gap + confreq);
                            } else if (curfreq < confreq) {
                                diffreq = (int) (diffreq * (1 - gap) + curfreq);
                            } else { // When both intensities are the same.
                                diffreq = curfreq;
                            }
                            
                            curBead.playBead(dif,diffreq);
                        }
                    }
                } else {// When there is no connected Bead, then just play a bead
                    if (start < (getBarIUPosition() + (page * getWidth()))
                            && end > (getBarIUPosition() + (page * getWidth()))) {
                        curBead.playBead();
                    }
                }
            }
        }
        if (!beads.isEmpty()) {
            vibcompUI.playerOverview1.higlightFrag(page);
        }
        repaint();
    }

    public void setBead(int x, int y, Bead bead) {
        x = x - (BEADHEIGHT / 2);
        if (x < 0) {          //keep bead within page bounds         
            x = 0;
        } else if (x > getWidth() - 55) {
            x = getWidth() - 55;
        }
        if (y < 0) {
            y = 0;
        } else if (y > 540) {
            y = 540;
        }
        y = ((getTrackAt(y) - 1) * TRACKHEIGHT + 5);
        bead.setTrack(getTrackAt(y));
        bead.setOpaque(false);
        bead.setLocation(x, y);
        bead.playable = true;
        bead.setPage(page);
        beads.add(bead);
        this.add(bead);
        refreshBeads();
    }

    public void deleteBead(Bead delBead) {
        delBead.breakConnections();
        beads.remove(delBead);
        
        this.remove(delBead);
        refreshBeads();
        if (beads.isEmpty()) {
            vibcompUI.playerOverview1.clearAll();
        }
    }

    public int getTrackAt(int y) {
        return (y / (TRACKHEIGHT) + 1);
    }

    /**
     * Find if there is a Bead around all possible areas of the clicked coordinate.
     * 
     * 
     * @param x
     * @param y
     * @param beadPage
     * @return Bead Object.
     */
    public Bead getBeadAt(int x, int y, int beadPage) {
        try {
            for (Component b : this.getComponents()) {
                if (b.isShowing()) {
                    if(containsLocation(b,x,y)){
                        return (Bead) b;
                    }
                }
            }
        } catch (ClassCastException | NullPointerException e) {
        }
        return null;
    }
    
    private boolean containsLocation(Component b,int x, int y) {
        if ((x >= b.getX()) && (x <= b.getX()+55)){
            if ((y >= b.getY())&&(y <= b.getY()+55)){
                    return true;
            }
        }
        return false;
    }

    public int getBarIUPosition() {
        return getWidth() * barPosition / MAXBARPOS;
    }
    
    /**
     * Suggest use for this method is for the last change, aka before one to save the whole file.
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void refreshBeads() {
        if (beads != null) {
            hs = new HashSet();
            hs.addAll(beads);
            beads = new ArrayList();
            beads.addAll(hs);
        }
    }
    
    Thread ServerCheck = new Thread(){
        public void run(){
            boolean currState = false;
            while(true){
                try {
                    URL url = new URL("http://saduda.com/imdc/uploads/beadboxInfo.txt");
                    Scanner s = new Scanner(url.openStream());
                    s.next();
                    boolean nextState = s.nextBoolean();
                    if(nextState!=currState){ 
                        vibcompUI.playing = nextState;
                        currState = nextState;
                        
                        String url2 = "http://saduda.com/imdc/uploads/beadbox.vidi";
                        downloadUsingStream(url2, "clientFile.vidi");
                        new OpenFile(new File("clientFile.vidi"),vibcompUI,false);
                        s.next();
                        page = s.nextInt();
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(BeadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BeadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MidiUnavailableException ex) {
                    Logger.getLogger(BeadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidMidiDataException ex) {
                    Logger.getLogger(BeadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                       
        }  
        
        private void downloadUsingStream(String urlStr, String file) throws IOException{
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count=0;
            while((count = bis.read(buffer,0,1024)) != -1)
            {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
        }
    };
}
