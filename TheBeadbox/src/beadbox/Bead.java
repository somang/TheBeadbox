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

/**
 *
 * @author somang
 */
public class Bead extends javax.swing.JPanel {
    
    private int curX,curY,centerX,centerY,curIntensity, maxIntensity, curFrequency;
    private int track, timeposition, duration;
    private int[] colorCodeReturned;
    private Color beadColor;
    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
                
        super.paintComponent(g2d);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        centerX = (getWidth()-maxIntensity)/2;
        centerY = (getHeight()-maxIntensity)/2;
        
        g2d.drawOval(centerX, centerY, maxIntensity, maxIntensity);
        
        // Here comes the color.
        track = 1;
        safeColors safeColor = new safeColors();
        colorCodeReturned = safeColor.pickColor(curFrequency, track);
        beadColor = new Color(colorCodeReturned[0],colorCodeReturned[1],colorCodeReturned[2]);
        System.out.println(beadColor);
        
        // There will be the array of color blind safe colors, 
        // and it will be picked depending on the track number.
        //color = blindsafeColor; 
        g2d.setColor(beadColor);
        //
        curX = (getWidth()-curIntensity)/2;
        curY = (getHeight()-curIntensity)/2;
        g2d.fillOval(curX, curY, curIntensity, curIntensity);
        
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
    
    public int getTime(){
        return timeposition;
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
    
    public void setTime(int position){
        timeposition = position;
    }
    
    public void setDuration(int givenduration){
        duration = givenduration;
    }
    
    /**
     * Creates new form beadJPanel
     */
    public Bead() {
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
}
