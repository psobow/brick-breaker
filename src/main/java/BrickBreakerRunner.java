import java.awt.EventQueue;
import sobow.brick.breaker.game.MainWindow;

public class BrickBreakerRunner
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> new MainWindow());
    }
}
/*
TODO: zmienić poruszanie się rakietki, użyć KeyPressed i KeyRelesead które zmieniają wartość prywatnej zmiennej dx dodać metody move() i update()
TODO: uprośić API wszystkich klas
TODO: posprzątać kolizje
TODO: dodać scoreServices i aktualizowac jego stan w collision resolver

 */
