package sobow.brick.breaker.game;

import javax.swing.JFrame;
import sobow.brick.breaker.settings.WindowSettings;

public class MainWindow extends JFrame
{
    private final RenderPanel RENDER_PANEL = RenderPanel.getInstance();

    public MainWindow()
    {
        add(RENDER_PANEL);
        setTitle("Brick Breaker");
        setSize(WindowSettings.WIDTH, WindowSettings.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}