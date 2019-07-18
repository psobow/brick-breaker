package sobow.brick.breaker.level;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Bricks
{
    private class Brick extends Rectangle
    {
        Brick(int x, int y)
        {
            super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        }
    }

    private static final Color COLOR = Color.GRAY;

    private static final int BRICK_WIDTH = 50;
    private static final int BRICK_HEIGHT = 50;
    private static final int GAP_BETWEEN_BRICKS = 5;
    private static final int DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS = 120;
    private static final int DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS = 50;
    private static final int QUANTITY_OF_ROWS = 4;
    private static final int INIT_QUANTITY_OF_BRICKS_IN_ROW = 13;

    private static Bricks instance;

    private List<List<Brick>> brickGrid = new ArrayList<>();


    public static Bricks getInstance()
    {
        if (instance == null)
        {
            synchronized (Bricks.class)
            {
                if (instance == null)
                {
                    instance = new Bricks();
                }
            }
        }
        synchronized (Bricks.class)
        {
            return instance;
        }
    }

    private Bricks()
    {
        reset();
    }

    public void reset()
    {
        brickGrid.clear();

        for (int i = 0; i < QUANTITY_OF_ROWS; i++)
        {
            brickGrid.add(new ArrayList<>());
            List<Brick> currentRow = brickGrid.get(i);

            // Add initial brick to each row
            currentRow.add(new Brick(DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS,
                                     i * BRICK_HEIGHT + i * GAP_BETWEEN_BRICKS + DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS));

            // Add remaining bricks
            for (int j = 1; j < INIT_QUANTITY_OF_BRICKS_IN_ROW; j++)
            {
                currentRow.add(new Brick(currentRow.get(currentRow.size() - 1).x + BRICK_WIDTH + GAP_BETWEEN_BRICKS,
                                         i * BRICK_HEIGHT + i * GAP_BETWEEN_BRICKS
                                         + DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS));
            }
        }
    }

    public void paint(Graphics g)
    {
        g.setColor(COLOR);
        brickGrid.stream()
                 .flatMap(list -> list.stream())
                 .forEach(brick -> g.fillRect(brick.x, brick.y, brick.width, brick.height));
    }

    public int getBrickLeftTopCornerYPositionAt(int brickRowIndex, int brickColumnIndex)
    {
        return brickGrid.get(brickRowIndex).get(brickColumnIndex).y;
    }

    public boolean isBrickIntersectingWith(int brickRowIndex, int brickColumnIndex, Rectangle rectangle)
    {
        return brickGrid.get(brickRowIndex).get(brickColumnIndex).intersects(rectangle);
    }

    public void removeBrickAt(int brickRowIndex, int brickColumnIndex)
    {
        brickGrid.get(brickRowIndex).remove(brickColumnIndex);
    }

    public boolean isBrickGridEmpty()
    {
        return brickGrid.get(0).isEmpty() && brickGrid.get(1).isEmpty() && brickGrid.get(2).isEmpty();
    }

    public int getQuantityOfBricksInRowAt(int index)
    {
        return brickGrid.get(index).size();
    }

    public static int getQuantityOfRows()
    {
        return QUANTITY_OF_ROWS;
    }

    public static int getBrickHeight()
    {
        return BRICK_HEIGHT;
    }
}
