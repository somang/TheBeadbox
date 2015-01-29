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

/**
 *
 * @author Albert@imdc
 */
public class BeadPlayer extends javax.swing.JPanel {
    int TRACKHEIGHT = getHeight()/8;
    int BEADHEIGHT = 50;
    int barPosition = 10, MAXBARPOS = 1000;
    int page = 1;
    static int maxPage = 2;
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
        g2d.drawLine(getWidth()*barPosition/MAXBARPOS, 0, getWidth()*barPosition/MAXBARPOS, getHeight());
        
        
        if(VibcompUI.playing && System.currentTimeMillis()%50<=20){//tick%speed == 0){
            if(barPosition < MAXBARPOS) barPosition+=50;
            else{ 
                barPosition = 0;
                page++;
                if(page>maxPage){
                    page=1;
                    //maxPage = page;
                    //vibcompUI.pageScroll.setMaximum(page+1);
                }
                vibcompUI.pageScroll.setValue(page);               
            }           
        }
        //vibcompUI.rewind.setText("Page: "+page);
        
        
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        int yOfset=2;
        vibcompUI.playerOverview1.clearAll();
        for(int i = 0; i<beads.size(); i++){
            //.show();
            //Draw beads here 
            if(beads.get(i).getPage()==page){ 
                beads.get(i).setVisible(true);
                if(beads.get(i).connectedTo != null){    
                    //Draw Connection lines here.
                    if(beads.get(i).connectedTo.page == beads.get(i).page){
                        g2d.drawLine(beads.get(i).getX()+(BEADHEIGHT/2), beads.get(i).getY()+yOfset, 
                                beads.get(i).connectedTo.getX()+(BEADHEIGHT/2), beads.get(i).connectedTo.getY()+yOfset);
                        g2d.drawLine(beads.get(i).getX()+(BEADHEIGHT/2), beads.get(i).getY()+yOfset+BEADHEIGHT, 
                                beads.get(i).connectedTo.getX()+(BEADHEIGHT/2), beads.get(i).connectedTo.getY()+yOfset+BEADHEIGHT);
                    }
                    else if(beads.get(i).connectedTo.page < beads.get(i).page){
                        g2d.drawLine(beads.get(i).getX()+(BEADHEIGHT/2), beads.get(i).getY()+yOfset, 
                                0, ((beads.get(i).connectedTo.getY()+yOfset)+(beads.get(i).getY()+yOfset))/2);
                        g2d.drawLine(beads.get(i).getX()+(BEADHEIGHT/2), beads.get(i).getY()+yOfset+BEADHEIGHT, 
                                0, ((beads.get(i).connectedTo.getY()+yOfset)+(beads.get(i).getY()+yOfset))/2+BEADHEIGHT);
                    }
                    else{
                        g2d.drawLine(getWidth(), ((beads.get(i).connectedTo.getY()+yOfset)+(beads.get(i).getY()+yOfset))/2, 
                                beads.get(i).getX()+(BEADHEIGHT/2), beads.get(i).getY()+yOfset);
                        g2d.drawLine(getWidth(), ((beads.get(i).connectedTo.getY()+yOfset)+(beads.get(i).getY()+yOfset))/2+BEADHEIGHT, 
                                beads.get(i).getX()+(BEADHEIGHT/2), beads.get(i).getY()+yOfset+BEADHEIGHT);
                    }
                }
            }
            else beads.get(i).setVisible(false);//.hide();
            
            vibcompUI.playerOverview1.setBead(beads.get(i).page, beads.get(i).track, true);
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
        activeBead.breakConnections();
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
        return getWidth()*barPosition/MAXBARPOS;
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
