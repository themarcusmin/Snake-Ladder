import javax.swing.Timer;
import java.awt.event.ActionListener;

public class PieceMover extends Timer
{
    private static final long serialVersionUID = 10l;
    public PieceMover(int delay, ActionListener listener)
    {
        super(delay, listener);
        setInitialDelay(500);
    }
}