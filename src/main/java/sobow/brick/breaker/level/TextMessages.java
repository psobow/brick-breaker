package sobow.brick.breaker.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import sobow.brick.breaker.services.ScoreService;

public class TextMessages
{
    private static final Color COLOR = Color.LIGHT_GRAY;

    private static final int FONT_SIZE = 25;
    private static final int CONTROLS_INFO_Y_POS = 350;

    private static TextMessages instance;

    private Ball ball = Ball.getInstance();


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

    public void paint(Graphics g, boolean isTimerRunning)
    {
        g.setColor(COLOR);
        g.setFont(new Font("Arial", 1, FONT_SIZE));

        if (!isTimerRunning)
        {
            paintControlsInfo(g);
        }
        // score info
        g.drawString("Score: " + ScoreService.getScore(), 25, 25);
    }

    private void paintControlsInfo(Graphics g)
    {
        if (ball.isTouchingBottom())
        {
            g.drawString("Press enter to reset", 360, CONTROLS_INFO_Y_POS);
        }
        else
        {
            g.drawString("Press space bar to start", 350, CONTROLS_INFO_Y_POS);
            g.drawString("Use arrows to move the racket", 310, CONTROLS_INFO_Y_POS + FONT_SIZE);
        }
    }


}

