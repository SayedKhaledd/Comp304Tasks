package CircleStar;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CircleStar implements GLEventListener, MouseMotionListener {
    final double ONE_DEGREE = (Math.PI / 180);
    final double THREE_SIXTY = 2 * Math.PI;
    /**
     * Take care of initialization here.
     */


GLCanvas glc=null;

    double xPosition=10000, yPosition=100100;
    double radius = 100;
    public void setGlc(GLCanvas glc) {
        this.glc = glc;
    }

    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-250.0, 250.0, -150.0, 150.0, -1, 1);
    }

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
float red,blue,green;
        drawCircle(gl);
        if(Math.sqrt(Math.pow(xPosition,2)+Math.pow(yPosition,2))<radius){
            System.out.println("yes");
            red=0;
            blue=0;
            green=0;

        }
        else{
             red = 0.5f;
             green = 0.0f;
             blue = 0.5f;
            System.out.println("no");
        }

        drawStar(gl,  red,  blue,  green);
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

    public void drawCircle(GL gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(2.0f);
        gl.glColor3f(0.2f, 0.1f, 0.1f);
        gl.glBegin(GL.GL_POINTS);
//THE CIRCLE
        double x, y;

        for (double a = 0; a < THREE_SIXTY; a += 0.001) {
            x = radius * (Math.cos(a));
            y = radius * (Math.sin(a));
            gl.glVertex2d(x, y);
        }

        gl.glEnd();


    }
    public void drawStar(GL gl,float red,float green,float blue){
        //DRAW Star

        double x, y;

        double[][] arr=new double[5][2];
        for(int i=0,k=0;i<=360&&k<5;i+=360/5,k++){
            x=radius*Math.cos(Math.toRadians(i));
            y=radius*Math.sin(Math.toRadians(i));
            arr[k][0]=x;
            arr[k][1]=y;

        }

        double x2,y2;
        gl.glColor3f(red, green, blue);
        gl.glBegin(GL.GL_LINES);


        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(i!=j && (j!=i+1) && (j!=i-1) && i+arr.length-1!=j && j+arr.length-1!=i){
                    x=arr[i][0];
                    y=arr[i][1];
                    x2=arr[j][0];
                    y2=arr[j][1];
                    gl.glVertex2d(x, y);
                    gl.glVertex2d(x2, y2);

                }
            }
        }
        gl.glEnd();
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();


        Component component = e.getComponent();
        double width = component.getWidth();
        double height = component.getHeight();


        xPosition = (int) ((x / width) * 500)-250;
        yPosition = -(((int) ((y / height) * 300))-150);
        // yPosition = 100 - yPosition;
        System.out.println("x is "+x+"y is "+y);
        System.out.println("real x " + xPosition + " real y " + yPosition);


        glc.repaint();

    }
}