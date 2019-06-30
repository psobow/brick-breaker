package sobow.brick.breaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class BrickBreakerGame implements ActionListener, KeyListener
{
    private static BrickBreakerGame instance;

    private WindowSettings windowSettings = WindowSettings.getInstance();
    private Racket racket = Racket.getInstance();
    private RenderPanel renderPanel;
    private Timer timer = new Timer(20, this);
    private Random random = new Random();
    private int playerScore = 0;
    private int yAxisBallMotionFactor = 0;
    private int xAxisBallMotionFactor = 0;
    private List<Brick> topBrickRow = new ArrayList<>();
    private List<Brick> midBrickRow = new ArrayList<>();
    private List<Brick> botBrickRow = new ArrayList<>();

    private final int INFORMATION_FONT_SIZE = 20;
    private final int RACKET_SPEED = 15;
    private final int GAP_BETWEEN_BRICKS = 5;

    private final Color BACKGROUND_COLOR = Color.black;
    private final Color RACKET_COLOR = Color.green.darker().darker();
    private final Color TEXT_COLOR = Color.LIGHT_GRAY;
    private final Color BALL_COLOR = Color.CYAN.darker().darker();
    private final Color BRICK_COLOR = Color.orange;

    private BrickBreakerGame()
    {
        MainWindow mainWindow = new MainWindow();
        mainWindow.addKeyListener(this);
        resetGame();
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

        // Move ball
        Ball.increseXby(xAxisBallMotionFactor);
        Ball.increseYby(yAxisBallMotionFactor);

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

        // Paint Bricks
        g.setColor(BRICK_COLOR);
        for (Brick brick : topBrickRow)
        {
            g.fillRect(brick.x, brick.y, brick.width, brick.height);
        }
        for (Brick brick : midBrickRow)
        {
            g.fillRect(brick.x, brick.y, brick.width, brick.height);
        }
        for (Brick brick : botBrickRow)
        {
            g.fillRect(brick.x, brick.y, brick.width, brick.height);
        }

        // initial information
        g.setColor(TEXT_COLOR);
        g.setFont(new Font("Arial", 1, INFORMATION_FONT_SIZE));
        if (timer.isRunning() == false)
        {
            g.drawString("Press space bar to start!",
                         windowSettings.getWINDOW_WIDTH() / 3 + 40,
                         windowSettings.getWINDOW_HEIGHT() / 2);
            g.drawString("Use arrows to move the racket", windowSettings.getWINDOW_WIDTH() / 3 + 10,
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
            case KeyEvent.VK_ENTER:
                resetGame();
                break;
            default:
                break;
        }
    }

    private void resetGame()
    {
        initializeBricks();
        Racket.resetRacketPosition();
        Ball.resetBallPosition();

        yAxisBallMotionFactor = -1;
        xAxisBallMotionFactor = 1;
        playerScore = 0;

        // Ball will randomly fly to left or right side
        if ((random.nextInt(100) + 1) % 2 == 0)
        {
            xAxisBallMotionFactor = xAxisBallMotionFactor * (-1);
        }
    }

    private void initializeBricks()
    {
        topBrickRow.clear();
        midBrickRow.clear();
        botBrickRow.clear();

        topBrickRow.add(new Brick(120, 50));
        midBrickRow.add(new Brick(120, 50 + GAP_BETWEEN_BRICKS + Brick.getBrickHeight()));
        botBrickRow.add(new Brick(120, 50 + 2 * GAP_BETWEEN_BRICKS + 2 * Brick.getBrickHeight()));
        for (int i = 1; i < 13; i++)
        {
            topBrickRow.add(new Brick(
                    topBrickRow.get(topBrickRow.size() - 1).x + Brick.getBrickWidth() + GAP_BETWEEN_BRICKS, 50));
            midBrickRow.add(new Brick(
                    midBrickRow.get(midBrickRow.size() - 1).x + Brick.getBrickWidth() + GAP_BETWEEN_BRICKS,
                    50 + GAP_BETWEEN_BRICKS + Brick.getBrickHeight()));
            botBrickRow.add(new Brick(
                    botBrickRow.get(botBrickRow.size() - 1).x + Brick.getBrickWidth() + GAP_BETWEEN_BRICKS,
                    50 + 2 * GAP_BETWEEN_BRICKS + 2 * Brick.getBrickHeight()));
        }
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
