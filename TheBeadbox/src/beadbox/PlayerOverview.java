/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import static beadbox.BeadPlayer.maxPage;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author albert & somang
 */
public final class PlayerOverview extends javax.swing.JPanel {

    /**
     * Creates new form PlayerOverview
     */  
    int prevPage = 1;
    Color defaultColor;
    ArrayList <PlayerOverviewFragment> frags = new ArrayList();
    PlayerOverviewFragment tempFrag;
    VibcompUI vui;
    
    public void refreshOverviewArray(){
        // Save all the frags.
        
    }

    public void setBead(int page, int track, boolean active){
        if (page > 15){
            int dpPage = page%15;
            frags.get(dpPage-1).setBeadBars(track, active);   
            frags.get(dpPage-1).repaint();
        }else{
            frags.get(page-1).setBeadBars(track, active);   
            frags.get(page-1).repaint();
        }
    }
    public void clearAll(){
        for (int i=0; i<frags.size(); i++){
            frags.get(i).clear();
            frags.get(i).repaint();
        }
    }    
    public void higlightFrag(int page){
        if (page > 15){
            page = page%15;
            if (page == 0){
                page = 15;
            }
        }
        frags.get(prevPage-1).setBackground(defaultColor); // turn off
        frags.get(page-1).setBackground(Color.WHITE); //highlight        
        prevPage = page;
    }
    
    public void createFrags(){        
        frags.add(playerOverviewFragment1);
        frags.add(playerOverviewFragment2);
        frags.add(playerOverviewFragment3);
        frags.add(playerOverviewFragment4);
        frags.add(playerOverviewFragment5);
        frags.add(playerOverviewFragment6);
        frags.add(playerOverviewFragment7);
        frags.add(playerOverviewFragment8);
        frags.add(playerOverviewFragment9);
        frags.add(playerOverviewFragment10);
        frags.add(playerOverviewFragment11);
        frags.add(playerOverviewFragment12);
        frags.add(playerOverviewFragment13);
        frags.add(playerOverviewFragment14);
        frags.add(playerOverviewFragment15);
    }
    
