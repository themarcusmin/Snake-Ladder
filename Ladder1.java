/*
 *  White ladder
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Ladder1 extends Unit {

    private BufferedImage ladder1;

    // Read image & set coordinates
    public Ladder1() {
        try {
            ladder1 = ImageIO.read(getClass().getResource("img/ladder1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {
        // Starting Y can 3 to 4 inclusive
        int random_Y = rand.nextInt(2) + 3;
        set_Y(random_Y);
        // Start X can be 1 to 9 inclusive
        int random_X = rand.nextInt(9) + 1;
        set_X(random_X);
        
        // End Y is less 3 squares
        set_end_Y(random_Y - 3);
        // End X is the same X
        set_end_X(random_X);
    }

    public BufferedImage getImage() {
        return ladder1;
    }

    public int adjustedX(int x) {
        return x + 8;
    }

    public int adjustedY(int y) {
        return y - 150;
    }

    public String toString() {
        return "Ladder 1 | White Ladder";
    }
}