package Clock;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

public class Clock implements GLEventListener {
    final double ONE_DEGREE = (Math.PI / 180);
    final double THREE_SIXTY = 2 * Math.PI;
    /**
     * Take care of initialization here.
     */
    float red = 0.5f;
    float green = 0.0f;
    float blue = 0.5f;

GLCanvas glc=null;
int degree=0;
double newx=-5,newy=0;

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

        drawClock(gl);
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

    public void drawClock(GL gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(2.0f);
        gl.glColor3f(red, green, blue);
        gl.glBegin(GL.GL_POINTS);
//THE CIRCLE
        double x, y;
        double radius = 100;
        for (double a = 0; a < THREE_SIXTY; a += 0.001) {
            x = radius * (Math.cos(a));
            y = radius * (Math.sin(a));
            gl.glVertex2d(x, y);
        }

        gl.glEnd();

        //DRAW LINES
        for (int i = 0, a = 0; i <= 12; i++, a += 30) {
            double x1, x2, y1, y2;
            gl.glColor3f(red, green, blue);

            gl.glBegin(GL.GL_LINES);
            x1 = (radius - 20) * Math.cos(Math.toRadians(a));
            y1 = (radius - 20) * Math.sin(Math.toRadians(a));
            x2 = (radius - 5) * Math.cos(Math.toRadians(a));
            y2 = (radius - 5) * Math.sin(Math.toRadians(a));
            gl.glVertex2d(x2, y2);
            gl.glVertex2d(x1, y1);

            System.out.println("line number " + i + " at coordinates Xs " + x2 + " and " + x1 + " and at coordinates Ys" + y2 + " and " + y1);
            gl.glEnd();
        }

        //DRAW 3AKRAB
        gl.glColor3f(red + 0.4f, green + 0.23f, blue);

        double x1 = (radius - 10) * Math.cos(Math.toRadians(90+degree*6));
        double y1 = (radius - 10) * Math.sin(Math.toRadians(90+degree*6));

        gl.glBegin(GL.GL_TRIANGLES);


  newx = (5) * Math.cos(Math.toRadians(180+degree*6));
 newy = (5) * Math.sin(Math.toRadians(180+degree*6));

        gl.glVertex2d(newx,newy);
        gl.glVertex2d(0, 0);

        gl.glVertex2d(x1, y1);
        gl.glEnd();

    }


}