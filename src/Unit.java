import java.util.Random;

public abstract class Unit {

    private int X;
    private int Y;
    private int end_X;
    private int end_Y;
    public Random rand = new Random();

    public void rollRandom() {
        
    }

    // Setter
    public void set_X(int start_X) {
        this.X = start_X;
    }

    public void set_Y(int start_Y) {
        this.Y = start_Y;
    }

    public void set_end_X(int end_X) {
        this.end_X = end_X;
    }

    public void set_end_Y(int end_Y) {
        this.end_Y = end_Y;
    }

    // Getter
    public int X() {
        return X;
    }

    public int Y() {
        return Y;
    }

    public int end_X() {
        return end_X;
    }

    public int end_Y() {
        return end_Y;
    }
}