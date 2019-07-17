package sobow.brick.breaker.game;

import javax.swing.JFrame;
import sobow.brick.breaker.settings.WindowSettings;

public class MainWindow extends JFrame
{
    public MainWindow()
    {
        add(BoardBrickBreaker.getInstance());
        setTitle("Brick Breaker");
        setSize(WindowSettings.WIDTH, WindowSettings.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}