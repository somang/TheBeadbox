/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author somang
 */
class DeleteActionListener implements ActionListener {
    private Bead db;
    private BeadPlayer bp;
    
    public DeleteActionListener(BeadPlayer beadPlayer1, Bead deletingBead, PlayerOverview overview1) {
        db = deletingBead;
        bp = beadPlayer1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bp.deleteBead(db);        
    }
    
    
}
