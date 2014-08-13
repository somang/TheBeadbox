package vibc;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;


public class notePacks extends JPanel
{
       Ellipse2D.Float circle;
       int radius;

       public notePacks(int given_radius)
       {
           radius = given_radius;
       }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Not a Hello, World program", 75, 100);
    }

}
