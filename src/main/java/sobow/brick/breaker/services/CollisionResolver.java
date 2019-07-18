package sobow.brick.breaker.services;



import sobow.brick.breaker.level.Ball;
import sobow.brick.breaker.level.Bricks;
import sobow.brick.breaker.level.Bricks.Brick;
import sobow.brick.breaker.level.Racket;
import sobow.brick.breaker.settings.WindowSettings;

public class CollisionResolver
{
    private static Ball ball = Ball.getInstance();
    private static Bricks bricks = Bricks.getInstance();
    private static Racket racket = Racket.getInstance();
    private static boolean isBallCollisionWithRacketPossible = true;

    private CollisionResolver() {}

    public static void resolveCollision()
    {
        ballWithRacket();

        ballWithLeftWall();
        ballWithRightWall();

        ballWithTopWall();
        ballWithBottomWall();

        ballWithBricks();
    }

    private static void ballWithLeftWall()
    {
        if (ball.getXPosLeftTopCorner() <= 0)
        {
            ball.revertMotionXAxis();
            isBallCollisionWithRacketPossible = true;
        }
    }

    private static void ballWithRightWall()
    {
        if (ball.getXPosLeftTopCorner() + ball.getDiameter() >= WindowSettings.WIDTH)
        {
            ball.revertMotionXAxis();
            isBallCollisionWithRacketPossible = true;
        }
    }

    private static void ballWithTopWall()
    {
        if (ball.getYPosLeftTopCorner() <= 0)
        {
            ball.revertMotionYAxis();
            isBallCollisionWithRacketPossible = true;
        }
    }

    private static void ballWithBottomWall()
    {
        if (ball.getYPosLeftTopCorner() + ball.getDiameter()
            >= WindowSettings.HEIGHT - WindowSettings.WINDOW_TOP_BAR_HEIGHT)
        {
            ball.setTouchingBottom(true);
        }
    }

    private static void ballWithRacket()
    {
        if (racket.intersects(ball.getBounds()) && isBallCollisionWithRacketPossible && ball.getYCenter() <= racket.y)
        {
            ball.revertMotionYAxis();
            isBallCollisionWithRacketPossible = false;
        }
    }

    private static void ballWithBricks()
    {
        loop:
        for (int row = 0; row < bricks.getBrickGrid().size(); row++)
        {
            for (int column = 0; column < bricks.getBrickGrid().get(row).size(); column++)
            {
                Brick brick = bricks.getBrick(row, column);
                if (brick.intersects(ball.getBounds()))
                {
                    // if collision occured and ball center is located below or above brick
                    if (ball.getYCenter() >= brick.y + bricks.getBrickHeight() || ball.getYCenter() <= brick.y)
                    {
                        ball.revertMotionYAxis();
                    }
                    else
                    {
                        ball.revertMotionXAxis();
                    }

                    bricks.removeBrick(row, column);
                    isBallCollisionWithRacketPossible = true;
                    break loop;
                }
            }
        }
    }
}
