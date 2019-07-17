package sobow.brick.breaker.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import sobow.brick.breaker.settings.WindowSettings;

public class Ball
{
    private static final Color COLOR = Color.CYAN.darker().darker();

    private static final int DIAMETER = 20;
    private static final int INIT_X_POS_TOP_LEFT_CORNER =
            WindowSettings.WIDTH / 2 - DIAMETER / 2; // center the ball horizontally
    private static final int INIT_Y_POS_TOP_LEFT_CORNER = 450;

    private static Ball instance;

    private Rectangle bounds = new Rectangle();

    private int xPosLeftTopCorner;
    private int yPosLeftTopCorner;
    private int diameter;

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
    }

    public void paint(Graphics g)
    {
        g.setColor(COLOR);
        g.fillOval(xPosLeftTopCorner, yPosLeftTopCorner, diameter, diameter);
    }

    public void increaseXby(int additionalX)
    {
        xPosLeftTopCorner = xPosLeftTopCorner + additionalX;
    }

    public void increaseYby(int additionalY)
    {
        yPosLeftTopCorner = yPosLeftTopCorner + additionalY;
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
}
