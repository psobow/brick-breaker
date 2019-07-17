package sobow.brick.breaker.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import sobow.brick.breaker.settings.WindowSettings;

public class TextMessages
{
    private static final Color COLOR = Color.LIGHT_GRAY;

    private static final int FONT_SIZE = 25;


    private static TextMessages instance;


    public static TextMessages getInstance()
    {
        if (instance == null)
        {
            synchronized (TextMessages.class)
            {
                if (instance == null)
                {
                    instance = new TextMessages();
                }
            }
        }
        synchronized (TextMessages.class)
        {
            return instance;
        }
    }

    private TextMessages()
    {}

    public void paint(Graphics g, boolean isTimerRunning, boolean initialState, int playerScore)
    {
        g.setColor(COLOR);
        g.setFont(new Font("Arial", 1, FONT_SIZE));

        if (!isTimerRunning)
        {
            if (initialState)
            {
                g.drawString("Press space bar to start", 350, WindowSettings.HEIGHT / 2);
                g.drawString("Use arrows to move the racket", 310, WindowSettings.HEIGHT / 2 + FONT_SIZE);
            }
            else
            {
                g.drawString("Press enter to reset", 360, WindowSettings.HEIGHT / 2);
            }
        }
        // score info
        g.drawString("Score: " + playerScore, 25, 25);
    }


}

