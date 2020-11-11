/*
 *  Tall Ladder
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Ladder4 extends Unit {

    private BufferedImage ladder4;

    // Read image & set coordinates
    public Ladder4() {
        try {
            ladder4 = ImageIO.read(getClass().getResource("img/ladder4.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {
        // Starting Y can 5 to 8 inclusive
        int random_Y = rand.nextInt(3) + 5;
        set_Y(random_Y);
        // Start X can be 1 to 9 inclusive
        int random_X = rand.nextInt(8);
        set_X(random_X);
        
        // End Y is less 5 squares
        set_end_Y(random_Y - 5);
        // End X is 2 squares more
        set_end_X(random_X + 2);
    }

    public BufferedImage getImage() {
        return ladder4;
    }

    public int adjustedX(int x) {
        return x;
    }

    public int adjustedY(int y) {
        return y - 270;
    }

    public String toString() {
        return "Ladder 4 | Tall Ladder";
    }
}