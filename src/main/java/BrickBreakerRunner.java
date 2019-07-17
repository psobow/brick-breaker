import java.awt.EventQueue;
import sobow.brick.breaker.game.MainWindow;

public class BrickBreakerRunner
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> new MainWindow());
    }
}
