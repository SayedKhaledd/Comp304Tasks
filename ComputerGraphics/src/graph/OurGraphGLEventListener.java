package graph;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class OurGraphGLEventListener implements GLEventListener {

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        //GLU glu = gld.getGLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glLineWidth(2.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-250.0, 250.0, -150.0, 150.0,-1,1);
    }

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {

GL gl=drawable.getGL();
 float red=0.2f;
 float green=0.2f;
 float blue=0.2f;

 gl.glClear(GL.GL_COLOR_BUFFER_BIT);
 gl.glColor3f(red,green,blue);
   gl.glBegin(GL.GL_LINES);
   //DRAWING vertical lines
for(int i=-250;i<=250;i+=10){
    gl.glVertex2d(i,-150);
    gl.glVertex2d(i,150);

}
//drawing horizontal lines
        for(int i=-150;i<=150;i+=10){
            gl.glVertex2d(-250,i);
            gl.glVertex2d(250,i);

        }

gl.glEnd();

red=0.0f; green=0.2f;blue=0.4f;
gl.glColor3f(red,green,blue);
gl.glBegin(GL.GL_LINES);
gl.glVertex2d(-250,0);
gl.glVertex2d(250,0);

        gl.glVertex2d(0,-150);
        gl.glVertex2d(0,150);
gl.glEnd();
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d( 0, 150);
        gl.glVertex2d(-5, 140);
        gl.glVertex2d( 5, 140);
        gl.glVertex2d( 0, -150);
        gl.glVertex2d(-5, -140);
        gl.glVertex2d( 5, -140);
        gl.glVertex2d(250, 0);
        gl.glVertex2d(240,-5);
        gl.glVertex2d(240, 5);
        gl.glVertex2d(-250, 0);
        gl.glVertex2d(-240,-5);
        gl.glVertex2d(-240, 5);
        gl.glEnd();

    }

    /**
     * Called when the GLDrawable (GLCanvas
     * or GLJPanel) has changed in size. We
     * won't need this, but you may eventually
     * need it -- just not yet.
     */
    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height
    ) {}

    /**
     * If the display depth is changed while the
     * program is running this method is called.
     * Nowadays this doesn't happen much, unless
     * a programmer has his program do it.
     */
    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged
    ) {}

    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub

    }

}

