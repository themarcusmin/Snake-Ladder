/*
 *  Yellow Snake
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Snake4 extends Unit {

    private BufferedImage snake4;

    // Initiate snake image & set coordinates
    public Snake4() {
        // Read pink snake image
        try {
            snake4 = ImageIO.read(getClass().getResource("img/snake4.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rollRandom();
    }

    // Randomize starting x, y and calculate ending x, y
    @Override
    public void rollRandom() {
        // Starting Y can 4 to 7 inclusive
        int random_Y = rand.nextInt(4) + 4;
        set_Y(random_Y);
        // Start X can be 3 to 9 inclusive
        int random_X = rand.nextInt(7) + 3;
        set_X(random_X);
        
        // End Y is 2 squares more
        set_end_Y(random_Y + 2);
        // End X is less 3 squares
        set_end_X(random_X - 3);
    }

    public BufferedImage getImage() {
        return snake4;
    }

    public int adjustedX(int x) {
        return x - 140;
    }

    public int adjustedY(int y) {
        return y - 40;
    }

    public String toString() {
        return "Snake 4 | Yellow Snake";
    }
}