package sobow.brick.breaker;

import java.awt.Rectangle;

public class Ball
{
    private static Ball instance;
    private static Racket racket = Racket.getInstance();
    private static final WindowSettings WINDOW_SETTINGS = WindowSettings.getInstance();

    private static final int DIAMETER = 20;

    private static final int INIT_X_POS_TOP_LEFT_CORNER = WINDOW_SETTINGS.getWINDOW_WIDTH() / 2 - 10;
    private static final int INIT_Y_POS_TOP_LEFT_CORNER =
            WINDOW_SETTINGS.getWINDOW_HEIGHT() - WINDOW_SETTINGS.getWINDOW_TOP_BAR_HEIGHT() - 50 - racket.height
            - DIAMETER;

    private static int xPosLeftTopCorner;
    private static int yPosLeftTopCorner;

    private static Rectangle bounds = new Rectangle();

    private Ball()
    {
        resetBallPosition();
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

    public static void resetBallPosition()
    {
        xPosLeftTopCorner = INIT_X_POS_TOP_LEFT_CORNER;
        yPosLeftTopCorner = INIT_Y_POS_TOP_LEFT_CORNER;
    }

    public static void increseXby(int additionalX)
    {
        xPosLeftTopCorner = xPosLeftTopCorner + additionalX;
    }

    public static void increseYby(int additionalY)
    {
        yPosLeftTopCorner = yPosLeftTopCorner + additionalY;
    }

    public static Rectangle getBounds()
    {
        bounds.x = xPosLeftTopCorner;
        bounds.y = yPosLeftTopCorner;
        bounds.width = bounds.height = DIAMETER;
        return bounds;
    }

    public static int getxPosLeftTopCorner()
    {
        return xPosLeftTopCorner;
    }

    public static int getyPosLeftTopCorner()
    {
        return yPosLeftTopCorner;
    }

    public static int getDiameter()
    {
        return DIAMETER;
    }

    public static int getXCenter()
    {
        return xPosLeftTopCorner + DIAMETER / 2;
    }

    public static int getYCenter()
    {
        return yPosLeftTopCorner + DIAMETER / 2;
    }
}
