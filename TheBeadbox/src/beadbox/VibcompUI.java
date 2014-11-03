/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beadbox;


/**
 *
 * @author somang & albert
 */
public class VibcompUI extends javax.swing.JFrame {
    
    static protected boolean playing = false;
    protected Bead activeBead;
    /**
     * Creates new form VibCUI
     */
    public VibcompUI() {
        initComponents();
        activeBead = bead1;  
        beadPanelText.setVisible(false);
        beadPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        beadPlayer1 = new beadbox.BeadPlayer();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        playButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        beadPanel = new javax.swing.JPanel();
        bead1 = new beadbox.Bead();
        beadPanelText = new javax.swing.JLabel();
        barSlider = new javax.swing.JSlider();
        rightJPanel1 = new beadbox.rightJPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 800));

        beadPlayer1.setBackground(new java.awt.Color(255, 255, 255));
        beadPlayer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beadPlayer1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout beadPlayer1Layout = new javax.swing.GroupLayout(beadPlayer1);
        beadPlayer1.setLayout(beadPlayer1Layout);
        beadPlayer1Layout.setHorizontalGroup(
            beadPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );
        beadPlayer1Layout.setVerticalGroup(
            beadPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(beadPlayer1);

        jSlider1.setMajorTickSpacing(100);
        jSlider1.setMaximum(1000);
        jSlider1.setMinimum(100);
        jSlider1.setMinorTickSpacing(50);
        jSlider1.setPaintTicks(true);
        jSlider1.setToolTipText("");
        jSlider1.setBorder(javax.swing.BorderFactory.createTitledBorder("Frequency"));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jSlider2.setMinorTickSpacing(10);
        jSlider2.setPaintTicks(true);
        jSlider2.setBorder(javax.swing.BorderFactory.createTitledBorder("Intensity"));
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        playButton.setText("> Play");
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playButtonMouseClicked(evt);
            }
        });

        stopButton.setText("[] Stop");
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopButtonMouseClicked(evt);
            }
        });

        beadPanel.setMinimumSize(new java.awt.Dimension(60, 60));
        beadPanel.setName(""); // NOI18N

        bead1.setName(""); // NOI18N
        bead1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bead1MouseDragged(evt);
            }
        });
        bead1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bead1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bead1Layout = new javax.swing.GroupLayout(bead1);
        bead1.setLayout(bead1Layout);
        bead1Layout.setHorizontalGroup(
            bead1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        bead1Layout.setVerticalGroup(
            bead1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        beadPanelText.setBackground(new java.awt.Color(255, 255, 255));
        beadPanelText.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        beadPanelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        beadPanelText.setText("NEW BEAD");
        beadPanelText.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        beadPanelText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beadPanelTextMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout beadPanelLayout = new javax.swing.GroupLayout(beadPanel);
        beadPanel.setLayout(beadPanelLayout);
        beadPanelLayout.setHorizontalGroup(
            beadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadPanelLayout.createSequentialGroup()
                .addComponent(beadPanelText, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(beadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bead1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        beadPanelLayout.setVerticalGroup(
            beadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bead1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beadPanelText, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bead1.getAccessibleContext().setAccessibleName("");

        barSlider.setMinorTickSpacing(4);
        barSlider.setPaintTicks(true);
        barSlider.setPaintTrack(false);
        barSlider.setValue(10);
        barSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barSliderMouseDragged(evt);
            }
        });

        rightJPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(barSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(beadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(rightJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(barSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(playButton)
                                .addComponent(stopButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(58, Short.MAX_VALUE))
                    .addComponent(beadPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        activeBead.setMaxIntensity(jSlider2.getMaximum()/2);
        activeBead.setIntensity(jSlider2.getValue()/2);
    }//GEN-LAST:event_jSlider2StateChanged

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        int freq = jSlider1.getValue();
        //logarithmic frequenct calculation goes here
        //freq = (int) Math.log(freq)*12;
        activeBead.setFrequency(freq);
    }//GEN-LAST:event_jSlider1StateChanged

    private void bead1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bead1MouseDragged

    }//GEN-LAST:event_bead1MouseDragged

    private void bead1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bead1MouseClicked
        // TODO add your handling code here:       
    }//GEN-LAST:event_bead1MouseClicked

    private void beadPlayer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPlayer1MouseClicked
        Bead tmpBead = beadPlayer1.getBeadAt(evt.getX(), evt.getY());
        if(tmpBead == null){
            beadPanelText.setVisible(true);
            activeBead.vibcompUI= this;
            beadPlayer1.setBead(evt.getX(), evt.getY(), activeBead);
        }else{
            activeBead = tmpBead;
        }
    }//GEN-LAST:event_beadPlayer1MouseClicked

    private void playButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseClicked
        playing = true;
    }//GEN-LAST:event_playButtonMouseClicked

    private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
        playing = false;
    }//GEN-LAST:event_stopButtonMouseClicked

    private void beadPanelTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPanelTextMouseClicked
        if(beadPanel.getComponentCount()==1){           
            Bead tmpBead = new Bead();          
            tmpBead.setSize(55,55);
            beadPanel.add(tmpBead);
            tmpBead.setLocation(15, 20);
            beadPanel.repaint();
            activeBead = tmpBead;
        }
    }//GEN-LAST:event_beadPanelTextMouseClicked

    private void barSliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barSliderMouseDragged
        beadPlayer1.barPosition = barSlider.getValue();
    }//GEN-LAST:event_barSliderMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VibcompUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VibcompUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VibcompUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VibcompUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VibcompUI().setVisible(true);                
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider barSlider;
    private beadbox.Bead bead1;
    private javax.swing.JPanel beadPanel;
    private javax.swing.JLabel beadPanelText;
    public beadbox.BeadPlayer beadPlayer1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JButton playButton;
    protected beadbox.rightJPanel rightJPanel1;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}