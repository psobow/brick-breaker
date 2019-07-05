package sobow.brick.breaker;

import javax.swing.JFrame;

public class MainWindow extends JFrame
{
    private final RenderPanel RENDER_PANEL = RenderPanel.getInstance();

    public MainWindow()
    {
        add(RENDER_PANEL);
        setTitle("Brick Breaker");
        setSize(WindowSettings.getWINDOW_WIDTH(), WindowSettings.getWINDOW_HEIGHT());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}