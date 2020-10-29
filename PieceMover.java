import javax.swing.Timer;
import java.awt.event.ActionListener;

public class PieceMover extends Timer
{
    public PieceMover(int delay, ActionListener listener)
    {
        super(delay, listener);
        setInitialDelay(1500);
    }
}