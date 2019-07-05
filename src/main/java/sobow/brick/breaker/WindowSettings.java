package sobow.brick.breaker;


public class WindowSettings
{
    private static final int WINDOW_WIDTH = 960;
    private static final int WINDOW_HEIGHT = 540;
    private static final int WINDOW_TOP_BAR_HEIGHT = 30;

    private WindowSettings() {}


    public static int getWINDOW_WIDTH()
    {
        return WINDOW_WIDTH;
    }

    public static int getWINDOW_HEIGHT()
    {
        return WINDOW_HEIGHT;
    }

    public static int getWINDOW_TOP_BAR_HEIGHT()
    {
        return WINDOW_TOP_BAR_HEIGHT;
    }
}