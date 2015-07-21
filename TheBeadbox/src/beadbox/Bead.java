/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.nio.BufferOverflowException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Somang and Albert
 */
public class Bead extends javax.swing.JPanel {

    private int curX, curY, centerX, centerY, curIntensity, maxIntensity, curFrequency;
    protected int track, page, duration;
    final private safeColors safeColor;
    protected boolean playable = false;
    public VibcompUI vibcompUI = null;
    protected Bead connectedTo = null;
    protected int connectPosX = -1, connectPosY = -1;;
    boolean InOutSwitch = false, initial = true;
    Beadlight tmpBeadlight;
    

    @Override
    protected void paintComponent(Graphics g) {
        if (initial) {
            centerX = (getWidth() - maxIntensity) / 2;
            centerY = (getHeight() - maxIntensity) / 2;
            initial = false;
        }
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval(centerX, centerY, maxIntensity, maxIntensity);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX, centerY, maxIntensity, maxIntensity);
        g2d.setColor(safeColor.pickColor(curFrequency, track));
        curX = (getWidth() - curIntensity / 2) / 2;
        curY = (getHeight() - curIntensity / 2) / 2;
        g2d.fillOval(curX, curY, curIntensity / 2, curIntensity / 2);
        if(VibcompUI.activeBead==this || VibcompUI.multiSelect.contains(this)) {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.RED);
            g2d.drawOval(centerX, centerY, maxIntensity, maxIntensity);
        }

        try {
            if (playable && VibcompUI.playing) {
                int barPos = vibcompUI.beadPlayer1.getBarIUPosition();
                int start = getX() + (page * getWidth());
                int end = getX() + (page * getWidth()) + getWidth();
                tmpBeadlight = (Beadlight) vibcompUI.rightJPanel1.getComponent(track - 1);

                if (connectedTo != null) { // If there's connected Bead
                    int mid = (start + connectedTo.getX() + (page * getWidth())) / 2;

                    if ((track != connectedTo.track) && (start < connectedTo.getX() + (page * getWidth()))) { // When track is not the same
                        /*
                         blink til midpoint, then light out.
                         */
                        if (start < (barPos + (page * getWidth())) && mid > (barPos + (page * getWidth()))) {
                            if (InOutSwitch) {
                                tmpBeadlight.setIntensity(40);
                                InOutSwitch = false;
                            }
                        } else {
                            if (!InOutSwitch) {
                                tmpBeadlight.setIntensity(0);
                                InOutSwitch = true;
                            }
                        }
                        tmpBeadlight = (Beadlight) vibcompUI.rightJPanel1.getComponent(connectedTo.track - 1);
                        start = mid;
                        end = connectedTo.getX() + (connectedTo.page * getWidth());
                    } else if (start < connectedTo.getX() + (page * getWidth())) { // when both beads are in same track
                        end = connectedTo.getX() + (connectedTo.page * getWidth());
                    }
                }
                if (start < (barPos + (page * getWidth())) && end > (barPos + (page * getWidth()))) {
                    if (InOutSwitch) {
                        tmpBeadlight.setIntensity(40);
                        InOutSwitch = false;
                    }
                } else {
                    if (!InOutSwitch) {
                        tmpBeadlight.setIntensity(0);
                        InOutSwitch = true;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public void playBead() {
        double playIntensity = curIntensity;//20 * Math.log10(curIntensity);
        //Play note 
        try {
            float[] sampleWave = new float[vibcompUI.listener.getBufferSize()];
            if (vibcompUI.driverLoaded) {
                try {
                    double samplingInterval = (double) (vibcompUI.listener.getSampleRate() / curFrequency);
                    for (int k = 0; k < vibcompUI.driver.getBufferPreferredSize(); k++) {
                        //sampleWave[k] = (float) Math.sin ( curFrequency *k*20.0 / vibcompUI.listener.getSampleRate())*curIntensity/100;                   
                        double angle = (2.0 * Math.PI * k) / samplingInterval;
                        sampleWave[k] = (float) ((float) Math.sin(angle) * playIntensity / 100.0);
                    }
                    vibcompUI.listener.output(track - 1, sampleWave);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bead.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BufferOverflowException e) {
                    //if(sleepTime<5)sleepTime+=2;
                    //System.out.print("\tWarning Buffer overload..Sleep Time increased: "+sleepTime);                     
                }
            }
        } catch (NullPointerException e) {
        }
    }

    /**
     * At specific setup. i.e. for fade in and out play
     *
     * for(int i=0; i<rate; i++){ 
     *      double angle = (i/rate)*Hertz*2.0*Math.PI;
     *      buf[0]=(byte)(Math.sin(angle)*vol); sourceDL.write(buf,0,1);
     *      sines[i]=(double)(Math.sin(angle)*vol); }
     * @param varIntensity
     * @param varFrequency
     */
    public void playBead(int varIntensity, int varFrequency) {
        double playIntensity = varIntensity;
        //playIntensity = 20*Math.log10(60-varIntensity);
        
        try {
            float[] sampleWave = new float[vibcompUI.listener.getBufferSize()];
            if (vibcompUI.driverLoaded) {
                try {
                    double samplingInterval = (double) (vibcompUI.listener.getSampleRate() / varFrequency);                    
                    for (int k = 0; k < vibcompUI.driver.getBufferPreferredSize(); k++) {
                        //sampleWave[k] = (float) Math.sin ( curFrequency *k*20.0 / vibcompUI.listener.getSampleRate())*varIntensity/100;
                        double angle = (2.0 * Math.PI * k) / samplingInterval;
                        sampleWave[k] = (float) ((float) Math.sin(angle) * playIntensity/100);
                    }
                    vibcompUI.listener.output(track - 1, sampleWave);
                    //vibcompUI.listener.setVolume(varIntensity);
                    //System.out.println(playIntensity+","+varFrequency);
                    
                } catch (InterruptedException ex) {
                } catch (BufferOverflowException e) {
                }
            }
        } catch (NullPointerException e) {
        }
    }

    /**
     * Creates new form beadJPanel
     */
    public Bead() {
        safeColor = new safeColors();
        curFrequency = 100;
        track = 0;
        curIntensity = 100;
        maxIntensity = 50;
        page = -1;
        initComponents();
    }

    /* GETTERS */
    public int getIntensity() {
        return curIntensity;
    }

    public int getFrequency() {
        return curFrequency;
    }

    public int getTrack() {
        return track;
    }

    public int getPage() {
        return page;
    }

    public int getDuration() {
        return duration;
    }

    /* SETTERS */
    public void setIntensity(int intensity) {
        curIntensity = intensity;
    }

    public void setMaxIntensity(int intensity) {
        maxIntensity = intensity;
    }

    public void setFrequency(int freq) {
        curFrequency = freq;
    }

    public void setTrack(int tracknumber) {
        track = tracknumber;
    }

    public void setPage(int position) {
        page = position;
    }

    public void setDuration(int givenduration) {
        duration = givenduration;
    }

    public void setConnection(Bead otherBead) {
        connectedTo = otherBead;
        otherBead.connectedTo = this;
    }

    public void breakConnections() {
        if (connectedTo != null) {
            connectedTo.connectedTo = null;
            connectedTo = null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(60, 60));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        System.out.println("Keypressed");
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
