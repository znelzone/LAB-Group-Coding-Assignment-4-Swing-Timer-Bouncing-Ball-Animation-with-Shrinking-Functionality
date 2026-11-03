import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;


/**
 * Represents the bouncing ball displayed in the animation.
 * 
 * @author Zackary Nelson
 * @version 11-2-2026
 * @since 11-2-2026
 */
public class Ball {
    private Point center;
    private int radius;
    private int dx;
    private int dy;

    private ImageIcon ballPNG;


    /**
     * Consturcts a Ball object with a center position, radius,
     * and horizontal and vertical movement speed.
     * 
     * @param center the initial center position of the ball
     * @param radius the initial radius of the ball
     * @param dx    the horizontal movement speed of the ball
     * @param dy    the vertical movement speed of the ball
     */
    public Ball(Point center, int radius, int dx, int dy) {
        this.center = new Point(center);
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;

        ballPNG = new ImageIcon("BouncingBall.png");
    }


    /**
     * Gets the current center position of the ball.
     * 
     * @return the current center position of the ball
     */
    public Point getCenter() {
        return new Point(center);
    }

    /**
     * Sets the center position of the ball.
     * 
     * @param center the new center position of the ball
     */
    public void setCenter(Point center){
        this.center = new Point(center);
    }

    /**
     * Gets the current radius of the ball.
     * 
     * @return the current radius of the ball
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the ball.
     * 
     * @param radius the new radius of the ball
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Gets the current vertical movement of the ball.
     * 
     * @return the current vertical movement of the ball
     */
    public int getDy() {
        return dy;
    }

    /**
     * Sets the vertical movement of the ball.
     * 
     * @param dy the vertical movement of the ball
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * Gets the current horizontal movement of the ball.
     * 
     * @return the current horizontal movement of the ball
     */
    public int getDx() {
        return dx;
    }

    /**
     * Sets the horizontal movement of the ball.
     * 
     * @param dx the horizontal movement of the ball
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * Updates the ball's center position by adding it's horizontal
     * and vertical movement speeds to its current coordinates. 
     */
    public void update() {
        this.center.setLocation(
            this.center.x + dx,
            this.center.y + dy
        );
    }

    /**
     * Draws the ball image onto a circular shape, placing
     * the PNG image onto the balls center coords scaling
     * to the balls radius.
     * 
     * @param g graphics
     */
    public void draw(Graphics g) {

        g.drawImage(ballPNG.getImage(),
        center.x - radius,
        center.y - radius,
        radius * 2,
        radius * 2,
        null
    );
    }
}
