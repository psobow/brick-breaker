package sobow.brick.breaker.level;

import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import sobow.brick.breaker.settings.WindowSettings;

public class Racket extends Rectangle
{
    private static final int WIDTH = 100;
    private static final int HEIGHT = 10;
    private static final int INIT_X_POS = WindowSettings.WIDTH / 2 - WIDTH / 2; // center the Racket horizontally
    private static final int INIT_Y_POS = 470;
    private static final int RACKET_SPEED = 15;

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

    public void KeyPressed(KeyEvent e)
    {
        int keyID = e.getKeyCode();

        if (keyID == VK_LEFT && x > 0)
        {
            x -= RACKET_SPEED;
        }
        else if (keyID == VK_RIGHT && x + width < WindowSettings.WIDTH)
        {
            x += RACKET_SPEED;
        }
    }

    public void reset()
    {
        x = INIT_X_POS;
        y = INIT_Y_POS;
    }
}
