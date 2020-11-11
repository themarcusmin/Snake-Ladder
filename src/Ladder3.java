/*
 *  Black Ladder
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Ladder3 extends Unit {

    private BufferedImage ladder3;

    // Read image & set coordinates
    public Ladder3() {
        try {
            ladder3 = ImageIO.read(getClass().getResource("img/ladder3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {
        // Starting Y can 5 to 7 inclusive
        int random_Y = rand.nextInt(3) + 5;
        set_Y(random_Y);
        // Start X can be 1 to 9 inclusive
        int random_X = rand.nextInt(9) + 1;
        set_X(random_X);
        
        // End Y is less 3 squares
        set_end_Y(random_Y - 3);
        // End X is less 1 square
        set_end_X(random_X - 1);
    }

    public BufferedImage getImage() {
        return ladder3;
    }

    public int adjustedX(int x) {
        return x - 45;
    }

    public int adjustedY(int y) {
        return y - 157;
    }

    public String toString() {
        return "Ladder 3 | Black Ladder";
    }
}