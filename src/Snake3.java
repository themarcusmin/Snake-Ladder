/*
 *  Purple snake
 *  - Starting coord and ending coord
 *  - Adjused X and Y relative to image positioning
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Snake3 extends Unit {

    private BufferedImage snake3;

    // Read image & set coordinates
    public Snake3() {
        try {
            snake3 = ImageIO.read(getClass().getResource("img/snake3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {
        // Starting Y can 1 to 5 inclusive
        int random_Y = rand.nextInt(5) + 1;
        set_Y(random_Y);
        // Start X can be 0 to 6 inclusive
        int random_X = rand.nextInt(7);
        set_X(random_X);
        
        // End Y is 4 squares more
        set_end_Y(random_Y + 4);
        // End X is 3 squares more
        set_end_X(random_X + 3);
    }

    public BufferedImage getImage() {
        return snake3;
    }

    public int adjustedX(int x) {
        return x + 33;
    }

    public int adjustedY(int y) {
        return y + 6;
    }

    public String toString() {
        return "Snake 3 | Purple Snake";
    }
}