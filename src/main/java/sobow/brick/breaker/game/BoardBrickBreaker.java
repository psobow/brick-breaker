package sobow.brick.breaker.game;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_SPACE;

import java.awt.Color;
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
    private Random random = new Random();
    private BrickCollisionDTO brickCollisionDTO;

    private int playerScore;
    private int yAxisBallMotionFactor;
    private int xAxisBallMotionFactor;

    private boolean initialState;
    private boolean collisionWithRacketPossible;

    private final int INIT_X_AXIS_BALL_MOTION_FACTOR = -3;
    private final int INIT_Y_AXIS_BALL_MOTION_FACTOR = -3;

    private final Color BACKGROUND_COLOR = Color.black;

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
