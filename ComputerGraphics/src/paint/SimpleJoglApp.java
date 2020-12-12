package paint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;

/**
 * This is a basic JOGL app. Feel free to
 * reuse this code or modify it.
 */
public class SimpleJoglApp extends JFrame {

    /**
     *
     */

    static GLCanvas glcanvas = null;
    JButton clear = new JButton("Clear");
    JButton undo = new JButton("Undo");
    JButton redo = new JButton("Redo");
    JButton color = new JButton("Color");

    Paint kd = new paint.Paint();

    public static void main(String[] args) {
        final SimpleJoglApp app = new SimpleJoglApp();

        // show what we've done
/*    SwingUtilities.invokeLater (
      new Runnable() {
        public void run() {
          app.setVisible(true);
        }
      }
    );*/
    }

    public SimpleJoglApp() {
        //set the JFrame title
        super("Simple JOGL Application");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //only three JOGL lines of code ... and here they are
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(kd);
        glcanvas.addKeyListener(kd);
        glcanvas.addMouseListener(kd);
        glcanvas.addMouseMotionListener(kd);
        kd.setGlc(glcanvas);
        // add the GLCanvas just like we would	 any Component
        JPanel jp = new JPanel();
        jp.add(clear);
        jp.add(redo);
        jp.add(undo);
        jp.add(color);
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<10;i++)

                    if(!kd.undo.isEmpty()){
                kd.points.add(kd.undo.pop());
                glcanvas.repaint();
                }
            }
        });
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<10;i++)
                if(!kd.points.isEmpty()){
               kd.undo.push( kd.points.remove(kd.points.size()-1));
               glcanvas.repaint();
                }
            }
        });
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", null);
                float red = newColor.getRed();
                float green = newColor.getGreen();
                float blue = newColor.getBlue();
                System.out.println("red is " + red + " green is " + green + " blue is " + blue);
                kd.red = (float) (red / 256.0);
                kd.blue = (float) (blue / 256.0);
                kd.green = (float) (green / 256.0);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kd.points = new ArrayList<>();
                glcanvas.repaint();


            }
        });
        add(glcanvas, BorderLayout.CENTER);
        add(jp, BorderLayout.SOUTH);
        setSize(500, 300);

        //center the JFrame on the screen
        centerWindow();
        setVisible(true);
    }

    public void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        if (frameSize.width > screenSize.width) frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height) frameSize.height = screenSize.height;

        this.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }


}

/**
 * For our purposes only two of the
 * GLEventListeners matter. Those would
 * be init() and display().
 */
