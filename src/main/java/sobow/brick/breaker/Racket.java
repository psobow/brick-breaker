package sobow.brick.breaker;

import java.awt.Rectangle;

public class Racket extends Rectangle
{
    private static Racket instance;

    private static final int RACKET_WIDTH = WindowSettings.getWINDOW_HEIGHT() / 5;
    private static final int RACKET_HEIGHT = 10;
    private static final int INIT_X_POS = WindowSettings.getWINDOW_WIDTH() / 2 - RACKET_WIDTH / 2;
    private static final int INIT_Y_POS =
            WindowSettings.getWINDOW_HEIGHT() - RACKET_HEIGHT - WindowSettings.getWINDOW_TOP_BAR_HEIGHT() - 30;


    private Racket()
    {
        super(INIT_X_POS, INIT_Y_POS, RACKET_WIDTH, RACKET_HEIGHT);
    }

    public static Racket getInstance()
    {
        if (instance == null)
        {
            synchronized (Racket.class)
            {
                if (instance == null)
                {
                    instance = new Racket();
                }
            }
        }

        synchronized (Racket.class)
        {
            return instance;
        }
    }

    public void resetPosition()
    {
        if (instance != null)
        {
            instance.x = INIT_X_POS;
            instance.y = INIT_Y_POS;
        }
    }
}
