package beadbox;


import java.awt.event.ActionEvent;
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
public class OverviewPopUp extends JPopupMenu {
        JMenuItem copy, paste, delete, cut;
        public OverviewPopUp(VibcompUI vu){
            delete = new JMenuItem("Delete");
            cut = new JMenuItem("Cut");            
            copy = new JMenuItem("Copy");
            paste = new JMenuItem("Paste");

            add(delete);
            add(cut); 
            add(copy); 
            add(paste);            
            cut.addActionListener((ActionEvent e) -> {
                vu.copy();
                vu.delete();
            });
            copy.addActionListener((ActionEvent e) ->{
                    vu.copy(); 
            });
            paste.addActionListener((ActionEvent e) -> {
                    vu.paste();   
            });
            delete.addActionListener((ActionEvent e) -> {
                    vu.delete();
            });
        }
    }