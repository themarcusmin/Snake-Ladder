import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Map;
import java.util.HashMap;

public class Board extends JPanel {

    private JPanel[][] squares = new JPanel[10][10];
    private int N = 100;
    private Square sq;
    private int loc_Y = 9;
    private int loc_X = 0;
    private JLabel PIECE = new JLabel(new ImageIcon("img/chess-piece.png"));
    private JLabel PIECE_2 = new JLabel(new ImageIcon("img/horse.png"));
    // Store current dice number
    private int diceNumber;
    // test13 | document this
    private Mover mover = new Mover();
    // Map to store coordinates of each snake / ladder
    private Map<Coordinate, Coordinate> path = new HashMap<Coordinate, Coordinate>();
    // Snakes
    private Snake1 s1;
    private Snake2 s2;
    private Snake3 s3;
    private Snake4 s4;
    // Ladders
    private Ladder1 l1;
    private Ladder2 l2;
    private Ladder3 l3;
    private Ladder4 l4;

    public Board() {
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

        // Add Snake 1
        s1 = new Snake1();
        addToPath(s1);
        // Add Snake 2
        s2 = new Snake2();
        addToPath(s2);
        // Add Snake 3
        s3 = new Snake3();
        addToPath(s3);
        // Add Snake 4
        s4 = new Snake4();
        addToPath(s4);

        // Add Ladder 1
        l1 = new Ladder1();
        addToPath(l1);
        // Add Ladder 2
        l2 = new Ladder2();
        addToPath(l2);
        // Add Ladder 3
        l3 = new Ladder3();
        addToPath(l3);
        // Add Ladder 4
        l4 = new Ladder4();
        addToPath(l4);

    }

    /*
     * Store all starting and ending positions in hashmap to avoid overlapping coordinates
     * @param unit
     */
    private void addToPath(Unit unit) {
        // Instantiate starter positions
        Coordinate start = new Coordinate(unit.Y(), unit.X());
        Coordinate end = new Coordinate(unit.end_Y(), unit.end_X());

        // If starting and ending coordinates already existed, get new postions
        while (true) {
            if (!(path.containsKey(start)) && !(path.containsValue(end)) && !(path.containsKey(end)) && !(path.containsValue(start))) break;
            unit.rollRandom();
            start = new Coordinate(unit.Y(), unit.X());
            end = new Coordinate(unit.end_Y(), unit.end_X());
        }
        // Add to the path
        path.put(start, end);
        System.out.println(unit);
        System.out.println("start " + start);
        System.out.println("end " + end);
    }

    // Paint snakes and ladders on the board
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        /*
         * Snake 1 | Green snake
         *  - Adjust X and Y relative to image positioning
         *  - Draw image on board based on adjusted X and Y
         */
        int s1_x = s1.adjustedX(squares[s1.Y()][s1.X()].getX());
        int s1_y = s1.adjustedY(squares[s1.Y()][s1.X()].getY());
        g.drawImage(s1.getImage(), s1_x, s1_y, this);

        /*
         * Snake 2 | Pink snake
         */
        int s2_x = s2.adjustedX(squares[s2.Y()][s2.X()].getX());
        int s2_y = s2.adjustedY(squares[s2.Y()][s2.X()].getY());
        g.drawImage(s2.getImage(), s2_x, s2_y, this);
        
        /*
         * Snake 3 | Purple snake
         */
        int s3_x = s3.adjustedX(squares[s3.Y()][s3.X()].getX());
        int s3_y = s3.adjustedY(squares[s3.Y()][s3.X()].getY());
        g.drawImage(s3.getImage(), s3_x, s3_y, this);

        /*
         * Snake 4 | Yellow snake
         */
        int s4_x = s4.adjustedX(squares[s4.Y()][s4.X()].getX());
        int s4_y = s4.adjustedY(squares[s4.Y()][s4.X()].getY());
        g.drawImage(s4.getImage(), s4_x, s4_y, this);

        /*
         * White ladder
         */
        int l1_x = l1.adjustedX(squares[l1.Y()][l1.X()].getX());
        int l1_y = l1.adjustedY(squares[l1.Y()][l1.X()].getY());
        g.drawImage(l1.getImage(), l1_x, l1_y, this);

        /*
         * Yellow ladder
         */
        int l2_x = l2.adjustedX(squares[l2.Y()][l2.X()].getX());
        int l2_y = l2.adjustedY(squares[l2.Y()][l2.X()].getY());
        g.drawImage(l2.getImage(), l2_x, l2_y, this);
        
        /*
         * Black ladder
         */
        int l3_x = l3.adjustedX(squares[l3.Y()][l3.X()].getX());
        int l3_y = l3.adjustedY(squares[l3.Y()][l3.X()].getY());
        g.drawImage(l3.getImage(), l3_x, l3_y, this);

        /*
         * Tall ladder
         */
        int l4_x = l4.adjustedX(squares[l4.Y()][l4.X()].getX());
        int l4_y = l4.adjustedY(squares[l4.Y()][l4.X()].getY());
        g.drawImage(l4.getImage(), l4_x, l4_y, this);
    
    }

    /*
     *  Clear image at former square
     *  - Store the number labelling
     *  - Remove the chess_piece_image of current location (Also remove the number)
     *  - Add the number labelling and set it visible
     *  - Refresh the component
     */
    private void clearImage() {
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
     *  Add image at new square
     *  - Hide the number labelling
     *  - Add the chess_piece_image
     */
    private void addImage() {
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
    private void moveOneSquare() {
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
                // if Y and X in DS, clearimage().. reset Y and X
                // if pathing(x, y) then clearImage(), loc_Y = and loc_X = , addImage()
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