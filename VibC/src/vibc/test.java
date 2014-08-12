/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vibc;

import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author imdc
 */
public class test {
    testGUI GUI;
    JSlider slider;
    JPanel jp;
    JLabel jl;
    
    public test(testGUI g){
        slider = g.jSlider1;
        jp = g.jPanel1;
        drawCircle();
    }
    public void print(){
        System.out.println(jp.getHeight()+":"+jp.getWidth());
    }
    
    public void drawCircle(){
        jp.add("rtet", new customJL(23));
        jp.paintComponents(GUI.getGraphics());
    }
    
    
        
        
}