    /**
     * Creates new form BeadPlayerOverview
     */
    public PlayerOverview() {
        initComponents();
        createFrags(); 
        defaultColor = frags.get(0).getBackground();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerOverviewFragment1 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment2 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment3 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment4 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment5 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment6 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment7 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment8 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment9 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment10 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment11 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment12 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment13 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment14 = new beadbox.PlayerOverviewFragment();
        playerOverviewFragment15 = new beadbox.PlayerOverviewFragment();

        setBackground(new java.awt.Color(250, 250, 250));
        setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        playerOverviewFragment1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment1Layout = new javax.swing.GroupLayout(playerOverviewFragment1);
        playerOverviewFragment1.setLayout(playerOverviewFragment1Layout);
        playerOverviewFragment1Layout.setHorizontalGroup(
            playerOverviewFragment1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment1Layout.setVerticalGroup(
            playerOverviewFragment1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment2Layout = new javax.swing.GroupLayout(playerOverviewFragment2);
        playerOverviewFragment2.setLayout(playerOverviewFragment2Layout);
        playerOverviewFragment2Layout.setHorizontalGroup(
            playerOverviewFragment2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment2Layout.setVerticalGroup(
            playerOverviewFragment2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment3Layout = new javax.swing.GroupLayout(playerOverviewFragment3);
        playerOverviewFragment3.setLayout(playerOverviewFragment3Layout);
        playerOverviewFragment3Layout.setHorizontalGroup(
            playerOverviewFragment3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment3Layout.setVerticalGroup(
            playerOverviewFragment3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment4Layout = new javax.swing.GroupLayout(playerOverviewFragment4);
        playerOverviewFragment4.setLayout(playerOverviewFragment4Layout);
        playerOverviewFragment4Layout.setHorizontalGroup(
            playerOverviewFragment4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment4Layout.setVerticalGroup(
            playerOverviewFragment4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment5Layout = new javax.swing.GroupLayout(playerOverviewFragment5);
        playerOverviewFragment5.setLayout(playerOverviewFragment5Layout);
        playerOverviewFragment5Layout.setHorizontalGroup(
            playerOverviewFragment5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment5Layout.setVerticalGroup(
            playerOverviewFragment5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment6Layout = new javax.swing.GroupLayout(playerOverviewFragment6);
        playerOverviewFragment6.setLayout(playerOverviewFragment6Layout);
        playerOverviewFragment6Layout.setHorizontalGroup(
            playerOverviewFragment6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment6Layout.setVerticalGroup(
            playerOverviewFragment6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment7Layout = new javax.swing.GroupLayout(playerOverviewFragment7);
        playerOverviewFragment7.setLayout(playerOverviewFragment7Layout);
        playerOverviewFragment7Layout.setHorizontalGroup(
            playerOverviewFragment7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment7Layout.setVerticalGroup(
            playerOverviewFragment7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment8Layout = new javax.swing.GroupLayout(playerOverviewFragment8);
        playerOverviewFragment8.setLayout(playerOverviewFragment8Layout);
        playerOverviewFragment8Layout.setHorizontalGroup(
            playerOverviewFragment8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment8Layout.setVerticalGroup(
            playerOverviewFragment8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment9Layout = new javax.swing.GroupLayout(playerOverviewFragment9);
        playerOverviewFragment9.setLayout(playerOverviewFragment9Layout);
        playerOverviewFragment9Layout.setHorizontalGroup(
            playerOverviewFragment9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment9Layout.setVerticalGroup(
            playerOverviewFragment9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment10MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment10Layout = new javax.swing.GroupLayout(playerOverviewFragment10);
        playerOverviewFragment10.setLayout(playerOverviewFragment10Layout);
        playerOverviewFragment10Layout.setHorizontalGroup(
            playerOverviewFragment10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment10Layout.setVerticalGroup(
            playerOverviewFragment10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment11MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment11Layout = new javax.swing.GroupLayout(playerOverviewFragment11);
        playerOverviewFragment11.setLayout(playerOverviewFragment11Layout);
        playerOverviewFragment11Layout.setHorizontalGroup(
            playerOverviewFragment11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment11Layout.setVerticalGroup(
            playerOverviewFragment11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment12MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment12Layout = new javax.swing.GroupLayout(playerOverviewFragment12);
        playerOverviewFragment12.setLayout(playerOverviewFragment12Layout);
        playerOverviewFragment12Layout.setHorizontalGroup(
            playerOverviewFragment12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment12Layout.setVerticalGroup(
            playerOverviewFragment12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment13MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment13Layout = new javax.swing.GroupLayout(playerOverviewFragment13);
        playerOverviewFragment13.setLayout(playerOverviewFragment13Layout);
        playerOverviewFragment13Layout.setHorizontalGroup(
            playerOverviewFragment13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment13Layout.setVerticalGroup(
            playerOverviewFragment13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment14MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment14Layout = new javax.swing.GroupLayout(playerOverviewFragment14);
        playerOverviewFragment14.setLayout(playerOverviewFragment14Layout);
        playerOverviewFragment14Layout.setHorizontalGroup(
            playerOverviewFragment14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment14Layout.setVerticalGroup(
            playerOverviewFragment14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        playerOverviewFragment15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerOverviewFragment15MousePressed(evt);
            }
        });

        javax.swing.GroupLayout playerOverviewFragment15Layout = new javax.swing.GroupLayout(playerOverviewFragment15);
        playerOverviewFragment15.setLayout(playerOverviewFragment15Layout);
        playerOverviewFragment15Layout.setHorizontalGroup(
            playerOverviewFragment15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        playerOverviewFragment15Layout.setVerticalGroup(
            playerOverviewFragment15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerOverviewFragment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerOverviewFragment15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playerOverviewFragment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(playerOverviewFragment15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playerOverviewFragment1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment1MousePressed
        vui.beadPlayer1.page=1;
        vui.pageScroll.setValue(1);
    }//GEN-LAST:event_playerOverviewFragment1MousePressed

    private void playerOverviewFragment2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment2MousePressed
        if(maxPage > 1){
            vui.beadPlayer1.page=2;
            vui.pageScroll.setValue(2);
        }
    }//GEN-LAST:event_playerOverviewFragment2MousePressed

    private void playerOverviewFragment3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment3MousePressed
        if(maxPage > 2){
            vui.beadPlayer1.page=3;
            vui.pageScroll.setValue(3);
        }
    }//GEN-LAST:event_playerOverviewFragment3MousePressed

    private void playerOverviewFragment4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment4MousePressed
        if(maxPage > 3){
            vui.beadPlayer1.page=4;
            vui.pageScroll.setValue(4);
        }
    }//GEN-LAST:event_playerOverviewFragment4MousePressed

    private void playerOverviewFragment5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment5MousePressed
        if(maxPage > 4){
            vui.beadPlayer1.page=5;
            vui.pageScroll.setValue(5);
        }
    }//GEN-LAST:event_playerOverviewFragment5MousePressed

    private void playerOverviewFragment6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment6MousePressed
        if(maxPage > 5){
            vui.beadPlayer1.page=6;
            vui.pageScroll.setValue(6);
        }
    }//GEN-LAST:event_playerOverviewFragment6MousePressed

    private void playerOverviewFragment7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment7MousePressed
        if(maxPage > 6){
            vui.beadPlayer1.page=7;
            vui.pageScroll.setValue(7);
        }
    }//GEN-LAST:event_playerOverviewFragment7MousePressed

    private void playerOverviewFragment8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment8MousePressed
        if(maxPage > 7){
            vui.beadPlayer1.page=8;
            vui.pageScroll.setValue(8);
        }
    }//GEN-LAST:event_playerOverviewFragment8MousePressed

    private void playerOverviewFragment9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment9MousePressed
        if(maxPage > 8){
            vui.beadPlayer1.page=9;
            vui.pageScroll.setValue(9);
        }
    }//GEN-LAST:event_playerOverviewFragment9MousePressed

    private void playerOverviewFragment10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment10MousePressed
        if(maxPage > 9){
            vui.beadPlayer1.page=10;
            vui.pageScroll.setValue(10);
        }
    }//GEN-LAST:event_playerOverviewFragment10MousePressed

    private void playerOverviewFragment11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment11MousePressed
        if(maxPage > 10){
            vui.beadPlayer1.page=11;
            vui.pageScroll.setValue(11);
        }
    }//GEN-LAST:event_playerOverviewFragment11MousePressed

    private void playerOverviewFragment12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment12MousePressed
        if(maxPage > 11){
            vui.beadPlayer1.page=12;
            vui.pageScroll.setValue(12);
        }
    }//GEN-LAST:event_playerOverviewFragment12MousePressed

    private void playerOverviewFragment13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment13MousePressed
        if(maxPage > 12){
            vui.beadPlayer1.page=13;
            vui.pageScroll.setValue(13);
        }
    }//GEN-LAST:event_playerOverviewFragment13MousePressed

    private void playerOverviewFragment14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment14MousePressed
        if(maxPage > 13){
            vui.beadPlayer1.page=14;
            vui.pageScroll.setValue(14);
        }
    }//GEN-LAST:event_playerOverviewFragment14MousePressed

    private void playerOverviewFragment15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerOverviewFragment15MousePressed
        if(maxPage > 14){
            vui.beadPlayer1.page=15;
            vui.pageScroll.setValue(15);
        }
    }//GEN-LAST:event_playerOverviewFragment15MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private beadbox.PlayerOverviewFragment playerOverviewFragment1;
    private beadbox.PlayerOverviewFragment playerOverviewFragment10;
    private beadbox.PlayerOverviewFragment playerOverviewFragment11;
    private beadbox.PlayerOverviewFragment playerOverviewFragment12;
    private beadbox.PlayerOverviewFragment playerOverviewFragment13;
    private beadbox.PlayerOverviewFragment playerOverviewFragment14;
    private beadbox.PlayerOverviewFragment playerOverviewFragment15;
    private beadbox.PlayerOverviewFragment playerOverviewFragment2;
    private beadbox.PlayerOverviewFragment playerOverviewFragment3;
    private beadbox.PlayerOverviewFragment playerOverviewFragment4;
    private beadbox.PlayerOverviewFragment playerOverviewFragment5;
    private beadbox.PlayerOverviewFragment playerOverviewFragment6;
    private beadbox.PlayerOverviewFragment playerOverviewFragment7;
    private beadbox.PlayerOverviewFragment playerOverviewFragment8;
    private beadbox.PlayerOverviewFragment playerOverviewFragment9;
    // End of variables declaration//GEN-END:variables
}
