/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author imdc
 */
class DeleteActionListener implements ActionListener {
    private Bead gb;
    private BeadPlayer bp;
    
    public DeleteActionListener(BeadPlayer beadPlayer1, Bead deletingBead) {
        gb = deletingBead;
        bp = beadPlayer1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bp.deleteBead(gb);
        System.out.println("deleted.");
    }
    
}
