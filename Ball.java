import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ball {
    private Point center;
    private int radius;
    private int dx;
    private int dy;

    public Ball(Point center, int radius, int dx, int dy) {
        this.center = new Point(center);
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
    }


    public Point getCenter() {
        return new Point(center);
    }

    public void setCenter(Point center){
        this.center = new Point(center);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void update() {
        this.center.setLocation(
            this.center.x + dx,
            this.center.y + dy
        );
    }

    public void draw(Graphics g) {
        //gives color RED
        g.setColor(Color.RED);

        g.fillOval(
            //
            center.x - radius,
            //
            center.y - radius,
            //
            radius * 2,
            //
            radius * 2
        );
    }
}
