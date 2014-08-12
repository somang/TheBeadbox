package drawcircleshape;


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
       int w, h, radius;

       public notePacks(int given_radius)
       {
           radius = given_radius;
           circle = new Ellipse2D.Float(0, 0, 2*radius, 2*radius);
           setOpaque(false);
       }
       
       public Dimension getPreferredSize()
       {
           Rectangle bounds = circle.getBounds();
           return new Dimension(bounds.width, bounds.height);
       }
       
    public void paintComponent(Graphics g) {
 
        super.paintComponent(g);
        
        int y = getHeight() - radius;
        int x = getWidth()- radius;
        
        System.out.println(x);
        
        
        g.drawOval(x, y, radius, radius);
        
    }
           
}
       