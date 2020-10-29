import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Board1 extends JPanel {

    private JPanel[][] squares = new JPanel[10][10];
    private int N = 100;
    private Square sq;
    private int loc_Y = 9;
    private int loc_X = 0;
    private JLabel PIECE = new JLabel(new ImageIcon("img/chess-piece.png"));
    private JLabel PIECE_2 = new JLabel(new ImageIcon("img/horse.png"));
    // Hold current dice number
    private int diceNumber;

    //test13
    private Mover mover = new Mover();

    public Board1() {
        // Layout for squares
        setLayout(new GridLayout(0, 10));

        // Border
        setBorder(new LineBorder(Color.BLACK));

        // For each square of squares[][], add color and text
        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                // JPanel square = new JPanel();
                sq = new Square(N);

                // Square coloring
                if ((row % 2 != 0 && col % 2 != 0) || (row % 2 == 0 && col % 2 == 0)) {
                    sq.updateColor(new Color(139, 69, 19));
                }
                else {
                    sq.updateColor(Color.LIGHT_GRAY);
                }

                // Every even row, decrease number
                if (row % 2 == 0) {
                    N--;
                    // Less 9 at end
                    if (col == 9) {
                        N -= 9;
                    }
                }
                // Every odd row, increase number
                else {
                    N++;
                    // Less 11 at end
                    if (col == 9) {
                        N -= 11;
                    }
                }
                squares[row][col] = sq;
            }
        }

        // Add all squares to the board
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                add(squares[row][col]);
            }
        }

        // Starter location for the piece at [9][0]
        squares[loc_Y][loc_X].add(PIECE);
        // TEST: add second player
        squares[loc_Y][loc_X].add(PIECE_2, BorderLayout.LINE_START);
        // squares[loc_Y][loc_X].remove(2);
        System.out.println(squares[loc_Y][loc_X].getComponentCount());
    }
    /*
     *  Clear image at former Location
     *  - Store the number labelling
     *  - Remove the chess_piece_image of current location (Also remove the number)
     *  - Add the number labelling and set it visible
     *  - Refresh the component
     */
    private void clearImage() {
        //Former Location
        // Store number labelling in temp
        Component temp = squares[loc_Y][loc_X].getComponents()[0];
        // Remove the image of the piece at index 1 (side effect: hide the number too)
        squares[loc_Y][loc_X].remove(1);
        // Add the stored number labelling, set it visible and refresh
        squares[loc_Y][loc_X].add(temp);
        squares[loc_Y][loc_X].getComponents()[0].setVisible(true);
        squares[loc_Y][loc_X].revalidate();
    }

    /*
     *  Add image at new Location
     *  - Hide the number labelling
     *  - Add the chess_piece_image
     */
    private void addImage() {
        // New Location
        // Hide the number labelling
        squares[loc_Y][loc_X].getComponents()[0].setVisible(false);
        // Add the image of the piece and refresh
        squares[loc_Y][loc_X].add(PIECE);
        squares[loc_Y][loc_X].revalidate();
    }

    /*
     * Move the piece by a square per action
     * DESCRIPTION:
     *  Former Location
     *  - Clear Image
     *  Update Location (X, Y)
     *  - For odd row number Y,
     *    - Less Y if end (X is 9)
     *    - Add X otherwise
     *  - For even row number Y,
     *    - Less Y if start (X is 0)
     *    - Less X otherwise
     *  New Location
     *  - Add Image
     */
    public void moveOneSquare() {
        System.out.println("Moving one square");
        clearImage();
        // Update Location
        // Y is even
        if (loc_Y % 2 == 0)
        {
            // Start of X, less Y
            if (loc_X == 0) loc_Y--;
            // Else, minus X
            else loc_X--;
        }
        // Y is odd
        else
        {
            // End of X, less Y 
            if (loc_X == 9) loc_Y--;
            // Else, add X
            else loc_X++;
        }
        addImage();
    }

    /*
     * ActionListener executes moving 1 square, stops when counter reaches diceNumber
     */
    private class Mover implements ActionListener {
        // Count the number of squres to move
        int count = 0;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Increment count per square-move
            moveOneSquare();
            count++;
            if (count == diceNumber) {
                // Reset count and stop timer
                count = 0;
                Timer timer = (Timer) e.getSource();
                timer.stop();
                // LOGIC for snake and ladder PATH
                // TEST: stop (if get loc_Y and loc_X >> move up or down)
                clearImage();
                // TEST coordinate
                loc_Y = 9;
                loc_X = 1;
                addImage();
            }
        }
    }

    /*
     * Update diceNumber and return ActionListener to move piece
     * @param diceNumber
     * @return
     */
    public ActionListener move(int diceNumber)
    {
        System.out.println("TEST: dicenumber " + diceNumber);
        this.diceNumber = diceNumber;
        return mover;
    }
}