package sobow.brick.breaker;

public class RenderGameRelationInitializator
{
    private BrickBreakerGame gameInstance = BrickBreakerGame.getInstance();
    private RenderPanel renderPanelInstance = RenderPanel.getInstance();

    public RenderGameRelationInitializator()
    {
        renderPanelInstance.setGameInstance(gameInstance);
        gameInstance.setRenderPanelInstance(renderPanelInstance);
    }
}
