/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beadbox;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

/**
 *
 * @author Somang and Albert
 */
public class rightJPanel extends javax.swing.JPanel {
    PointerInfo mouseP = MouseInfo.getPointerInfo();
    int activeSideBead = 0;
    int ofsetX = 0, ofsetY=0;
    
    /**
     * Creates new form rightJPanel
     */
    public rightJPanel() {
        initComponents();
        loadLights();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        beadlight1 = new beadbox.Beadlight();
        jLabel1 = new javax.swing.JLabel();
        beadlight2 = new beadbox.Beadlight();
        jLabel2 = new javax.swing.JLabel();
        beadlight3 = new beadbox.Beadlight();
        jLabel3 = new javax.swing.JLabel();
        beadlight4 = new beadbox.Beadlight();
        jLabel4 = new javax.swing.JLabel();
        beadlight5 = new beadbox.Beadlight();
        jLabel5 = new javax.swing.JLabel();
        beadlight6 = new beadbox.Beadlight();
        jLabel6 = new javax.swing.JLabel();
        beadlight7 = new beadbox.Beadlight();
        jLabel7 = new javax.swing.JLabel();
        beadlight8 = new beadbox.Beadlight();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        beadlight1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight1MouseDragged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("1");

        javax.swing.GroupLayout beadlight1Layout = new javax.swing.GroupLayout(beadlight1);
        beadlight1.setLayout(beadlight1Layout);
        beadlight1Layout.setHorizontalGroup(
            beadlight1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight1Layout.setVerticalGroup(
            beadlight1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName("");

        beadlight2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight2MouseDragged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("2");

        javax.swing.GroupLayout beadlight2Layout = new javax.swing.GroupLayout(beadlight2);
        beadlight2.setLayout(beadlight2Layout);
        beadlight2Layout.setHorizontalGroup(
            beadlight2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight2Layout.setVerticalGroup(
            beadlight2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.getAccessibleContext().setAccessibleName("");

        beadlight3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight3MouseDragged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("3");

        javax.swing.GroupLayout beadlight3Layout = new javax.swing.GroupLayout(beadlight3);
        beadlight3.setLayout(beadlight3Layout);
        beadlight3Layout.setHorizontalGroup(
            beadlight3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight3Layout.setVerticalGroup(
            beadlight3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.getAccessibleContext().setAccessibleName("");

        beadlight4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight4MouseDragged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("4");

        javax.swing.GroupLayout beadlight4Layout = new javax.swing.GroupLayout(beadlight4);
        beadlight4.setLayout(beadlight4Layout);
        beadlight4Layout.setHorizontalGroup(
            beadlight4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight4Layout.setVerticalGroup(
            beadlight4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.getAccessibleContext().setAccessibleName("");

        beadlight5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight5MouseDragged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("5");

        javax.swing.GroupLayout beadlight5Layout = new javax.swing.GroupLayout(beadlight5);
        beadlight5.setLayout(beadlight5Layout);
        beadlight5Layout.setHorizontalGroup(
            beadlight5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight5Layout.setVerticalGroup(
            beadlight5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.getAccessibleContext().setAccessibleName("");

        beadlight6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight6MouseDragged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("6");

        javax.swing.GroupLayout beadlight6Layout = new javax.swing.GroupLayout(beadlight6);
        beadlight6.setLayout(beadlight6Layout);
        beadlight6Layout.setHorizontalGroup(
            beadlight6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight6Layout.setVerticalGroup(
            beadlight6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.getAccessibleContext().setAccessibleName("");

        beadlight7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight7MouseDragged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("7");

        javax.swing.GroupLayout beadlight7Layout = new javax.swing.GroupLayout(beadlight7);
        beadlight7.setLayout(beadlight7Layout);
        beadlight7Layout.setHorizontalGroup(
            beadlight7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight7Layout.setVerticalGroup(
            beadlight7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel7.getAccessibleContext().setAccessibleName("");

        beadlight8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadlight8MouseDragged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("8");

        javax.swing.GroupLayout beadlight8Layout = new javax.swing.GroupLayout(beadlight8);
        beadlight8.setLayout(beadlight8Layout);
        beadlight8Layout.setHorizontalGroup(
            beadlight8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beadlight8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );
        beadlight8Layout.setVerticalGroup(
            beadlight8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadlight8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beadlight6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beadlight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beadlight2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beadlight3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beadlight4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beadlight5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beadlight8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beadlight7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beadlight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beadlight2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beadlight3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beadlight4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beadlight5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beadlight6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(beadlight7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beadlight8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void beadlight1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight1MouseDragged
       Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=1){
            activeSideBead=1;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }
    }//GEN-LAST:event_beadlight1MouseDragged

    private void beadlight2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight2MouseDragged
        Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=2){
            activeSideBead=2;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }    }//GEN-LAST:event_beadlight2MouseDragged

    private void beadlight3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight3MouseDragged
        Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=3){
            activeSideBead=3;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }
    }//GEN-LAST:event_beadlight3MouseDragged

    private void beadlight4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight4MouseDragged
        Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=4){
            activeSideBead=4;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }
    }//GEN-LAST:event_beadlight4MouseDragged

    private void beadlight5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight5MouseDragged
        Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=5){
            activeSideBead=5;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }
    }//GEN-LAST:event_beadlight5MouseDragged

    private void beadlight6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight6MouseDragged
        Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=6){
            activeSideBead=6;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }
    }//GEN-LAST:event_beadlight6MouseDragged

    private void beadlight7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight7MouseDragged
        Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=7){
            activeSideBead=7;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }
    }//GEN-LAST:event_beadlight7MouseDragged

    private void beadlight8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadlight8MouseDragged
        Beadlight object = (Beadlight)evt.getSource();
        if(activeSideBead!=8){
            activeSideBead=8;
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            ofsetX = ((int)b.getX() - object.getLocation().x );
            ofsetY = ((int)b.getY() - object.getLocation().y );
        }
        else{
            mouseP = MouseInfo.getPointerInfo();
            Point b = mouseP.getLocation();
            object.setLocation(b.x-ofsetX, b.y-ofsetY);
        }
    }//GEN-LAST:event_beadlight8MouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private beadbox.Beadlight beadlight1;
    private beadbox.Beadlight beadlight2;
    private beadbox.Beadlight beadlight3;
    private beadbox.Beadlight beadlight4;
    private beadbox.Beadlight beadlight5;
    private beadbox.Beadlight beadlight6;
    private beadbox.Beadlight beadlight7;
    private beadbox.Beadlight beadlight8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables

    private void loadLights() {
        beadlight1.setTrack(1);
        beadlight2.setTrack(2);
        beadlight3.setTrack(3);
        beadlight4.setTrack(4);
        beadlight5.setTrack(5);
        beadlight6.setTrack(6);
        beadlight7.setTrack(7);
        beadlight8.setTrack(8);
    }
}
