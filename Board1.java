import javax.swing.*;
import javax.swing.border.*;

import java.util.Arrays;
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

    public Board1(int diceNumber) {
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

        /*
         * Move the piece by a square per action
         * DESCRIPTION:
         *  Former Location
         *  - Store the number labelling
         *  - Remove the chess_piece_image of current location (Also remove the number)
         *  - Add the number labelling and set it visible
         *  - Refresh the component
         *  Update Location (X, Y)
         *  - For odd row number Y,
         *    - Less Y if end (X is 9)
         *    - Add X otherwise
         *  - For even row number Y,
         *    - Less Y if start (X is 0)
         *    - Less X otherwise
         *  New Location
         *  - Hide the number labelling
         *  - Add the chess_piece_image
         */
        ActionListener action = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Action");
                // Former Location
                // Store number labelling in temp
                Component temp = squares[loc_Y][loc_X].getComponents()[0];
                // Remove the image of the piece at index 1 (side effect: hide the number too)
                squares[loc_Y][loc_X].remove(1);
                // Add the stored number labelling, set it visible and refresh
                squares[loc_Y][loc_X].add(temp);
                squares[loc_Y][loc_X].getComponents()[0].setVisible(true);
                squares[loc_Y][loc_X].revalidate();
                
                // Update Location
                // Y is even
                if (loc_Y % 2 == 0) {
                    // Start of X, less Y
                    if (loc_X == 0) loc_Y--;
                    // Else, minus X
                    else loc_X--;
                }
                // Y is odd
                else {
                    // End of X, less Y 
                    if (loc_X == 9) loc_Y--;
                    // Else, add X
                    else loc_X++;
                }

                // New Location
                // Hide the number labelling
                squares[loc_Y][loc_X].getComponents()[0].setVisible(false);
                // Add the image of the piece and refresh
                squares[loc_Y][loc_X].add(PIECE);
                squares[loc_Y][loc_X].revalidate();
            }
        };

        //Timer timer = new Timer(1000, action);
        Timer timer = new Timer(1000, new Test());
        timer.setInitialDelay(150);
        timer.setRepeats(false);

        // test button
        JButton btn = new JButton("ST");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        squares[1][1].add(btn);
    }
}