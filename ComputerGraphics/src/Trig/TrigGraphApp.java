package Trig;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrigGraphApp
        extends JFrame implements ActionListener
{
    //Notice we've given these two objects a larger scope.
//Local scope to the constructor was no longer sufficient.
    TrigGLEventListener listener = new TrigGLEventListener();
    GLCanvas glcanvas;
    public static void main(String[] args) {
        final TrigGraphApp app = new TrigGraphApp();
// show what we've done
        SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        app.setVisible(true);
                    }
                }
        );
    }
    public TrigGraphApp() {
//set the JFrame title
        super("Sine, Cosine and Tangent");
//kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Setting up our southern JPanel with JRadioButtons
        JPanel jp = new JPanel();
        ButtonGroup bg = new ButtonGroup();
//setting up the sine JRadioButton

        JRadioButton jrb1 = new JRadioButton("Sine", true);
        jrb1.setActionCommand("sine");
        jrb1.addActionListener(this);
//setting up the cosine JRadioButton
        JRadioButton jrb2 = new JRadioButton("Cosine");
        jrb2.setActionCommand("cosine");
        jrb2.addActionListener(this);
//setting up the tangent JRadioButton
        JRadioButton jrb3 = new JRadioButton("Tangent");
        jrb3.setActionCommand("tangent");
        jrb3.addActionListener(this);
//adding the buttons to the ButtonGroup
        bg.add( jrb1 );
        bg.add( jrb2 );
        bg.add( jrb3 );
//adding the buttons to the JPanel
        jp.add( jrb1 );
        jp.add( jrb2 );
        jp.add( jrb3 );
        getContentPane().add("South", jp);
//only three JOGL lines of code ... and here they are
        glcanvas =new GLCanvas();
        glcanvas.addGLEventListener(listener);
//add the GLCanvas just like we would any Component
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(500, 300);
//center the JFrame on the screen
        centerWindow(this);
    }
    public void centerWindow(Component frame) {
        Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width )
            frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        frame.setLocation (
                (screenSize.width - frameSize.width ) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }

    /**
     * Implementation of our ActionListener. This allows the
     * buttons to perform an action. In this case they set
     * the "whatToDraw" String and ask for a repaint of the
     * GLCanvas.
     */
    public void actionPerformed(ActionEvent ae) {
        listener.whatToDraw = ae.getActionCommand();
        glcanvas.repaint();
    }
}
