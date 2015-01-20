/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beadbox;

import com.synthbot.jasiohost.AsioDriver;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


/**
 *
 * @author somang & albert
 */
public class VibcompUI extends javax.swing.JFrame {
    
    static protected boolean playing = false;
    protected Bead activeBead;
    protected AsioDriver driver;
    protected AsioSoundHost listener;   
    protected boolean driverLoaded;
    final private JMenuItem BeadMenuDelete = new JMenuItem("Delete");
    final private JMenuItem BeadMenuDuration = new JMenuItem("Duration");
    final private JPopupMenu menuPopup = new JPopupMenu();

    Bead startBead,endBead;
    Bead prevBead=null;
    int startBead_x,startBead_y,endBead_x,endBead_y;
    boolean dragStatus=false;
    Point point1,point2;
    Bead beadOnClick;
    
    
    /**
     * Creates new form VibCUI
     */
    public VibcompUI() {
        initComponents();
        startBead= null;
        endBead = null;
        activeBead = null;  
        beadPanel.repaint();
        
        //Component[] incrButton = pageScroll.getComponents();
        //for (int i=0; i<incrButton.length;i++){
        //    System.out.println(incrButton[i]);            
        //}
        
        
        //this.setComponentZOrder(beadPlayer1, 1);
        //this.setComponentZOrder(barSlider, 0);
        
        /* load the driver */
        try {
            /*
                https://github.com/mhroth/jasiohost
            */
            driver = AsioDriver.getDriver ( "ASIO PreSonus FireStudio" );
            listener = new AsioSoundHost ( driver );
            driver.start();
            driverLoaded = true;
            System.out.println("loaded.");
            //System.out.println(driver.getNumChannelsInput());
            //System.out.println(driver.getNumChannelsOutput());
            //System.out.println(driver.getName());
            //System.out.println(driver.getCurrentState());
            
        }
        catch ( UnsatisfiedLinkError e ){ 
            System.out.println("Please install the Following Driver: ASIO PreSonus FireStudio");
            driverLoaded = false;
        }
        catch (com.synthbot.jasiohost.AsioException err){
            System.out.println("Output Device not found: Please connect the Asio device");
            driverLoaded = false;
        }
        beadPlayer1.vibcompUI = this;
        
        menuPopup.add(BeadMenuDelete);
        menuPopup.add(BeadMenuDuration);
        
        
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
        frequencySlider = new javax.swing.JSlider();
        intensitySlider = new javax.swing.JSlider();
        playButton = new javax.swing.JButton();
        beadPanel = new javax.swing.JPanel();
        beadPanelText = new javax.swing.JLabel();
        barSlider = new javax.swing.JSlider();
        rightJPanel1 = new beadbox.rightJPanel();
        pageScroll = new javax.swing.JScrollBar();
        addPage = new javax.swing.JButton();
        playerOverview1 = new beadbox.PlayerOverview();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        beadPlayer1.setBackground(new java.awt.Color(255, 255, 255));
        beadPlayer1.setPreferredSize(new java.awt.Dimension(1350, 550));
        beadPlayer1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                beadPlayer1MouseDragged(evt);
            }
        });
        beadPlayer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                beadPlayer1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                beadPlayer1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout beadPlayer1Layout = new javax.swing.GroupLayout(beadPlayer1);
        beadPlayer1.setLayout(beadPlayer1Layout);
        beadPlayer1Layout.setHorizontalGroup(
            beadPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        beadPlayer1Layout.setVerticalGroup(
            beadPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(beadPlayer1);

        frequencySlider.setMajorTickSpacing(100);
        frequencySlider.setMaximum(1000);
        frequencySlider.setMinimum(100);
        frequencySlider.setMinorTickSpacing(50);
        frequencySlider.setOrientation(javax.swing.JSlider.VERTICAL);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setToolTipText("");
        frequencySlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Frequency"));
        frequencySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                frequencySliderStateChanged(evt);
            }
        });

        intensitySlider.setMinorTickSpacing(10);
        intensitySlider.setOrientation(javax.swing.JSlider.VERTICAL);
        intensitySlider.setPaintTicks(true);
        intensitySlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Intensity"));
        intensitySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                intensitySliderStateChanged(evt);
            }
        });

        playButton.setText("> Play");
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playButtonMousePressed(evt);
            }
        });

        beadPanel.setMinimumSize(new java.awt.Dimension(60, 60));
        beadPanel.setName(""); // NOI18N

        beadPanelText.setBackground(new java.awt.Color(255, 255, 255));
        beadPanelText.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        beadPanelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        beadPanelText.setText("NEW BEAD");
        beadPanelText.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        beadPanelText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                beadPanelTextMousePressed(evt);
            }
        });

        javax.swing.GroupLayout beadPanelLayout = new javax.swing.GroupLayout(beadPanel);
        beadPanel.setLayout(beadPanelLayout);
        beadPanelLayout.setHorizontalGroup(
            beadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadPanelLayout.createSequentialGroup()
                .addComponent(beadPanelText, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        beadPanelLayout.setVerticalGroup(
            beadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beadPanelText, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        barSlider.setMajorTickSpacing(50);
        barSlider.setMaximum(1000);
        barSlider.setPaintLabels(true);
        barSlider.setPaintTicks(true);
        barSlider.setPaintTrack(false);
        barSlider.setValue(10);
        barSlider.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        barSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barSliderMouseDragged(evt);
            }
        });
        barSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barSliderMouseClicked(evt);
            }
        });

        rightJPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pageScroll.setMaximum(3);
        pageScroll.setMinimum(1);
        pageScroll.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        pageScroll.setVisibleAmount(1);
        pageScroll.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pageScrollMouseDragged(evt);
            }
        });
        pageScroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pageScrollMouseClicked(evt);
            }
        });

        addPage.setText("Add Page [+]");
        addPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addPage))
                    .addComponent(pageScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(barSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playerOverview1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(beadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(intensitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frequencySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(barSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pageScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rightJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(beadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(playerOverview1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(intensitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(frequencySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void intensitySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_intensitySliderStateChanged
        activeBead.setMaxIntensity(intensitySlider.getMaximum()/2);
        activeBead.setIntensity(intensitySlider.getValue()/2);
    }//GEN-LAST:event_intensitySliderStateChanged

    private void frequencySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_frequencySliderStateChanged
        int freq = frequencySlider.getValue();
        //logarithmic frequenct calculation goes here
        //freq = (int) Math.log(freq)*12;
        activeBead.setFrequency(freq);
    }//GEN-LAST:event_frequencySliderStateChanged

    private void barSliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barSliderMouseDragged
        beadPlayer1.barPosition = barSlider.getValue();
    }//GEN-LAST:event_barSliderMouseDragged

    private void barSliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barSliderMouseClicked
        beadPlayer1.barPosition = barSlider.getValue();
    }//GEN-LAST:event_barSliderMouseClicked

    private void pageScrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pageScrollMouseClicked
        beadPlayer1.page = pageScroll.getValue();
    }//GEN-LAST:event_pageScrollMouseClicked

    private void pageScrollMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pageScrollMouseDragged
        beadPlayer1.page = pageScroll.getValue();
    }//GEN-LAST:event_pageScrollMouseDragged

    private void beadPanelTextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPanelTextMousePressed
        if(beadPanel.getComponentCount()==1){           
            Bead tmpBead = new Bead();          
            tmpBead.setSize(55,55);
            beadPanel.add(tmpBead);
            tmpBead.setLocation(15, 20);
            beadPanel.repaint();
            activeBead = tmpBead;
        }
    }//GEN-LAST:event_beadPanelTextMousePressed

    private void beadPlayer1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPlayer1MousePressed
        
        /*Few bugs. 2014-12-07
        1. After a few repetited placement, the new bead button does not appear.
        2. Above is causing new problem, which draws connection line in random position
        when the page is turned already.
        
        */
        point1 = evt.getPoint();        
        Bead tmpBead = beadPlayer1.getBeadAt(point1.x, point1.y);
        if (evt.getButton() == MouseEvent.BUTTON1) // Left click
        {
            if(activeBead != null){
                if(tmpBead == null){//Create one
                    beadPanelText.setVisible(true);
                    activeBead.vibcompUI= this;
                    beadPlayer1.setBead(point1.x, point1.y, activeBead);
                    try{
                        prevBead.setBorder(BorderFactory.createEmptyBorder());
                    }catch(Exception e){
                        
                    }
                    prevBead = activeBead;
                    
                }else{
                    //Select.
                    activeBead = tmpBead;
                    frequencySlider.setValue(activeBead.getFrequency());
                    intensitySlider.setValue(activeBead.getIntensity()*2);
                    
                    // If selected, then create a border to show.
                    tmpBead.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (tmpBead!=prevBead){
                        prevBead.setBorder(BorderFactory.createEmptyBorder());
                    }
                    prevBead = tmpBead;
                    
                    //Dragging.
                    startBead = tmpBead;
                    startBead_x = startBead.getX();
                    startBead_y = startBead.getY();
                }
            }else JOptionPane.showMessageDialog(null, "Please click 'New Bead' to create a Bead, then Click on then click on the canvas");
        }else if (evt.getButton() == MouseEvent.BUTTON3) // Right click
        {
            if (tmpBead != null){
                
                tmpBead.setComponentPopupMenu(menuPopup);
                BeadMenuDelete.addActionListener(new DeleteActionListener(beadPlayer1,tmpBead));
            }
        }
    }//GEN-LAST:event_beadPlayer1MousePressed

    private void beadPlayer1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPlayer1MouseReleased
        endBead = beadPlayer1.getBeadAt(endBead_x, endBead_y); // Check if there is a bead in the end.
        if (dragStatus && evt.isControlDown()){ //if control is down, AND dragged.   
            if (activeBead != null){
                if(endBead == null){//If there is no bead.
                    endBead = new Bead();                 
                    endBead.setSize(55,55);
                    endBead.setIntensity(activeBead.getIntensity());
                    endBead.setFrequency(activeBead.getFrequency());
                    endBead.setConnection(activeBead);
                    endBead.vibcompUI = this;
                    beadPlayer1.setBead(endBead_x, endBead_y, endBead);
                }else{//If there is a bead already.
                    
                    endBead_x = endBead.getX();
                    endBead_y = endBead.getY();
                }               
            }else JOptionPane.showMessageDialog(null, "There is no active Bead!");
            
            System.out.println("dragged from " + startBead_x + "," + startBead_y + " to " + endBead_x + "," + endBead_y);
        }
        dragStatus = false;
    }//GEN-LAST:event_beadPlayer1MouseReleased

    private void beadPlayer1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPlayer1MouseDragged
        // TODO add your handling code here:
        point2 = evt.getPoint();
        endBead_x = point2.x;
        endBead_y = point2.y;
        dragStatus=true;

        //System.out.println("Dragging at"+endBead_x+","+endBead_y);
        
        if (dragStatus){
            /*The line between two beads come here.*/
            //Point startPoint = new Point(startBead_x,startBead_y);
            //Line2D line2d = new Line2D.Double(point1,point2);
            //repaint();
        }
        
    }//GEN-LAST:event_beadPlayer1MouseDragged

    private void playButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMousePressed
        if(playing){        
            playButton.setText("> PLAY");
            playing = false;
        }else{
            playButton.setText("[] STOP");
            playing = true;
        }
        
    }//GEN-LAST:event_playButtonMousePressed

    private void addPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPageMouseClicked
        BeadPlayer.maxPage++;
        pageScroll.setMaximum(BeadPlayer.maxPage+1);
    }//GEN-LAST:event_addPageMouseClicked

    
    
    
    
    
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
    private javax.swing.JButton addPage;
    private javax.swing.JSlider barSlider;
    private javax.swing.JPanel beadPanel;
    private javax.swing.JLabel beadPanelText;
    public beadbox.BeadPlayer beadPlayer1;
    private javax.swing.JSlider frequencySlider;
    private javax.swing.JSlider intensitySlider;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JScrollBar pageScroll;
    private javax.swing.JButton playButton;
    protected beadbox.PlayerOverview playerOverview1;
    protected beadbox.rightJPanel rightJPanel1;
    // End of variables declaration//GEN-END:variables


}
