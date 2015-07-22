package beadbox;


import static beadbox.VibcompUI.multiSelect;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author imdc Albert
 */
public class BeadPlayerPopUp extends JPopupMenu {
        JMenuItem copy, paste, delete, cut;
        VibcompUI vu;
        public BeadPlayerPopUp(VibcompUI vui){
            vu = vui;
            delete = new JMenuItem("Delete"); delete.setToolTipText("(backspace)");
            cut = new JMenuItem("Cut"); cut.setToolTipText("(ctrl+x)");
            copy = new JMenuItem("Copy"); copy.setToolTipText("(ctrl+c)");
            paste = new JMenuItem("Paste"); paste.setToolTipText("(ctrl+v)");

            add(delete);
            add(cut); 
            add(copy); 
            add(paste);            
            cut.addActionListener((ActionEvent e) -> {
                copy();
                delete();
            });
            copy.addActionListener((ActionEvent e) ->{
                    copy(); 
            });
            paste.addActionListener((ActionEvent e) -> {
                    paste();   
            });
            delete.addActionListener((ActionEvent e) -> {
                    delete();
            });
        }
        
        public void copy(){
        try {
            /*Copy this page*/
            VibroMidiFile tempmf = new VibroMidiFile();
            // Initiate the tracks
            for (int i = 0; i < vu.beadPlayer1.tracksize; i++) { // 0-7
                tempmf.noteOn(0, i, 60, 60);
                tempmf.noteOff(0, i, 0);
            }
            
            ArrayList<Bead> beadsOnThisPage = multiSelect;
            
            if (beadsOnThisPage != null) {
                for (int i = 0; i < beadsOnThisPage.size(); i++) {
                    Bead tmpbead = beadsOnThisPage.get(i);
                    tempmf = beadInfoParser.parseBead(tempmf, tmpbead, true);
                }
                tempmf.writeToFile("tmp.mid");
            }
            
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(PlayerOverview.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerOverview.class.getName()).log(Level.SEVERE, null, ex);
        }
        //multiSelect.clear();
    }
    
    public void paste(){
        File file = new File ("tmp.mid");
        try {
            new OpenFile(file, vu, true);
        }catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(PlayerOverview.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (MidiUnavailableException ex) {
            System.out.println(ex);
            Logger.getLogger(PlayerOverview.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMidiDataException ex) {
            System.out.println(ex);
            Logger.getLogger(PlayerOverview.class.getName()).log(Level.SEVERE, null, ex);
        }
        multiSelect.clear();
    }
    
    public void delete(){
        ArrayList<Bead> beadAry= new ArrayList<>(); 
        for (Bead bead : multiSelect)beadAry.add(bead);
        for(Bead bead : beadAry) vu.beadPlayer1.deleteBead(bead);
        multiSelect.clear();
    }
    
    public void open(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();         
            try {
                new OpenFile(selectedFile, vu, false);
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void save(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if(!selectedFile.getName().endsWith(".vidi")) 
                selectedFile = new File(selectedFile.toString()+".vidi");
            try {
                // TODO add your handling code here:
                vu.ph.saveFile(vu.beadPlayer1, vu.rightJPanel1,selectedFile.getAbsolutePath());
                System.out.println("Saved file: " + selectedFile.getAbsolutePath());
            } catch (Exception ex) {
                Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void saveToServer(){      
        try {
            //save and send current Beadbox file
            vu.ph.saveFile(vu.beadPlayer1, vu.rightJPanel1,"severFile.vidi");
            File file = new File ("severFile.vidi");
            new FileUploader(file, "beadbox.vidi");
            
            //save and send extra info
            PrintWriter writer = new PrintWriter("serverFileInfo.txt", "UTF-8");
            writer.println("playing: "+vu.playing);
            writer.println("page: "+vu.beadPlayer1.page);
            writer.close();
            File file2 = new File ("serverFileInfo.txt");
            new FileUploader(file2, "beadboxInfo.txt");
        } catch (Exception ex) {
            Logger.getLogger(VibcompUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}