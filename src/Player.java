import javax.swing.JLabel;

public class Player {
    private JLabel icon;
    private int loc_Y;
    private int loc_X;
    private static int number = 0;

    public Player(JLabel icon) {
        this.icon = icon;
        this.loc_Y = 9;
        this.loc_X = 0;
        number++;
    }

    public void reset() {
        loc_Y = 9;
        loc_X = 0;
    }

    // Getter
    public int get_Y() {
        return loc_Y;
    }

    public int get_X() {
        return loc_X;
    }

    public JLabel getIcon() {
        return icon;
    }

    // Setter
    public void set_coordinates(int Y, int X) {
        this.loc_Y = Y;
        this.loc_X = X;
    }

    public String toString() {
        return String.format("Player %d", number);
    }
}