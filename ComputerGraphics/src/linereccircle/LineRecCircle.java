package linereccircle;

import paint.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

public class LineRecCircle implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
    //Points points= new Points();


    GLCanvas glc;
    float red, green, blue;
    double startx, starty, endx, endy,radius;

    final double ONE_DEGREE = (Math.PI/180);
    final double THREE_SIXTY = 2 * Math.PI;

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
        gl.glColor3f(red+0.2f, green+0.1f, blue);
// angle is
// x = radius * (cosine of angle)
// y = radius * (sine of angle)
        gl.glPointSize(10.0f);

        gl.glBegin(GL.GL_LINES);
//diagonal
        gl.glVertex2d(startx, starty);
        gl.glVertex2d(endx, endy);
        //rectangle
        gl.glVertex2d(startx, starty);
        gl.glVertex2d(startx, endy);

        gl.glVertex2d(startx, endy);
        gl.glVertex2d(endx, endy);

        gl.glVertex2d(startx, starty);
        gl.glVertex2d(endx, starty);

        gl.glVertex2d(endx, starty);
        gl.glVertex2d(endx, endy);

        //circle

        gl.glColor3f(red+0.9f, green, blue);
        gl.glPointSize(10.0f);

        gl.glBegin(GL.GL_POLYGON);
// angle is
// x = radius * (cosine of angle)
// y = radius * (sine of angle)
        double x,y;
        // radius = 0.5 *Math.sqrt(Math.pow(endx-startx,2)+Math.pow(endy-starty,2))-10;
        radius=Math.abs(endx-startx)>=Math.abs(endy-starty)?0.5*Math.abs(endy-starty):0.5*Math.abs(endx-startx);
        double centerX=(startx+endx)/2.0,centerY=(starty+endy)/2.0;
        System.out.println("startx "+startx+" starty "+starty+" endx "+endx+" endy" +endy);

        for (double a=0,i=0; a<THREE_SIXTY; a+=ONE_DEGREE/10,i+=0.01) {
            x = radius * Math.cos(i);
            y = radius * Math.sin(i);
            if(true){
                System.out.println("x is " +x+" y is "+y);
                gl.glVertex2d(centerX-x, centerY-y);

            }

        }



        gl.glEnd();



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
        double x = e.getX();
        double y = e.getY();
        double xPosition, yPosition;

        Component component = e.getComponent();
        double width = component.getWidth();
        double height = component.getHeight();

        xPosition = (int) ((x / width) * 100);
        yPosition = ((int) ((y / height) * 100));
        yPosition = 100 - yPosition;
        startx=xPosition;
        starty=yPosition;
        glc.repaint();
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
    public void mouseDragged(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        double xPosition, yPosition;

        Component component = e.getComponent();
        double width = component.getWidth();
        double height = component.getHeight();

        xPosition = (int) ((x / width) * 100);
        yPosition = ((int) ((y / height) * 100));
        yPosition = 100 - yPosition;

        endx = xPosition;
        endy = yPosition;


        glc.repaint();


    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }

public void getPosition(MouseEvent e){}
}