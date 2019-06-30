package sobow.brick.breaker;

public class Ball
{
    private static Ball instance;
    private static Racket racket = Racket.getInstance();
    private static final WindowSettings WINDOW_SETTINGS = WindowSettings.getInstance();

    private static final int INIT_R = 30;
    private static final int INIT_X_POS = WINDOW_SETTINGS.getWINDOW_WIDTH() / 2;
    private static final int INIT_Y_POS =
            WINDOW_SETTINGS.getWINDOW_HEIGHT() - WINDOW_SETTINGS.getWINDOW_TOP_BAR_HEIGHT() - racket.height - INIT_R;

    private static int x = INIT_X_POS - (INIT_R / 2);
    private static int y = INIT_Y_POS - (INIT_R / 2);
    private static int r = INIT_R;

    private Ball()
    {
        resetBall();
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

    public static void resetBall()
    {
        x = INIT_X_POS - (INIT_R / 2);
        y = INIT_Y_POS - (INIT_R / 2);
        r = INIT_R;
    }

    public static void increseXby(int additionalX)
    {
        x = x + additionalX;
    }

    public static void increseYby(int additionalY)
    {
        y = y + additionalY;
    }

    public static int getX()
    {
        return x;
    }

    public static int getY()
    {
        return y;
    }

    public static int getR()
    {
        return r;
    }
}
