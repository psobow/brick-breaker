package sobow.brick.breaker.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import sobow.brick.breaker.settings.WindowSettings;

public class Ball
{
    private static final Color COLOR = Color.CYAN.darker().darker();

    private static final int DIAMETER = 20;
    private static final int INIT_X_POS_TOP_LEFT_CORNER =
            WindowSettings.WIDTH / 2 - DIAMETER / 2; // center the ball horizontally
    private static final int INIT_Y_POS_TOP_LEFT_CORNER = 450;

    private static final int INIT_X_AXIS_MOTION_FACTOR = -3;
    private static final int INIT_Y_AXIS_MOTION_FACTOR = -3;

    private static Ball instance;

    private Random random = new Random();
    private Rectangle bounds = new Rectangle();

    private int xPosLeftTopCorner;
    private int yPosLeftTopCorner;
    private int diameter;

    private int yAxisMotionFactor;
    private int xAxisMotionFactor;

    private boolean isTouchingBottom;

    public static Ball getInstance()
    {
        if (instance == null)
        {
            synchronized (Ball.class)
            {
                if (instance == null)
                {
                    instance = new Ball();
                }
            }
        }
        synchronized (Ball.class)
        {
            return instance;
        }
    }

    private Ball()
    {
        reset();
    }

    public void reset()
    {
        xPosLeftTopCorner = INIT_X_POS_TOP_LEFT_CORNER;
        yPosLeftTopCorner = INIT_Y_POS_TOP_LEFT_CORNER;
        diameter = DIAMETER;
        yAxisMotionFactor = INIT_Y_AXIS_MOTION_FACTOR;
        xAxisMotionFactor = INIT_X_AXIS_MOTION_FACTOR;
        isTouchingBottom = false;

        // Ball will randomly fly to left or right side
        if (random.nextInt(10) % 2 == 0)
        {
            revertMotionXAxis();
        }
    }

    public void paint(Graphics g)
    {
        g.setColor(COLOR);
        g.fillOval(xPosLeftTopCorner, yPosLeftTopCorner, diameter, diameter);
    }

    public void update()
    {
        move();
    }

    private void move()
    {
        xPosLeftTopCorner += xAxisMotionFactor;
        yPosLeftTopCorner += yAxisMotionFactor;
    }

    public void revertMotionXAxis()
    {
        xAxisMotionFactor *= -1;
    }

    public void revertMotionYAxis()
    {
        yAxisMotionFactor *= -1;
    }

    public Rectangle getBounds()
    {
        bounds.x = xPosLeftTopCorner;
        bounds.y = yPosLeftTopCorner;
        bounds.width = bounds.height = diameter;
        return bounds;
    }

    public int getXPosLeftTopCorner()
    {
        return xPosLeftTopCorner;
    }

    public int getYPosLeftTopCorner()
    {
        return yPosLeftTopCorner;
    }

    public int getDiameter()
    {
        return diameter;
    }

    public int getXCenter()
    {
        return xPosLeftTopCorner + diameter / 2;
    }

    public int getYCenter()
    {
        return yPosLeftTopCorner + diameter / 2;
    }

    public boolean isTouchingBottom()
    {
        return isTouchingBottom;
    }

    public void setTouchingBottom(boolean touchingBottom)
    {
        isTouchingBottom = touchingBottom;
    }
}
