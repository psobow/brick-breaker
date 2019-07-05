package sobow.brick.breaker;

import java.util.List;

public class CollisionResolver
{
    private static BrickCollisionDTO brickCollisionDTO = new BrickCollisionDTO(false, 0, 0);

    private CollisionResolver() {}

    public static boolean withLeftWall(Ball ball)
    {
        return ball.getXPosLeftTopCorner() <= 0;
    }

    public static boolean withRightWall(Ball ball)
    {
        return ball.getXPosLeftTopCorner() + ball.getDiameter() >= WindowSettings.getWINDOW_WIDTH();
    }

    public static boolean withTopWall(Ball ball)
    {
        return ball.getYPosLeftTopCorner() <= 0;
    }

    public static boolean withBottomWall(Ball ball)
    {
        return ball.getYPosLeftTopCorner() + ball.getDiameter()
               >= WindowSettings.getWINDOW_HEIGHT() - WindowSettings.getWINDOW_TOP_BAR_HEIGHT();
    }

    public static boolean withRacket(Ball ball, Racket racket, boolean isCollisionPossible)
    {
        return racket.intersects(ball.getBounds()) && isCollisionPossible && ball.getYCenter() <= racket.y;
    }

    public static BrickCollisionDTO withBricks(List<List<Brick>> brickGrid, Ball ball)
    {
        brickCollisionDTO.setCollisionDetected(false);
        loop:
        for (int row = 0; row < brickGrid.size(); row++)
        {
            for (int column = 0; column < brickGrid.get(row).size(); column++)
            {
                if (brickGrid.get(row).get(column).intersects(ball.getBounds()))
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
