package sobow.brick.breaker.services;


import sobow.brick.breaker.dto.BrickCollisionDTO;
import sobow.brick.breaker.level.Ball;
import sobow.brick.breaker.level.Bricks;
import sobow.brick.breaker.level.Racket;
import sobow.brick.breaker.settings.WindowSettings;

public class CollisionResolver
{
    private static Ball ball = Ball.getInstance();
    private static Bricks bricks = Bricks.getInstance();
    private static Racket racket = Racket.getInstance();
    private static BrickCollisionDTO brickCollisionDTO = new BrickCollisionDTO(false, 0, 0);


    private CollisionResolver() {}

    public static boolean ballWithLeftWall()
    {
        return ball.getXPosLeftTopCorner() <= 0;
    }

    public static boolean ballWithRightWall()
    {
        return ball.getXPosLeftTopCorner() + ball.getDiameter() >= WindowSettings.WIDTH;
    }

    public static boolean ballWithTopWall()
    {
        return ball.getYPosLeftTopCorner() <= 0;
    }

    public static boolean ballWithBottomWall()
    {
        return ball.getYPosLeftTopCorner() + ball.getDiameter()
               >= WindowSettings.HEIGHT - WindowSettings.WINDOW_TOP_BAR_HEIGHT;
    }

    public static boolean ballWithRacket(boolean isCollisionPossible)
    {
        return racket.intersects(ball.getBounds()) && isCollisionPossible && ball.getYCenter() <= racket.y;
    }

    public static BrickCollisionDTO withBricks()
    {
        brickCollisionDTO.setCollisionDetected(false);
        loop:
        for (int row = 0; row < bricks.getBrickGrid().size(); row++)
        {
            for (int column = 0; column < bricks.getBrickGrid().get(row).size(); column++)
            {
                if (bricks.getBrick(row, column).intersects(ball.getBounds()))
                {
                    brickCollisionDTO.setCollisionDetected(true);
                    brickCollisionDTO.setRowIndex(row);
                    brickCollisionDTO.setColumnIndex(column);
                    break loop;
                }
            }
        }
        return brickCollisionDTO;
    }
}
