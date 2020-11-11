/*
 *  Yellow Ladder
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Ladder2 extends Unit {

    private BufferedImage ladder2;

    // Read image & set coordinates
    public Ladder2() {
        try {
            ladder2 = ImageIO.read(getClass().getResource("img/ladder2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {
        // Starting Y can 8 to 9 inclusive
        int random_Y = rand.nextInt(2) + 8;
        set_Y(random_Y);
        // Start X can be 1 to 8 inclusive
        int random_X = rand.nextInt(8) + 1;
        set_X(random_X);
        
        // End Y is 2 squares more
        set_end_Y(random_Y - 2);
        // End X is less 1 square
        set_end_X(random_X + 1);
    }

    public BufferedImage getImage() {
        return ladder2;
    }

    public int adjustedX(int x) {
        return x;
    }

    public int adjustedY(int y) {
        return y - 95;
    }

    public String toString() {
        return "Ladder 2 | Yellow Ladder";
    }
}