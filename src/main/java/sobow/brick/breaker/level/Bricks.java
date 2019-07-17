package sobow.brick.breaker.level;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Bricks
{
    public class Brick extends Rectangle
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
    private static final int QUANTITY_OF_BRICKS_IN_ROW = 13;

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
        resetBrickGrid();
    }

    public void resetBrickGrid()
    {
        brickGrid.clear();

        List<Brick> topBrickRow = new ArrayList<>();
        List<Brick> midBrickRow = new ArrayList<>();
        List<Brick> botBrickRow = new ArrayList<>();

        topBrickRow.add(new Brick(DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS, DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS));
        midBrickRow.add(new Brick(DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS,
                                  BRICK_HEIGHT + DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS + GAP_BETWEEN_BRICKS));
        botBrickRow.add(new Brick(DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS,
                                  2 * BRICK_HEIGHT + DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS + 2 * GAP_BETWEEN_BRICKS));
        for (int i = 1; i < QUANTITY_OF_BRICKS_IN_ROW; i++)
        {
            topBrickRow.add(new Brick(topBrickRow.get(topBrickRow.size() - 1).x + BRICK_WIDTH + GAP_BETWEEN_BRICKS,
                                      DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS));
            midBrickRow.add(new Brick(midBrickRow.get(midBrickRow.size() - 1).x + BRICK_WIDTH + GAP_BETWEEN_BRICKS,
                                      DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS + BRICK_HEIGHT + GAP_BETWEEN_BRICKS));
            botBrickRow.add(new Brick(botBrickRow.get(botBrickRow.size() - 1).x + BRICK_WIDTH + GAP_BETWEEN_BRICKS,
                                      DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS + 2 * BRICK_HEIGHT
                                      + 2 * GAP_BETWEEN_BRICKS));
        }

        brickGrid.add(topBrickRow);
        brickGrid.add(midBrickRow);
        brickGrid.add(botBrickRow);
    }

    public void paint(Graphics g)
    {
        g.setColor(COLOR);
        brickGrid.stream()
                 .flatMap(list -> list.stream())
                 .forEach(brick -> g.fillRect(brick.x, brick.y, brick.width, brick.height));
    }

    public Brick getBrick(int row, int column)
    {
        return brickGrid.get(row).get(column);
    }

    public List<List<Brick>> getBrickGrid()
    {
        return brickGrid;
    }

    public void removeBrick(int row, int column)
    {
        brickGrid.get(row).remove(column);
    }

    public boolean isBrickGridEmpty()
    {
        return brickGrid.get(0).isEmpty() && brickGrid.get(1).isEmpty() && brickGrid.get(2).isEmpty();
    }

    public static int getBrickWidth()
    {
        return BRICK_WIDTH;
    }

    public static int getBrickHeight()
    {
        return BRICK_HEIGHT;
    }
}
