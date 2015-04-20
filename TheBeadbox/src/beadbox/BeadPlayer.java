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
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    HashSet hs;
    
    VibcompUI vibcompUI = null;
    Thread thread;
    int SPEED = 50;
    boolean inandout = true;
    
    Runnable playerTickTock;
    
    /**
     * Creates new form BeadPlayer
     */
    public BeadPlayer() {
        this.playerTickTock = new Runnable(){
            public void run(){
                while(true){
                    if(VibcompUI.playing){                        
                        if(barPosition < MAXBARPOS) barPosition+=50;
                        else{                            
                            barPosition = 0;
                            page++;
                            if(page>maxPage){
                                page=1;
                            }                            
                            vibcompUI.pageScroll.setValue(page); 
                        }
                    }
                    try {
                        thread.sleep(SPEED);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BeadPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        initComponents(); 
        Thread thread = new Thread(playerTickTock);
        thread.start();
    }
    
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
        
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        int yOfset=2;
        if(beads.size()>0) vibcompUI.playerOverview1.clearAll();
        for(int i = 0; i<beads.size(); i++){
            Bead curBead = beads.get(i);
            //.show();
            //Draw beads here 
            if(curBead.getPage()==page){ 
                curBead.setVisible(true);
                if(curBead.connectedTo != null){    
                    //Draw Connection lines here.
                    if(curBead.connectedTo.page == curBead.page){
                        g2d.drawLine(curBead.getX()+(BEADHEIGHT/2), curBead.getY()+yOfset, 
                                curBead.connectedTo.getX()+(BEADHEIGHT/2), curBead.connectedTo.getY()+yOfset);
                        g2d.drawLine(curBead.getX()+(BEADHEIGHT/2), curBead.getY()+yOfset+BEADHEIGHT, 
                                curBead.connectedTo.getX()+(BEADHEIGHT/2), curBead.connectedTo.getY()+yOfset+BEADHEIGHT);
                    }
                    else if(curBead.connectedTo.page < curBead.page){
                        g2d.drawLine(curBead.getX()+(BEADHEIGHT/2), curBead.getY()+yOfset, 
                                0, ((curBead.connectedTo.getY()+yOfset)+(curBead.getY()+yOfset))/2);
                        g2d.drawLine(curBead.getX()+(BEADHEIGHT/2), curBead.getY()+yOfset+BEADHEIGHT, 
                                0, ((curBead.connectedTo.getY()+yOfset)+(curBead.getY()+yOfset))/2+BEADHEIGHT);
                    }
                    else{
                        g2d.drawLine(getWidth(), ((curBead.connectedTo.getY()+yOfset)+(curBead.getY()+yOfset))/2, 
                                curBead.getX()+(BEADHEIGHT/2), curBead.getY()+yOfset);
                        g2d.drawLine(getWidth(), ((curBead.connectedTo.getY()+yOfset)+(curBead.getY()+yOfset))/2+BEADHEIGHT, 
                                curBead.getX()+(BEADHEIGHT/2), curBead.getY()+yOfset+BEADHEIGHT);
                    }
                }
            }
            else curBead.setVisible(false);//.hide();
            
            vibcompUI.playerOverview1.setBead(curBead.page, curBead.track, true);
            
            if(VibcompUI.playing){
                int start = curBead.getX()+(curBead.page*getWidth());
                int end = curBead.getX()+(curBead.page*getWidth())+curBead.getWidth();
                
                if (curBead.connectedTo!=null){ // if there is a connected Bead
                    int mid = (start+curBead.connectedTo.getX()+(curBead.page*getWidth()))/2;
                    
                    if ((curBead.connectedTo.track != curBead.track)){// in dif track
                        /*
                        Then, fade out first bead, and fade in second bead.
                        */
                        
                        if (start < ( getBarIUPosition() + (page*getWidth())) && 
                          mid > ( getBarIUPosition() + (page*getWidth()))){
                            float gap = (float) (-1.0*((getBarIUPosition()+page*getWidth())-mid)/(mid-start)) ;     
                            //System.out.println(gap*curBead.getIntensity());
                            curBead.playBead((int) (gap*curBead.getIntensity()));
                        }
                        
                        curBead = curBead.connectedTo; //Change what to play after mid point.
                        start = mid;
                        end = curBead.connectedTo.getX()+(curBead.connectedTo.page*getWidth());
                        
                        if (start < ( getBarIUPosition() + (page*getWidth())) && 
                          end > ( getBarIUPosition() + (page*getWidth()))){
                            float gap = (float) (-1.0*(mid-(getBarIUPosition()+page*getWidth()))/(end-mid)) ;
                            //System.out.println(gap*curBead.getIntensity());
                            curBead.playBead((int) (gap*curBead.getIntensity()));
                        }
                    }else{
                        //when it's in the same track
                        end = curBead.connectedTo.getX()+(curBead.connectedTo.page*getWidth());
                        if (start < ( getBarIUPosition() + (page*getWidth())) && 
                              end > ( getBarIUPosition() + (page*getWidth()))){
                            curBead.playBead();
                        }
                    }
                }else{// When there is no connected Bead, then just play a bead
                    if (start < ( getBarIUPosition() + (page*getWidth())) && 
                        end > ( getBarIUPosition() + (page*getWidth()))){
                        curBead.playBead();
                    }
                }
            }
          
        }
        if(beads.size()>0) vibcompUI.playerOverview1.higlightFrag(page);
        repaint ();
    }
    

    public void setBead(int x, int y, Bead bead){ 
        x = x-(BEADHEIGHT/2);        
        if (x<0){          //keep bead within page bounds         
            x = 0;
        }else if (x>getWidth()-55){
            x = getWidth()-55;
        }
        y = ((getTrackAt(y)-1)*TRACKHEIGHT+5);
        bead.setTrack(getTrackAt(y));
        bead.setOpaque(false);
        bead.setLocation(x, y);
        bead.playable = true;
        bead.setPage(page);
        beads.add(bead);
        this.add(bead);    

    }
    
    
    
    public void deleteBead(Bead activeBead) {
        activeBead.breakConnections();
        beads.remove(activeBead);
        this.remove(activeBead);        
    }
    
    public int getTrackAt(int y){
        return (y/(TRACKHEIGHT)+1);
    }
    
    public Bead getBeadAt(int x, int y, int beadPage){
        try{
            Bead tmp = (Bead)this.getComponentAt(x, y);
            if (tmp.getPage() == beadPage){
                return tmp;
            }else{
                return null;
            }
            
        }
        catch(ClassCastException e){
            return null;
        }catch (NullPointerException ex){
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
