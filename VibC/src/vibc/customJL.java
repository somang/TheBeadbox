/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vibc;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author imdc
 */
public class customJL extends JPanel {
    
    int x,y,radi;
    public customJL(int radius){
        x=0;y=0;
        radi = radius;
    }  
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //invalidate();
        x++;y++;
        //g.fillOval(x, y, radi, radi);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        invalidate();
        System.out.println(getHeight());
        g.drawLine(x, y, getWidth(), getHeight());
    }
    
}
