package LineGraph;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineGraphApp extends JFrame implements    ActionListener {
    //Notice we've given these two objects a larger scope.
//Local scope to the constructor was no longer sufficient.
    LineGLEventListener listener = new LineGLEventListener();
    GLCanvas glcanvas;
    JTextField ajtf = new JTextField("3", 3);
    JTextField bjtf = new JTextField("2", 3);
    JTextField mjtf = new JTextField("-1", 6);
    public static void main(String[] args) {
        final LineGraphApp app = new LineGraphApp();
// show what we've done
        SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        app.setVisible(true);
                    }
                }
        );
    }
    public LineGraphApp() {
//set the JFrame title
        super("Paint.Point-Slope Calculation");
//kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Setting up our southern JPanel
        JPanel jp = new JPanel();
//adding the JTextFields and JLabels
        jp.add(new JLabel("x:"));
        jp.add(ajtf);
        jp.add(new JLabel(" y:"));
        jp.add(bjtf);
        jp.add(new JLabel(" slope: "));
        jp.add(mjtf);

//adding the JButton
        JButton jb = new JButton("Redraw");
        jb.addActionListener(this);
        jp.add(jb);
        getContentPane().add("South", jp);
//only three JOGL lines of code ... and here they are
        GLCapabilities glcaps = new GLCapabilities();
        glcanvas = new GLCanvas();
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
        listener.a = Double.parseDouble( ajtf.getText() );
        listener.b = Double.parseDouble( bjtf.getText() );
        listener.m = Double.parseDouble( mjtf.getText() );
        glcanvas.repaint();
    }
}