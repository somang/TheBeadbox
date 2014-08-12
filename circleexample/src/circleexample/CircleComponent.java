import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;

public class CircleComponent extends JPanel
{
       Ellipse2D.Double circle;

       public CircleComponent(int radius)
       {
           circle = new Ellipse2D.Double(0, 0, radius, radius);
           setOpaque(false);
       }

       public Dimension getPreferredSize()
       {
            Rectangle bounds = circle.getBounds();
           return new Dimension(bounds.width, bounds.height);
       }

       public void paintComponent(Graphics g)
       {
           super.paintComponent(g);
           Graphics2D g2 = (Graphics2D) g;
           g2.setColor( getForeground() );
           g2.fill(circle);

       }

    public static void main(String[] args)
    {
            //  Create a panel using a null layout so we can add components at random positions
            final JPanel center = new JPanel();
            center.setLayout(null);

              JButton button = new JButton("Draw");
              button.addActionListener( new ActionListener()
              {
                 public void actionPerformed(ActionEvent event)
                 {
                     String x = JOptionPane.showInputDialog("X Coordinate", "Enter an x coordinate");
                     int xCoord = Integer.parseInt(x);
                     String y = JOptionPane.showInputDialog("Y Coordinate", "Enter a y coordinate");
                     int yCoord = Integer.parseInt(y);
                     String width = JOptionPane.showInputDialog("Radius", "Enter the length of the radius");
                     int radius = Integer.parseInt(width);
                     CircleComponent component = new CircleComponent(radius);
                     component.setLocation(xCoord,yCoord);
                     component.setSize(component.getPreferredSize());
                     center.add(component);
                     center.repaint();

                 }
              });

              JFrame frame = new JFrame();
              frame.add(center, BorderLayout.CENTER);
              frame.add(button, BorderLayout.NORTH);
              frame.setSize(500, 500);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
    }
}