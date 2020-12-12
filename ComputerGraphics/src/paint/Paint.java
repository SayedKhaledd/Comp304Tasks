package paint;

import javax.media.opengl.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

public class Paint implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
    //Points points= new Points();

    ArrayList<Point> points = new ArrayList<>();
    Stack<Point> undo = new Stack<>();
    int counter100 = 0;

    GLCanvas glc;
    float red, green, blue;

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        red = 0.0f;
        green = 0.0f;
        blue = 0.0f;
        gl.glClearColor(red, green, blue, 0.0f);
        gl.glViewport(0, 0, 100, 100);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 100, 0, 100, -1, 1);
    }

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(5.0f);
        gl.glColor3f(red, green, blue);
// angle is
// x = radius * (cosine of angle)
// y = radius * (sine of angle)
        System.out.println("size is " + points.size());
        gl.glPointSize(3.0f);
        for (int i = 0; i < points.size(); i++) {

            gl.glColor3f(points.get(i).getRed(), points.get(i).getGreen(), points.get(i).getBlue());
            if (!points.get(i).isStop() && i + 1 < points.size()) {
                /**using only the 2 points
                 * */
  /*              gl.glBegin(GL.GL_LINES);
                gl.glVertex2d(points.get(i).getX(), points.get(i).getY());
                gl.glVertex2d(points.get(i+1).getX(), points.get(i+1).getY());
                gl.glEnd();
*/
                /**using more points
                 * */
                double point1x = points.get(i).getX(), point1y = points.get(i).getY();
                double point2x = points.get(i + 1).getX(), point2y = points.get(i + 1).getY();
                double x = point2x - point1x, y = point2y - point1y;
                double j = point1x, k = point1y;
                int counter = 0;
                while (counter != 10) {
                    gl.glBegin(GL.GL_LINES);
                    System.out.println(j + " " + k);

                    gl.glVertex2d(j, k);
                    j += x / 10.0;
                    k += y / 10.0;
                    gl.glVertex2d(j, k);
                    gl.glEnd();
                    counter++;

                }

            }


        }

    }

    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height
    ) {
    }

    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged
    ) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    public void setGlc(GLCanvas glc) {
        this.glc = glc;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
        points.get(points.size() - 1).setStop(true);
        glc.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        double xPosition, yPosition;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Component component = e.getComponent();
        double width = component.getWidth();
        double height = component.getHeight();
        System.out.println("fake x " + x + " fake y " + y);

        System.out.println("width " + width + " height " + height);
        System.out.println("some x " + component.getX() + " some y " + component.getY());

        xPosition = (int) ((x / width) * 100);
        yPosition = ((int) ((y / height) * 100));
        yPosition = 100 - yPosition;
        System.out.println("real x " + xPosition + " real y " + yPosition);


        Point p = new Point(red, blue, green, xPosition, yPosition, false);
        points.add(p);

        counter100++;


        glc.repaint();


    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }
}