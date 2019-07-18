package sobow.brick.breaker.game;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_SPACE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import sobow.brick.breaker.level.Ball;
import sobow.brick.breaker.level.Bricks;
import sobow.brick.breaker.level.Racket;
import sobow.brick.breaker.level.TextMessages;
import sobow.brick.breaker.services.CollisionResolver;

public class BoardBrickBreaker extends JPanel implements ActionListener
{
    private static BoardBrickBreaker instance;

    private Racket racket = Racket.getInstance();
    private Ball ball = Ball.getInstance();
    private Bricks bricks = Bricks.getInstance();
    private TextMessages textMessages = TextMessages.getInstance();
    private Timer timer = new Timer(20, this);

    private int playerScore;

    private boolean initialState;


    public static BoardBrickBreaker getInstance()
    {
        if (instance == null)
        {
            synchronized (BoardBrickBreaker.class)
            {
                if (instance == null)
                {
                    instance = new BoardBrickBreaker();
                }
            }
        }
        synchronized (BoardBrickBreaker.class)
        {
            return instance;
        }
    }

    private BoardBrickBreaker()
    {
        setBackground(Color.BLACK);
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
        resetGame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
        ball.update();
        CollisionResolver.resolveCollision();
        if (ball.isTouchingBottom() || bricks.isBrickGridEmpty())
        {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        revalidate();

        racket.paint(g);
        ball.paint(g);
        bricks.paint(g);
        textMessages.paint(g, timer.isRunning(), initialState, playerScore);
    }

    private class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            boolean isTimerRunning = timer.isRunning();
            int keyID = e.getKeyCode();

            if (isTimerRunning)
            {
                racket.KeyPressed(e);
            }
            else if (initialState && keyID == VK_SPACE)
            {
                timer.start();
                initialState = false;
            }
            else if (!initialState && keyID == VK_ENTER)
            {
                resetGame();
                repaint();
            }
        }
    }

    private void resetGame()
    {
        bricks.resetBrickGrid();
        racket.reset();
        ball.reset();

        playerScore = 0;

        initialState = true;
    }
}
