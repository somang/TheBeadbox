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
 * @author imdc
 */
public class BeadPlayer extends javax.swing.JPanel {
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
                
        super.paintComponent(g2d);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0, getHeight()/8, getWidth(), getHeight()/8);
        g2d.drawLine(0, getHeight()/8*2, getWidth(), getHeight()/8*2);
        g2d.drawLine(0, getHeight()/8*3, getWidth(), getHeight()/8*3);
        g2d.drawLine(0, getHeight()/8*4, getWidth(), getHeight()/8*4);
        g2d.drawLine(0, getHeight()/8*5, getWidth(), getHeight()/8*5);
        g2d.drawLine(0, getHeight()/8*6, getWidth(), getHeight()/8*6);
        g2d.drawLine(0, getHeight()/8*7, getWidth(), getHeight()/8*7);
        
        repaint ();
    }

    /**
     * Creates new form BeadPlayer
     */
    public BeadPlayer() {
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
