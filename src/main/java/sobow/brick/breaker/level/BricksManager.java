package sobow.brick.breaker.level;

import java.util.ArrayList;
import java.util.List;

public class BricksManager
{
    private List<List<Brick>> brickGrid = new ArrayList<>();
    private static BricksManager instance;
    private final int GAP_BETWEEN_BRICKS = 5;
    private final int DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS = 120;
    private final int DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS = 50;
    private final int QUANTITY_OF_BRICKS_IN_ROW = 13;

    private BricksManager()
    {
        resetBrickGrid();
    }

    public static BricksManager getInstance()
    {
        if (instance == null)
        {
            synchronized (BricksManager.class)
            {
                if (instance == null)
                {
                    instance = new BricksManager();
                }
            }
        }
        synchronized (BricksManager.class)
        {
            return instance;
        }
    }

    public void resetBrickGrid()
    {
        brickGrid.clear();

        List<Brick> topBrickRow = new ArrayList<>();
        List<Brick> midBrickRow = new ArrayList<>();
        List<Brick> botBrickRow = new ArrayList<>();

        topBrickRow.add(new Brick(DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS, DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS));
        midBrickRow.add(new Brick(DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS,
                                  Brick.getBrickHeight() + DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS + GAP_BETWEEN_BRICKS));
        botBrickRow.add(new Brick(DISTANCE_BETWEEN_LEFT_WALL_AND_BRICKS,
                                  2 * Brick.getBrickHeight() + DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS
                                  + 2 * GAP_BETWEEN_BRICKS));
        for (int i = 1; i < QUANTITY_OF_BRICKS_IN_ROW; i++)
        {
            topBrickRow.add(new Brick(
                    topBrickRow.get(topBrickRow.size() - 1).x + Brick.getBrickWidth() + GAP_BETWEEN_BRICKS,
                    DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS));
            midBrickRow.add(new Brick(
                    midBrickRow.get(midBrickRow.size() - 1).x + Brick.getBrickWidth() + GAP_BETWEEN_BRICKS,
                    DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS + Brick.getBrickHeight() + GAP_BETWEEN_BRICKS));
            botBrickRow.add(new Brick(
                    botBrickRow.get(botBrickRow.size() - 1).x + Brick.getBrickWidth() + GAP_BETWEEN_BRICKS,
                    DISTANCE_BETWEEN_TOP_WALL_AND_BRICKS + 2 * Brick.getBrickHeight() + 2 * GAP_BETWEEN_BRICKS));
        }

        brickGrid.add(topBrickRow);
        brickGrid.add(midBrickRow);
        brickGrid.add(botBrickRow);
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
}
