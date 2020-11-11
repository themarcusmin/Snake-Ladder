/*
 *  Pink snake
 *  - Starting coord and ending coord
 *  - Adjused X and Y relative to image positioning
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Snake2 extends Unit {

    private BufferedImage snake2;

    // Read image & set coordinates
    public Snake2() {
        try {
            snake2 = ImageIO.read(getClass().getResource("img/snake2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {

        // Starting Y can 0 to 4 inclusive
        int random_Y = rand.nextInt(5);
        set_Y(random_Y);
        // Start X can be 1 to 5 inclusive
        int random_X = rand.nextInt(6) + 1;
        set_X(random_X);

        // End X is 3 squares more
        set_end_Y(random_Y + 3);
        // End Y is 3 squares more
        set_end_X(random_X + 3);
    }

    public BufferedImage getImage() {
        return snake2;
    }

    public int adjustedX(int x) {
        return x + 30;
    }

    public int adjustedY(int y) {
        return y + 15;
    }

    public String toString() {
        return "Snake 2 | Pink Snake";
    }
}