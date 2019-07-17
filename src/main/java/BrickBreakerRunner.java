import java.awt.EventQueue;
import sobow.brick.breaker.game.RenderGameRelationInitializator;

public class BrickBreakerRunner
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(RenderGameRelationInitializator::new);
    }
}
