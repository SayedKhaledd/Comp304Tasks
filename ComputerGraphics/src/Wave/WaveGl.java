package Wave;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.time.Duration;
import java.time.LocalTime;

public class WaveGl implements GLEventListener {
    final double ONE_DEGREE = (Math.PI / 180);
    final double THREE_SIXTY = 2 * Math.PI;


    int frames = 0;
    //Remember to use floats for calculating slope
//of the line the ball follows. Ints will be
//far too imprecise (i.e. (8/9) == 0).
//
//Slope will change on each wall impact.
//It will be multiplied by -1.
    float slope = 7.0f / 6.0f;

    boolean movingRight = true;
    boolean movingUp = true;
    private LocalTime startTime;
    private long elapsedSeconds;
    private int elapsedNanos;
    long starttt;
    /**
     * Take care of initialization here.
     */
    float red = 1.f;
    float green = 0.0f;
    float blue = 0.0f;
    float radius = 10;
    int check = 1;
    float centerx = 0, centery = 0, factor = 100;
    float xIncrement = 15;
    double angle = 0;
    float freq = 1;

    public void init(GLAutoDrawable gld) {
        starttt = System.currentTimeMillis();
        GL gl = gld.getGL();
        startTime = LocalTime.now();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0, 0, 500, 500);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 500, 0, 500, -1, 1);
    }

    /**
     * Take care of drawing here.
     */


    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();


        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(2.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glBegin(GL.GL_POINTS);


        double x, y;

        float centerx = 0, centery;
        int n = (int) (500 / xIncrement);
        if (System.currentTimeMillis() - starttt >= 3000) {

            freq += 0.1;
            //n+=30;
        }
        for (int i = 0, j = 0; i <= n; i++, j += xIncrement) {

            angle = Math.toRadians(centerx + 90);

            if (check % 3 == 1) {
                red = 1;
                green = 0;
                blue = 0;
                check++;
            } else if (check % 3 == 2) {
                red = 0;
                green = 1;
                blue = 0;
                check++;
            } else {
                red = 0;
                green = 0;
                blue = 1;
                check = 1;

            }
            gl.glColor3f(red, green, blue);

            centerx += xIncrement;
            centery = 100 + factor * (float) Math.sin(freq * angle);
            gl.glVertex2f(centerx, centery);
            gl.glVertex2f(centerx, 300+centery);

            System.out.println("other : " + centerx + " " + centery);
            for (double a = 0; a < THREE_SIXTY; a += ONE_DEGREE) {
                x = centerx + radius * (Math.cos(a));
                y = centery + radius * (Math.sin(a));
                gl.glVertex2d(x, y);
            }
            for (double a = 0; a < THREE_SIXTY; a += ONE_DEGREE) {
                x = centerx + radius * (Math.cos(a));
                y = centery+300 + radius * (Math.sin(a));
                gl.glVertex2d(x, y);
            }

        }
        gl.glEnd();


    }

    public void drawNCircles(GL gl, int n) {


    }

    public void drawCircle(GL gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(2.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(red, green, blue);

        gl.glBegin(GL.GL_POINTS);


        double x, y;
        gl.glVertex2f(centerx, centery);

        for (double a = 0; a < THREE_SIXTY; a += ONE_DEGREE) {
            x = centerx + radius * (Math.cos(a));
            y = centery + radius * (Math.sin(a));
            gl.glVertex2d(x, y);
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

    public float getDeltaTime() {
        Duration timeSinceStart = Duration.between(startTime, LocalTime.now());
        int deltaTime = timeSinceStart.minusSeconds(elapsedSeconds).getNano() - elapsedNanos;
        if (timeSinceStart.getSeconds() > elapsedSeconds) {
            //     System.out.println(frames + " " + elapsedSeconds);
            frames = 0;
            elapsedSeconds = timeSinceStart.getSeconds();
        } else {
            frames++;
        }
        elapsedNanos = elapsedNanos + deltaTime;
        //   System.out.println(elapsedNanos + " - " + deltaTime);
        return deltaTime < 0 ? 0 : deltaTime / 1000000000f;
    }
}