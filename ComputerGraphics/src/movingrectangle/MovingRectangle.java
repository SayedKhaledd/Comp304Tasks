package movingrectangle;

import paint.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.*;

public class MovingRectangle implements GLEventListener, MouseMotionListener, MouseListener, KeyListener

{
    final double ONE_DEGREE = (Math.PI/180);
    final double THREE_SIXTY = 2 * Math.PI;
    GLCanvas glc;
double xplus,yplus,yviewport,xviewport;
    public void setGlc(GLCanvas glc) {
        this.glc = glc;
    }

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-250.0, 250.0, -150.0, 150.0,-1,1);
    }
    /**
     * Take care of drawing here.
     */
    double x = 100, y = 0, z = 0, angle = 0;
    float red = 0.5f;
    float green = 0.0f;
    float blue = 0.5f;
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(5.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(red, green, blue);
    //DRAWING PLUS SIGN
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(xplus-5,yplus);
        gl.glVertex2d(xplus+5,yplus);
        gl.glVertex2d(xplus,yplus-5);
        gl.glVertex2d(xplus,yplus+5);
gl.glEnd();


        gl.glPushMatrix();
        gl.glRotated(angle, 0, 0, 1);

        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2d(0,0);
        gl.glVertex2d(0,50);
        gl.glVertex2d(50,50);
        gl.glVertex2d(50,0);


// angle is
// x = radius * (cosine of angle)
// y = radius * (sine of angle)

        gl.glEnd();
        gl.glPopMatrix();
    }
    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height
    ) {}
    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged
    ) {}


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        double xPosition, yPosition;

        Component component = e.getComponent();
        double width = component.getWidth();
        double height = component.getHeight();


        xPosition = (int) ((x / width) * 500)-250;
        yPosition = -(((int) ((y / height) * 300))-150);
       // yPosition = 100 - yPosition;
        System.out.println("x is "+x+"y is "+y);
        System.out.println("real x " + xPosition + " real y " + yPosition);

    xplus=xPosition;
    yplus=yPosition;
        System.out.println("xplus is "+xplus+" yplus is "+yplus);
        glc.repaint();


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        red=(float) Math.random();
        green=(float) Math.random();
        blue=(float) Math.random();
glc.repaint();



    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
if(e.getKeyCode()==KeyEvent.VK_UP){
    angle+=5;
}
else if(e.getKeyCode()==KeyEvent.VK_DOWN){
    angle-=5;
}
glc.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}