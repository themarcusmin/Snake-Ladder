/*
 * Green snake
 *  - Starting coord and ending coord
 *  - Adjused X and Y relative to image positioning
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Snake1 extends Unit {

    private BufferedImage snake1;

    // Read image & set coordinates
    public Snake1() {
        try {
            snake1 = ImageIO.read(getClass().getResource("img/snake1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {
        // Starting Y can 0 to 7 inclusive
        int random_Y = rand.nextInt(8);
        set_Y(random_Y);
        // Start X can be 2 to 9 inclusive
        int random_X = rand.nextInt(8) + 2;
        set_X(random_X);

        // End Y is 2 squares more
        set_end_Y(random_Y + 2);
        // End X is less 2 squares
        set_end_X(random_X - 2);
    }

    public BufferedImage getImage() {
        return snake1;
    }

    public int adjustedX(int x) {
        return x - 75;
    }

    public int adjustedY(int y) {
        return y + 40;
    }

    public String toString() {
        return "Snake 1 | Green Snake";
    }
}