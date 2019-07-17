package sobow.brick.breaker.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import sobow.brick.breaker.settings.WindowSettings;

public class Racket extends Rectangle
{
    private static final int WIDTH = 100;
    private static final int HEIGHT = 10;
    private static final int INIT_X_POS = WindowSettings.WIDTH / 2 - WIDTH / 2; // center the Racket horizontally
    private static final int INIT_Y_POS = 470;
    private static Racket instance;

    private final Color COLOR = Color.green.darker().darker();

    private Racket()
    {
        super(INIT_X_POS, INIT_Y_POS, WIDTH, HEIGHT);
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

    public void paint(Graphics g)
    {
        g.setColor(COLOR);
        g.fillRect(x, y, width, height);
    }

    public void reset()
    {
        x = INIT_X_POS;
        y = INIT_Y_POS;
    }
}
