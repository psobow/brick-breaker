package sobow.brick.breaker;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BrickBreakerGame implements ActionListener, KeyListener
{
    private static BrickBreakerGame instance;

    private BrickBreakerGame()
    {
        MainWindow mainWindow = new MainWindow();
        mainWindow.addKeyListener(this);
    }

    public static BrickBreakerGame getInstance()
    {
        if (instance == null)
        {
            synchronized (BrickBreakerGame.class)
            {
                if (instance == null)
                {
                    instance = new BrickBreakerGame();
                }
            }
        }
        synchronized (BrickBreakerGame.class)
        {
            return instance;
        }
    }

    public void repaint(Graphics g)
    {
    }

    public void setRenderPanelInstance(RenderPanel renderPanelInstance)
    {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
