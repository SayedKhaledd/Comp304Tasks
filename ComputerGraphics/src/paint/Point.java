package paint;

public class Point {

   float red, blue, green;
   double x,y;
   boolean stop;

    public Point(float red, float blue, float green, double x, double y, boolean stop) {
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.x = x;
        this.y = y;
        this.stop = stop;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
