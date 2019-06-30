package sobow.brick.breaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class BrickBreakerGame implements ActionListener, KeyListener
{
    private static BrickBreakerGame instance;

    private WindowSettings windowSettings = WindowSettings.getInstance();
    private Racket racket = Racket.getInstance();
    private RenderPanel renderPanel;
    private Timer timer = new Timer(20, this);
    private int playerScore = 0;

    private final int INFORMATION_FONT_SIZE = 20;
    private final int RACKET_SPEED = 15;

    private final Color BACKGROUND_COLOR = Color.black;
    private final Color RACKET_COLOR = Color.green.darker().darker();
    private final Color TEXT_COLOR = Color.LIGHT_GRAY;
    private final Color BALL_COLOR = Color.CYAN;

    private BrickBreakerGame()
    {
        MainWindow mainWindow = new MainWindow();
        mainWindow.addKeyListener(this);
    }

    public static BrickBreakerGame getInstance()
    {
        if (instance == null)
        {
            synchronized (BrickBreakerGame.class)
            {
                if (instance == null)
                {
                    instance = new BrickBreakerGame();
                }
            }
        }
        synchronized (BrickBreakerGame.class)
        {
            return instance;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        renderPanel.revalidate();
        renderPanel.repaint();
    }

    public void repaint(Graphics g)
    {
        // Paint background
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, windowSettings.getWINDOW_WIDTH(), windowSettings.getWINDOW_HEIGHT());

        // Paint Racket
        g.setColor(RACKET_COLOR);
        g.fillRect(racket.x, racket.y, racket.width, racket.height);

        // Paint Ball
        g.setColor(BALL_COLOR);
        g.fillOval(Ball.getX(), Ball.getY(), Ball.getR(), Ball.getR());

        // initial information
        g.setColor(TEXT_COLOR);
        g.setFont(new Font("Arial", 1, INFORMATION_FONT_SIZE));
        if (timer.isRunning() == false)
        {
            g.drawString("Press space bar to start!",
                         windowSettings.getWINDOW_WIDTH() / 3 + 40,
                         windowSettings.getWINDOW_HEIGHT() / 2);
            g.drawString("Use arrows to move the racket",
                         windowSettings.getWINDOW_WIDTH() / 3 + 25,
                         windowSettings.getWINDOW_HEIGHT() / 2 + INFORMATION_FONT_SIZE);
        }
        // score info
        g.drawString("Score: " + playerScore, 25, 25);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyID = e.getKeyCode();
        switch (keyID)
        {
            case KeyEvent.VK_SPACE:
                if (timer.isRunning() == false)
                {
                    timer.start();
                }
                break;
            case KeyEvent.VK_LEFT:
                racket.x -= RACKET_SPEED;
                break;
            case KeyEvent.VK_RIGHT:
                racket.x += RACKET_SPEED;
                break;
            default:
                break;
        }
    }

    private void resetGame()
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    public void setRenderPanelInstance(RenderPanel renderPanel)
    {
        this.renderPanel = renderPanel;
    }
}
