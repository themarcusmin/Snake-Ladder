import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Map;
import java.util.HashMap;

public class Board extends JPanel {

    private static final long serialVersionUID = 10l;

    private JPanel[][] squares = new JPanel[10][10];
    private int N = 100;
    private Square sq;
    private int loc_Y;
    private int loc_X;

    // Players
    private JLabel PAWN = new JLabel(new ImageIcon("img/chess-piece.png"));
    private JLabel KNIGHT = new JLabel(new ImageIcon("img/horse.png"));
    private Player player1 = new Player(PAWN);
    private Player player2 = new Player(KNIGHT);
    // Track each player's turn
    private Player currentPlayer;
    private JLabel currentIcon;

    // Bounce backward at 100
    private boolean backward = false;
    // Store current dice number
    private int diceNumber;
    // Mover moves players' icons
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

        // Paint square 100 to indicate winning
        squares[0][0].setBackground(new Color(128, 202, 113));
        squares[0][0].setBorder(new LineBorder(Color.WHITE, 2, true));

        // Add all squares to the board
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                add(squares[row][col]);
            }
        }
        // New Game
        setupPlayer();

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

    /**
     * Set players for a new game / Restart game @ game over
     *  - Declare first player
     *  - Add icons at [9][0]
     */
    private void setupPlayer() {
        loc_Y = 9;
        loc_X = 0;
        player1.reset();
        player2.reset();
        // Declare current player
        currentPlayer = player1;
        currentIcon = currentPlayer.getIcon();
        // Set players' icons at [9][0]
        squares[loc_Y][loc_X].add(currentIcon);
        squares[loc_Y][loc_X].add(player2.getIcon(), BorderLayout.LINE_START);
        squares[loc_Y][loc_X].revalidate();
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
     *  - Remove current icon component
     */
    private void clearImage() {
        squares[loc_Y][loc_X].remove(currentIcon);
        squares[loc_Y][loc_X].revalidate();
        // Repaint snakes and ladders
        repaint();
    }

    /*
     *  Add image at new square
     *  - Get number of icons at new square
     *  - Add current icon based on the iconsCount
     */
    private void addImage() {
        int iconsCount = squares[loc_Y][loc_X].getComponentCount();
        squares[loc_Y][loc_X].add(currentIcon, (iconsCount == 1) ? BorderLayout.LINE_START : null);
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
     * Bounce back icons at [0][0]
     * Occurs at Y = 0 and increments X
     */
    private void moveOneSquareBackward() {
        clearImage();
        loc_X++;
        addImage();
    }

    /*
     * ActionListener Mover
     *  - Moves 1 square per action and halts when incremening counter reaches diceNumber
     *  - If square is 100 / [0][0], bounce pieces backward 
     *  - Else, move pieces forward
     *  - After timer stops or action halted, check coordinates
     *  - If coordinates is at snake's head ot ladder's tail, move along the path
     *  - Store x and y of player
     *  - Switch player and icon
     */
    private class Mover implements ActionListener {
        // Count the number of squres to move
        int count = 0;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Toggle backward on
            if (loc_Y == 0 && loc_X == 0) backward = true;
            // Forward or backward
            if (backward) {
                moveOneSquareBackward();
            } else {
                moveOneSquare();
            }
            count++;
            // All squares have been accounted
            if (count == diceNumber) {
                // Reset backward
                backward = false;
                // Reset count and stop timer
                count = 0;
                Timer timer = (Timer) e.getSource();
                timer.stop();

                Coordinate currentCoord = new Coordinate(loc_Y, loc_X);
                // Game Over At [0][0]
                if (currentCoord.getY() == 0 && currentCoord.getX() == 0) {
                    gameOver(currentPlayer);
                    return;
                }
                // Find out if current coord is the starting path: move piece to new coord
                if (path.containsKey(currentCoord)) {
                    clearImage();
                    Coordinate newCoord = path.get(currentCoord);
                    loc_Y = newCoord.getY();
                    loc_X = newCoord.getX();
                    addImage();
                }
                // Store Y & X in currentPlayer
                currentPlayer.set_coordinates(loc_Y, loc_X);
                // Switch player & icon
                currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
                currentIcon = currentPlayer.getIcon();
                loc_Y = currentPlayer.get_Y();
                loc_X = currentPlayer.get_X();
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
        this.diceNumber = diceNumber;
        return mover;
    }

    /**
     * Game Over Prompt: new game or quit game
     */
    private void gameOver(Player winner) {
        Object gameOptions[] = { "New Game", "Quit" };
        int response = JOptionPane.showOptionDialog(this, winner + " has made it out alive!", "GAME OVER",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, gameOptions,
                        null);
        if (response == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            System.exit(0);
        }
    }

    /**
     * Start a new game
     */
    private void newGame() {
        setupPlayer();
    }
}