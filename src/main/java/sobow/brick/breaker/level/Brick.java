package sobow.brick.breaker.level;

import java.awt.Rectangle;

public class Brick extends Rectangle
{
    private static final int BRICK_WIDTH = 50;
    private static final int BRICK_HEIGHT = 50;

    public Brick(int x, int y)
    {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
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
