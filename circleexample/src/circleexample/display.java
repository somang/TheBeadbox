package circleexample;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.*;
import java.awt.Canvas;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;                    

class DisplayCanvas extends Canvas
{
    static boolean clip = false;
    static boolean clipfurther = false;
    static boolean  isReset = false, isSlider = false;
    int w, h;
    static int wi = 300, hi = 300;
    public DisplayCanvas()
    {
        //setSize(300, 300);
    setBounds(20, 40, 300, 300);
    setBackground(Color.white);
    }
    public void paint(Graphics g)
    {
        Graphics2D g2=(Graphics2D) g;
    w = getSize().width;
    h = getSize().height;

    if(clip)
    {
        // Ellipse2D e = new Ellipse2D.Float(w/4.0f,h/4.0f,w/2.0f,h/2.0f);
        Rectangle2D r = null;
        if(isSlider)
        {
            r = new Rectangle2D.Float((w)/(4.0f), (h)/(4.0f), wi/2.0f, hi/2.0f);
        //     isSlider=false;
        }
        else
        r = new Rectangle2D.Float(w/4.0f, h/4.0f, wi/2.0f, hi/2.0f);
            //g2.setClip(e);

        System.out.println("Width : "+ wi +" Height : "+ hi);
        g2.setClip(r);
        System.out.println(g2.getClipBounds());

        if(shape.selectedcolor == "red")
        {
            g2.setColor(Color.red);
        }
        else if(shape.selectedcolor == "blue")
        {
            g2.setColor(Color.BLUE);
        }
        else if(shape.selectedcolor == "yellow")
        {
            g2.setColor(Color.yellow);
        }

        //g2.setColor(Color.RED);
        g2.fillRect(0, 0, w, h);
        }
    else if(clipfurther)
    {
        //Rectangle r = new Rectangle(w/4, h/4, w/4, h/4);
        Ellipse2D r = new Ellipse2D.Float(w/4.0f, h/4.0f, wi/2.0f, hi/2.0f);
        g2.clip(r);
        //g2.setColor(Color.green);
        //g2.setColor(Color.getColor(shape.selectedcolor));

        if(shape.selectedcolor == "red")
        {
            g2.setColor(Color.red);
        }
        else if(shape.selectedcolor == "blue")
        {
        g2.setColor(Color.BLUE);
        }
        else if(shape.selectedcolor == "yellow")
        {
        g2.setColor(Color.yellow);
        }

        g2.fillRect(0, 0, w, h);
        }
    }   
}

class RadioListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == shape.circle)
        {
        shape.selectedcolor = shape.selectedcolor_reset;
        System.out.println("Inside circle "+            shape.selectedcolor_reset);
        System.out.println("2222222222");
        DisplayCanvas.clip = true;
        DisplayCanvas.clipfurther = false;
        shape.canvas.repaint();
    }
    if (e.getSource() == shape.square)
    {
        shape.selectedcolor = shape.selectedcolor_reset;
        System.out.println("333333333333333");
        DisplayCanvas.clip = false;
        DisplayCanvas.clipfurther = true;
        shape.canvas.repaint();
    }                  
    }
}

class shape extends JFrame implements ActionListener
{
    static String fname;
    static JPanel panel, draw;
    static Container contentpane, draw_pane;
    static JFrame frame;
    static JLayeredPane layer, draw_layer;
    static JTextField user;
    static JButton reset, cancel;

    static JLabel file1, file2;
    static JTextField text1;
    static JRadioButton circle, square;
    static JInternalFrame iframe, errmessage;
    static JComboBox colors;
    static JEditorPane sqc;
    static JScrollPane scroller;
    static JSlider slider;
    String colors_array[]={"...", "red", "blue", "yellow"};
    static DisplayCanvas canvas;
    static String selectedcolor, selectedcolor_reset;

