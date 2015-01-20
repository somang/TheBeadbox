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
    
    private int curX,curY,centerX,centerY,curIntensity, maxIntensity, curFrequency;
    private int track, page, duration;
    private int[] colorCodeReturned;
    private Color beadColor;
    private safeColors safeColor ;	
    protected boolean playable = false;
    public VibcompUI vibcompUI = null;
    int sleepTime = 2;
    protected Bead connectedTo = null;

    boolean inandout = false;
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        centerX = (getWidth()-maxIntensity)/2;
        centerY = (getHeight()-maxIntensity)/2;
        g2d.drawOval(centerX, centerY, maxIntensity, maxIntensity);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX, centerY, maxIntensity, maxIntensity);
        colorCodeReturned = safeColor.pickColor(curFrequency, track);
        beadColor = new Color(colorCodeReturned[0],colorCodeReturned[1],colorCodeReturned[2]);g2d.setColor(beadColor);
        g2d.setColor(beadColor);
        curX = (getWidth()-curIntensity)/2;
        curY = (getHeight()-curIntensity)/2;
        g2d.fillOval(curX, curY, curIntensity, curIntensity);
        
        
        try{   
            if(playable && VibcompUI.playing){
                //if (vibcompUI.rewind.isSelected()) setLocation(getX()+1, getY());
                //else setLocation(getX()-1, getY());
                int barPos = vibcompUI.beadPlayer1.getBarIUPosition()*vibcompUI.beadPlayer1.page;
                
                //System.out.println(barPos);
                
                if(this.getLocation().x < barPos && this.getLocation().x+getWidth() > barPos // If bar is in a bead, 
                        || (connectedTo!=null && this.getLocation().x+getWidth()*page < barPos 
                        && connectedTo.getLocation().x*connectedTo.page>barPos)){ // If bar is in a bead connection, 
                    Beadlight tmpBeadlight = (Beadlight) vibcompUI.rightJPanel1.getComponent(track-1);
                    playBead();
                    if (inandout){
                        //System.out.println("playing a note at "+track);
                        tmpBeadlight.setIntensity(40);                      
                        inandout = false;
                    }
                }else{
                    if (!inandout){
                        Beadlight tmpBeadlight = (Beadlight) vibcompUI.rightJPanel1.getComponent(track-1);
                        //System.out.println("notplaying "+track);
                        tmpBeadlight.setIntensity(0);
                        inandout = true;
                    }
                    /*Bug 1, It refreshes to a original position when clicked play button. (while playing)*/
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
                    
        }
            
        repaint();
    }
    
    /* GETTERS */
    public int getIntensity(){
        return curIntensity;
    }
    
    public int getFrequency(){
        return curFrequency;
    }
    
    public int getTrack(){
        return track;
    }
    
    public int getPage(){
        return page;
    }
    
    public int getDuration(){
        return duration;
    }
    
    /* SETTERS */
    public void setIntensity(int intensity){
        curIntensity = intensity;
    }
    
    public void setMaxIntensity(int intensity){
        maxIntensity = intensity;
    }
    
    public void setFrequency(int freq){
        curFrequency = freq;
    }
    
    public void setTrack(int tracknumber){
        track = tracknumber;
    }
    
    public void setPage(int position){
        page = position;
    }
    
    public void setDuration(int givenduration){
        duration = givenduration;
    }
    
    public void setConnection(Bead otherBead){
        connectedTo = otherBead;
        otherBead.connectedTo=this;
    }
    
    public void breakConnections(){
        if(connectedTo != null){
            connectedTo.connectedTo = null;
            connectedTo = null;
        }
    }
    
    public void playBead(){
        //TODO: Play note 
        try{
            float[] sampleWave = new float[vibcompUI.listener.getBufferSize()];

            if(vibcompUI.driverLoaded){           
                try {
                    for ( int k = 0; k < vibcompUI.driver.getBufferPreferredSize(); k++ ) {
                        sampleWave[k] = (float) Math.sin ( curFrequency *k*20.0 / vibcompUI.listener.getSampleRate())*curIntensity/100;                   
                    }                          
                    vibcompUI.listener.output ( track-1, sampleWave );
                    
                    //Thread.sleep(sleepTime);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bead.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (BufferOverflowException e) {
                    //if(sleepTime<5)sleepTime+=2;
                    //System.out.print("\tWarning Buffer overload..Sleep Time increased: "+sleepTime);                     
                }
            }
        }catch(NullPointerException e){
            //System.out.println("Yo, This is from Bead.playBead() exception.");
        }
        
    }
    
    /**
     * Creates new form beadJPanel
     */
    public Bead() {
        safeColor = new safeColors();
        curFrequency = 1;
        track = 0;
        curIntensity = 50;
        maxIntensity = 50;
        page = -1;
        initComponents();
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
