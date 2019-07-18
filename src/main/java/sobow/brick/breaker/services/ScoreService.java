package sobow.brick.breaker.services;

public class ScoreService
{
    private static int playerScore = 0;

    private ScoreService() {}

    public static void reset()
    {
        playerScore = 0;
    }

    public static void incrementScore()
    {
        playerScore++;
    }

    public static int getScore()
    {
        return playerScore;
    }

}
