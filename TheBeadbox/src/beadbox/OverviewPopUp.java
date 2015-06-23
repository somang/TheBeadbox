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
 * @author imdc
 */
public class OverviewPopUp extends JPopupMenu {
        JMenuItem copy, paste, delete, cut;
        public OverviewPopUp(VibcompUI vu){
            cut = new JMenuItem("Cut");
            copy = new JMenuItem("Copy");
            paste = new JMenuItem("Paste");
            delete = new JMenuItem("Delete");
            add(cut); 
            add(copy); 
            add(paste);
            add(delete); 
            cut.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vu.copy(); 
                    vu.delete();
                }
            });
            copy.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vu.copy(); 
                }
            });
            paste.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vu.paste();                 
                }
            });
            delete.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vu.delete();
                }
            });
        }
    }