package sobow.brick.breaker.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import sobow.brick.breaker.dto.BrickCollisionDTO;
import sobow.brick.breaker.level.Ball;
import sobow.brick.breaker.level.Bricks;
import sobow.brick.breaker.level.Bricks.Brick;
import sobow.brick.breaker.level.Racket;
import sobow.brick.breaker.services.CollisionResolver;
import sobow.brick.breaker.settings.WindowSettings;

public class BoardBrickBreaker extends JPanel implements ActionListener
{
    private static BoardBrickBreaker instance;

    private Racket racket = Racket.getInstance();
    private Ball ball = Ball.getInstance();
    private Bricks bricks = Bricks.getInstance();
    private Timer timer = new Timer(20, this);
    private Random random = new Random();
    private BrickCollisionDTO brickCollisionDTO;

    private int playerScore;
    private int yAxisBallMotionFactor;
    private int xAxisBallMotionFactor;

    private boolean initialState;
    private boolean collisionWithRacketPossible;

    private final int INFORMATION_FONT_SIZE = 20;
    private final int RACKET_SPEED = 15;
    private final int INIT_X_AXIS_BALL_MOTION_FACTOR = -3;
    private final int INIT_Y_AXIS_BALL_MOTION_FACTOR = -3;

    private final Color BACKGROUND_COLOR = Color.black;
    private final Color TEXT_COLOR = Color.LIGHT_GRAY;

    private Brick brickWhichCollidedWithBall;

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
        setBackground(BACKGROUND_COLOR);
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
        resetGame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();

        brickCollisionDTO = CollisionResolver.withBricks();
        if (brickCollisionDTO.isCollisionDetected())
        {
            brickWhichCollidedWithBall = bricks.getBrick(brickCollisionDTO.getRowIndex(),
                                                         brickCollisionDTO.getColumnIndex());
            playerScore++;

            // if collision occured and ball center is located below or above brick
            if (ball.getYCenter() >= brickWhichCollidedWithBall.y + bricks.getBrickHeight()
                || ball.getYCenter() <= brickWhichCollidedWithBall.y)
            {
                yAxisBallMotionFactor *= -1;
            }
            else
            {
                xAxisBallMotionFactor *= -1;
            }
            bricks.removeBrick(brickCollisionDTO.getRowIndex(), brickCollisionDTO.getColumnIndex());
            collisionWithRacketPossible = true;
        }
        else if (CollisionResolver.ballWithLeftWall() || CollisionResolver.ballWithRightWall())
        {
            xAxisBallMotionFactor *= -1;
            collisionWithRacketPossible = true;
        }
        else if (CollisionResolver.ballWithTopWall())
        {
            yAxisBallMotionFactor *= -1;
            collisionWithRacketPossible = true;
        }
        else if (CollisionResolver.ballWithRacket(collisionWithRacketPossible))
        {
            yAxisBallMotionFactor *= -1;
            collisionWithRacketPossible = false;
        }
        else if (CollisionResolver.ballWithBottomWall())
        {
            timer.stop();
        }

        // Move ball
        ball.increaseXby(xAxisBallMotionFactor);
        ball.increaseYby(yAxisBallMotionFactor);

        if (bricks.isBrickGridEmpty())
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


        // initial information
        g.setColor(TEXT_COLOR);
        g.setFont(new Font("Arial", 1, INFORMATION_FONT_SIZE));
        if (timer.isRunning() == false)
        {
            if (initialState)
            {
                g.drawString("Press space bar to start!", WindowSettings.WIDTH / 3 + 40, WindowSettings.HEIGHT / 2);
                g.drawString("Use arrows to move the racket",
                             WindowSettings.WIDTH / 3 + 10,
                             WindowSettings.HEIGHT / 2 + INFORMATION_FONT_SIZE);
            }
            else
            {
                g.drawString("Press enter to reset!", WindowSettings.WIDTH / 3 + 40, WindowSettings.HEIGHT / 2);
            }
        }
        // score info
        g.drawString("Score: " + playerScore, 25, 25);
    }

    private class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int keyID = e.getKeyCode();
            switch (keyID)
            {
                case KeyEvent.VK_SPACE:
                    if (timer.isRunning() == false && initialState == true)
                    {
                        timer.start();
                        initialState = false;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (racket.x > 0 && timer.isRunning())
                    {
                        racket.x -= RACKET_SPEED;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (racket.x + racket.width < WindowSettings.WIDTH && timer.isRunning())
                    {
                        racket.x += RACKET_SPEED;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    resetGame();
                    repaint();
                    break;
                default:
                    break;
            }
        }
    }

    private void resetGame()
    {
        bricks.resetBrickGrid();
        racket.reset();
        ball.reset();

        yAxisBallMotionFactor = INIT_Y_AXIS_BALL_MOTION_FACTOR;
        xAxisBallMotionFactor = INIT_X_AXIS_BALL_MOTION_FACTOR;
        playerScore = 0;

        // Ball will randomly fly to left or right side
        if (random.nextInt(10) % 2 == 0)
        {
            xAxisBallMotionFactor = xAxisBallMotionFactor * (-1);
        }
        initialState = true;
        collisionWithRacketPossible = true;
    }
}
