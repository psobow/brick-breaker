package sobow.brick.breaker.level;

import java.awt.Rectangle;
import sobow.brick.breaker.settings.WindowSettings;

public class Ball
{
    private static Ball instance;
    private static Rectangle bounds = new Rectangle();

    private static final int DIAMETER = 20;
    private static final int INIT_X_POS_TOP_LEFT_CORNER = WindowSettings.getWINDOW_WIDTH() / 2 - 10;
    private static final int INIT_Y_POS_TOP_LEFT_CORNER = (int) (WindowSettings.getWINDOW_HEIGHT() / 1.3);

    private int xPosLeftTopCorner;
    private int yPosLeftTopCorner;
    private int diameter;

    private Ball()
    {
        resetBallPosition();
    }

    public void resetBallPosition()
    {
        xPosLeftTopCorner = INIT_X_POS_TOP_LEFT_CORNER;
        yPosLeftTopCorner = INIT_Y_POS_TOP_LEFT_CORNER;
        diameter = DIAMETER;
    }

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
