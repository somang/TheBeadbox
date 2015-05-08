/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import com.synthbot.jasiohost.AsioDriver;
import javax.swing.JMenuItem;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    final private JPopupMenu menuPopup = new JPopupMenu();

    Bead startBead, endBead;
    Bead prevBead = null;
    int startBead_x, startBead_y, endBead_x, endBead_y;
    boolean dragStatus = false;
    boolean move = false;
    Point point1, point2;
    Bead beadOnClick;
    ProtocolHandler ph = new ProtocolHandler();

    /**
     * Creates new form VibC UI
     */
    public VibcompUI() {
        initComponents();
        endBead = null;
        activeBead = null;
        beadPanel.repaint();
        //setExtendedState(this.MAXIMIZED_BOTH);        
        //Component[] incrButton = pageScroll.getComponents();
        //for (int i=0; i<incrButton.length;i++){
        //    System.out.println(incrButton[i]);            
        //}
        /* load the driver */
        try {
            /*
             https://github.com/mhroth/jasiohost
             */
            driver = AsioDriver.getDriver("ASIO PreSonus FireStudio");
            listener = new AsioSoundHost(driver);
            driver.start();
            driverLoaded = true;
            System.out.println("loaded.");
            //System.out.println(driver.getNumChannelsInput());
            //System.out.println(driver.getNumChannelsOutput());
            //System.out.println(driver.getName());
            //System.out.println(driver.getCurrentState());
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Please install the Following Driver: ASIO PreSonus FireStudio");
            JOptionPane.showMessageDialog(null, "Please install the Following Driver: ASIO PreSonus FireStudio");
            driverLoaded = false;
        } catch (com.synthbot.jasiohost.AsioException err) {
            System.out.println("Output Device not found: Please connect the Asio device");
            JOptionPane.showMessageDialog(null, "Output Device not found: Please connect the Asio device");
            driverLoaded = false;
        }
        beadPlayer1.vibcompUI = this;
        beadPlayer1.pageLab = pagelabel;
        menuPopup.add(BeadMenuDelete);
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
        beadPanel = new javax.swing.JPanel();
        beadPanelText = new javax.swing.JLabel();
        barSlider = new javax.swing.JSlider();
        rightJPanel1 = new beadbox.rightJPanel();
        pageScroll = new javax.swing.JScrollBar();
        playerOverview1 = new beadbox.PlayerOverview();
        jPanel1 = new javax.swing.JPanel();
        speedControl = new javax.swing.JSlider();
        saveButton = new javax.swing.JButton();
        addPage = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pagelabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        beadPlayer1.setBackground(new java.awt.Color(255, 255, 255));
        beadPlayer1.setPreferredSize(new java.awt.Dimension(1000, 550));
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
            .addGap(0, 1000, Short.MAX_VALUE)
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

        playerOverview1.setBackground(new java.awt.Color(100, 100, 100));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));

        speedControl.setMinorTickSpacing(10);
        speedControl.setPaintTicks(true);
        speedControl.setToolTipText("Speed Control");
        speedControl.setBorder(javax.swing.BorderFactory.createTitledBorder("Speed Control"));
        speedControl.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedControlStateChanged(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        addPage.setText("Add Page [+]");
        addPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPageMouseClicked(evt);
            }
        });

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beadbox/play_pause.png"))); // NOI18N
        playButton.setToolTipText("Play / Pause");
        playButton.setActionCommand("> Play ");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        openButton.setText("Open");
        openButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openButtonMouseClicked(evt);
            }
        });

        jLabel1.setText("Page");

        pagelabel.setText("1");
        pagelabel.setToolTipText("");
        pagelabel.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(openButton)
                        .addGap(18, 18, 18)
                        .addComponent(addPage))
                    .addComponent(speedControl, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton)
                            .addComponent(addPage)
                            .addComponent(openButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedControl, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(pagelabel)))
                .addContainerGap())
        );

        openButton.getAccessibleContext().setAccessibleName("openFile");
        pagelabel.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pageScroll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(playerOverview1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(barSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(beadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(intensitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frequencySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rightJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(barSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pageScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rightJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(intensitySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(frequencySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(playerOverview1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void intensitySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_intensitySliderStateChanged
        if (activeBead != null) {
            activeBead.setMaxIntensity(intensitySlider.getMaximum() / 2);
            activeBead.setIntensity(intensitySlider.getValue());
            beadPanel.repaint();
        }
    }//GEN-LAST:event_intensitySliderStateChanged

    private void frequencySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_frequencySliderStateChanged
        if (activeBead != null) {
            int freq = frequencySlider.getValue();
            //logarithmic frequenct calculation goes here
            //freq = (int) Math.log(freq)*12;
            activeBead.setFrequency(freq);
            beadPanel.repaint();
        }
    }//GEN-LAST:event_frequencySliderStateChanged

    private void barSliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barSliderMouseDragged
        beadPlayer1.barPosition = barSlider.getValue();
    }//GEN-LAST:event_barSliderMouseDragged

    private void barSliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barSliderMouseClicked
        beadPlayer1.barPosition = barSlider.getValue();
    }//GEN-LAST:event_barSliderMouseClicked

    private void pageScrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pageScrollMouseClicked
        beadPlayer1.page = pageScroll.getValue();
        //pagelabel.setText(Integer.toString(pageScroll.getValue()));
        if (activeBead != null) {
            if (activeBead.page != pageScroll.getValue()) {
                if (activeBead.connectedTo != null) {
                    activeBead = null;
                }
                beadPanelText.setVisible(true);
            }
        }
    }//GEN-LAST:event_pageScrollMouseClicked

    private void pageScrollMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pageScrollMouseDragged
        beadPlayer1.page = pageScroll.getValue();
        //pagelabel.setText(Integer.toString(pageScroll.getValue()));
        if (activeBead != null) {
            if (activeBead.page != pageScroll.getValue()) {
                if (activeBead.connectedTo != null) {
                    activeBead = null;
                }
                beadPanelText.setVisible(true);
            }
        }
    }//GEN-LAST:event_pageScrollMouseDragged

    private void beadPanelTextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPanelTextMousePressed
        if (isBeadPanelEmpty()) {
            refreshBeadPanel();
        }
    }//GEN-LAST:event_beadPanelTextMousePressed

    public boolean isBeadPanelEmpty() {
        /**
         * 1 if empty 2 if filled
         */
        if (beadPanel.getComponentCount() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void refreshBeadPanel() {
        Bead tmpBead = new Bead();
        tmpBead.setSize(55, 55);
        beadPanel.add(tmpBead);
        tmpBead.setLocation(15, 20);
        activeBead = tmpBead;
        beadPanel.repaint();
        prevBead = tmpBead;
    }

    private void beadPlayer1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPlayer1MousePressed
        /*
         2015-04-29
        
         */
        point1 = evt.getPoint();
        Bead tmpBead = beadPlayer1.getBeadAt(point1.x, point1.y, beadPlayer1.page);

        if (evt.getButton() == MouseEvent.BUTTON1) // Left click
        {
            if (activeBead != null) {
                if (tmpBead == null) {//Create one                    
                    beadPanelText.setVisible(true);
                    activeBead.vibcompUI = this;
                    beadPlayer1.setBead(point1.x, point1.y, activeBead);
                } else {          // There already is a bead at the location          
                    if (!activeBead.playable) {
                        remove(activeBead); //Remove bead panel glitch
                    } else {
                        activeBead = tmpBead;
                    }
                }
                //set slider positions
                intensitySlider.setValue(activeBead.getIntensity());
                frequencySlider.setValue(activeBead.getFrequency());
            } else if (beadPlayer1.beads.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please click 'New Bead' to create a Bead, then Click on then click on the canvas");
            } else if (activeBead == null && tmpBead != null) {
                activeBead = tmpBead;
            } else { //activeBead == null AND tmpBead == null
                //do nothing.
            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {// Right click
            if (tmpBead != null) {
                tmpBead.setComponentPopupMenu(menuPopup);
                BeadMenuDelete.addActionListener(new DeleteActionListener(beadPlayer1, tmpBead, playerOverview1));

                /*
                 System.out.println("Bead Info: \nPage: " + tmpBead.page + "\nTrack: " + tmpBead.track
                 + "\nLocation X: " + tmpBead.getLocation().x + "\nFrequency: " + tmpBead.getFrequency()
                 + "\nIntensity: " + tmpBead.getIntensity() + "\n");
                 BeadMenuDelete.setToolTipText("Bead Info-  Page: " + tmpBead.page + "  Track: " + tmpBead.track
                 + "  Location X: " + tmpBead.getLocation().x + "  Frequency: " + tmpBead.getFrequency()
                 + "  Intensity: " + tmpBead.getIntensity());
                 */
            }
        }

    }//GEN-LAST:event_beadPlayer1MousePressed

    /* 
     While there is a startBead, where the dragging started,
     this gets ending coordinates of the mouse dragging,
     and calculates the eucleadian distance in between
     so that it returns whether the drag is valid or not.
    
     tempMinimumDistance. = 100;
     */
    private boolean isActualDrag(int endBeadx, int endBeady) {
        int xdif = 0;
        int ydif = 0;
        double distance;
        try {
            xdif = endBeadx - activeBead.getX();
            ydif = endBeady - activeBead.getY();
            distance = Math.sqrt(Math.abs(xdif * xdif - ydif * ydif)); //Euclidean distance.
            if (distance > 55) { //if distance is greater than 100
                return true;
            }
        } catch (NullPointerException e) {
        }
        return false;
    }

    private void beadPlayer1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPlayer1MouseReleased
        endBead = beadPlayer1.getBeadAt(endBead_x, endBead_y, beadPlayer1.page); // Check if there is a bead in the end.  
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (dragStatus && isActualDrag(endBead_x, endBead_y)) {
                if (activeBead != null) {
                    if (endBead == null) {
                        if (activeBead.connectedTo == null) {//If there is no bead, and A is not connected, create one.
                            if (!activeBead.playable) {
                                remove(activeBead); //Remove bead panel glitch
                            } else {
                                endBead = new Bead();
                                endBead.setSize(55, 55);
                                endBead.setIntensity(activeBead.getIntensity());
                                endBead.setFrequency(activeBead.getFrequency());
                                endBead.setConnection(activeBead);
                                endBead.vibcompUI = this;
                                beadPlayer1.setBead(endBead_x, endBead_y, endBead);
                                activeBead = endBead;
                            }
                        } else {
                            int yLoc = (beadPlayer1.getTrackAt(endBead_y) - 1) * beadPlayer1.TRACKHEIGHT + 5;
                            activeBead.setTrack(activeBead.getTrack());
                            activeBead.setLocation(endBead_x, yLoc);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "There is no active Bead!");
                }
            }
            dragStatus = false;
        }
    }//GEN-LAST:event_beadPlayer1MouseReleased

    private void beadPlayer1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beadPlayer1MouseDragged
        // dynamically get where the drag ends.
        point2 = evt.getPoint();
        endBead_x = point2.x;
        endBead_y = point2.y;

        if (activeBead != null) {
            if (activeBead.connectedTo == null) {
                dragStatus = true;
            }
        }

    }//GEN-LAST:event_beadPlayer1MouseDragged

    private void addPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPageMouseClicked
        BeadPlayer.maxPage++;
        pageScroll.setMaximum(BeadPlayer.maxPage + 1);
        pageScroll.setValue(BeadPlayer.maxPage);
        beadPlayer1.page = BeadPlayer.maxPage;
        //pagelabel.setText(Integer.toString(pageScroll.getValue()));
        if (activeBead != null) {
            if (activeBead.page != pageScroll.getValue()) {
                if (activeBead.connectedTo != null) {
                    activeBead = null;
                }
                beadPanelText.setVisible(true);
            }
        }
    }//GEN-LAST:event_addPageMouseClicked

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed

        if (playing) {
            //playButton.setText("> PLAY");
            playing = false;
        } else {
            //playButton.setText("[] STOP");
            playing = true;
        }

    }//GEN-LAST:event_playButtonActionPerformed

    private void speedControlStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedControlStateChanged
        // playing speed slider
        beadPlayer1.SPEED = 100 - speedControl.getValue();

    }//GEN-LAST:event_speedControlStateChanged

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            // TODO add your handling code here:
            ph.saveFile(beadPlayer1, rightJPanel1);
        } catch (Exception ex) {
            Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void openButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openButtonMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            try {
                new OpenFile(selectedFile, beadPlayer1);
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_openButtonMouseClicked

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openButton;
    protected javax.swing.JScrollBar pageScroll;
    private javax.swing.JLabel pagelabel;
    private javax.swing.JButton playButton;
    protected beadbox.PlayerOverview playerOverview1;
    protected beadbox.rightJPanel rightJPanel1;
    private javax.swing.JButton saveButton;
    private javax.swing.JSlider speedControl;
    // End of variables declaration//GEN-END:variables

}
