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
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Albert@imdc
 */
public class BeadPlayer extends javax.swing.JPanel {
    int TRACKHEIGHT = getHeight()/8;
    int BEADHEIGHT = 50;
    int barPosition = 10;
    int tick = 0, speed = 10, page = 1, maxPage = 2;
    ArrayList <Bead> beads = new ArrayList();
    VibcompUI vibcompUI = null;
    @Override
    protected void paintComponent(Graphics g) {
        TRACKHEIGHT = getHeight()/8;
        Graphics2D g2d = (Graphics2D) g;                
        super.paintComponent(g2d);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(0, TRACKHEIGHT, getWidth(), TRACKHEIGHT);
        g2d.drawLine(0, TRACKHEIGHT*2, getWidth(), TRACKHEIGHT*2);
        g2d.drawLine(0, TRACKHEIGHT*3, getWidth(), TRACKHEIGHT*3);
        g2d.drawLine(0, TRACKHEIGHT*4, getWidth(), TRACKHEIGHT*4);
        g2d.drawLine(0, TRACKHEIGHT*5, getWidth(), TRACKHEIGHT*5);
        g2d.drawLine(0, TRACKHEIGHT*6, getWidth(), TRACKHEIGHT*6);
        g2d.drawLine(0, TRACKHEIGHT*7, getWidth(), TRACKHEIGHT*7);
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(getWidth()*barPosition/100, 0, getWidth()*barPosition/100, getHeight());
        
        if(VibcompUI.playing && tick%speed == 0){
            if(barPosition < 100) barPosition++;
            else{ 
                barPosition = 0;
                page++;
                if(page>maxPage){
                    maxPage = page;
                    vibcompUI.pageScroll.setMaximum(page+2);
                }
                vibcompUI.pageScroll.setValue(page);
            }           
        }
        tick++;
        //vibcompUI.rewind.setText("Page: "+page);
        for(int i = 0; i<beads.size(); i++){
            if(beads.get(i).getPage()==page) beads.get(i).show();
            else beads.get(i).hide();
        }
        repaint ();
    }

    /**
     * Creates new form BeadPlayer
     */
    public BeadPlayer() {
        initComponents();  
    }
    
    public void setBead(int x, int y, Bead bead){
        bead.setTrack(getTrackAt(y));
        x = x-(BEADHEIGHT/2);
        y = ((getTrackAt(y)-1)*TRACKHEIGHT+5);
        bead.setOpaque(false);
        bead.setLocation(x, y);
        bead.playable = true;
        bead.setPage(page);
        beads.add(bead);
        this.add(bead);
    }
    
    public void deleteBead(Bead activeBead) {
        this.remove(activeBead);
    }
    
    public int getTrackAt(int y){
        return (y/(TRACKHEIGHT)+1);
    }
    
    public Bead getBeadAt(int x, int y){
        try{
            return (Bead)this.getComponentAt(x, y);
        }
        catch(ClassCastException e){
            return null;
        }
    }
    
    public int getBarIUPosition(){
        return getWidth()*barPosition/100;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

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

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