    shape()
    {
        canvas=new DisplayCanvas();

    panel= new JPanel();
    panel.setBounds(20,20,250,140);
    //panel.setLayout(new FlowLayout(10, 10, 10));
    panel.setLayout(null);
    contentpane = getContentPane();
    contentpane.add(canvas);
    //draw_pane=getContentPane();

    layer = new JLayeredPane();
    layer.setBounds(50,245,300,205);
    layer.setForeground(Color.blue);

    draw_layer = new JLayeredPane();
    draw_layer.setBounds(200, 200, 80, 30);
    draw_layer.setForeground(Color.BLACK);

    draw = new JPanel();
    draw.setBounds(20, 20, 250, 300);
    draw.setLayout(null);

    reset = new JButton("RESET");
    reset.setBounds(360, 60, 100, 25);

    circle = new JRadioButton("Square");
    circle.setBounds(400, 100, 100, 40);
    circle.setActionCommand("encryption");

    square = new JRadioButton("Circle");
    square.setBounds(330,100, 60, 40);
    square.setActionCommand("decryption");

    colors = new JComboBox(colors_array);
    colors.setBounds(405, 140, 80, 30);
    colors.setVisible(true);

    file1 = new JLabel(" Select Color :");
    file1.setBounds(320, 130, 150, 50);
    //defining the Button Group for the Radio Buttons

    ButtonGroup group = new ButtonGroup();
    group.add(circle);
    group.add(square);

    cancel = new JButton("Exit");
    cancel.setBounds(365, 250, 85, 25);

    file2 = new JLabel("SIZE :");
    file2.setBounds(336, 180, 150, 50);
    setslider(0, 100, 100, 25, 5);
    slider.setBounds(386, 190, 100, 50);

        slider.setVisible(true);

    panel.add(file2);
    panel.add(layer);
        panel.add(circle);
    panel.add(square);
    panel.add(colors);
    panel.add(slider);
    panel.add(file1);
    panel.add(reset);
    panel.add(cancel);
    //panel.add(new CircleDraw());

    //draw.add(draw_layer);
    // draw_pane.add(draw);
    contentpane.add(panel);
    //   contentpane.add(draw);
    //  contentpane.add(scroller);
    RadioListener radio = new RadioListener();
    circle.addActionListener(radio);
    square.addActionListener(radio);
    reset.addActionListener(this);
    cancel.addActionListener(this);

    colors.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {                           
            JComboBox cb = (JComboBox)ae.getSource();
        selectedcolor = (String)cb.getSelectedItem();
        System.out.println(selectedcolor);
        selectedcolor_reset = selectedcolor;
        System.out.println("SELECTED "+ cb.getSelectedIndex());
        canvas.repaint();
        }
        });

    addWindowListener(new WindowAdapter()
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    });
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()== cancel)
    {
        System.exit(0);
    }
    if (e.getSource() == reset)
    {
        System.out.println("111111111");
        DisplayCanvas.clip = true;
        DisplayCanvas.clipfurther = false;
        DisplayCanvas.isReset = true;
        shape.selectedcolor = "...";
        System.out.println(" PREVIOUS " + selectedcolor_reset);
        DisplayCanvas.hi = DisplayCanvas.wi = 300;
        canvas.repaint();
    }
    }
    public class SliderListener implements ChangeListener 
    {
        public SliderListener() 
        {
    }
    public void stateChanged(ChangeEvent e) 
        {
        JSlider slider = (JSlider)e.getSource();
        System.out.println(slider.getValue());
        DisplayCanvas.wi = slider.getValue() * 3;
        DisplayCanvas.hi = DisplayCanvas.wi;
        DisplayCanvas.isSlider = true;
        canvas.repaint();
    }
    }
    public void setslider(int min,int max,int init,int mjrtk,int mintk)
    {
        slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
    slider.setPaintTicks(true);
    slider.setMajorTickSpacing(mjrtk);
    slider.setMinorTickSpacing(mintk);
    slider.setPaintLabels(true);
    slider.addChangeListener((ChangeListener) new SliderListener());
    }
}

public class display 
{
    static JFrame frame;
    /**
      * @param args the command line arguments
      */
    public static void main(String[] args) 
    {
        // TODO code application logic here
    frame=new shape();

    frame.setBounds(380, 200, 500, 400);
    frame.setTitle("SHAPE AND COLOR");
    frame.setVisible(true);

    }
}