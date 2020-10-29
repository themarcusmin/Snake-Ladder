import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GUI {
    // API to roll random dice
    private Random rand = new Random();

    // Board
    private Board1 board;

    // Menu
    private Menu menu;
    
    // Dice Rolling-related
    private ImageIcon[] DICES = new ImageIcon[7];
    private JLabel rollImage;
    private boolean not_rolling = true;
    private int diceNumber;
    private PieceMover pieceMover;

    // Load dice images into the array DICES
    public void loadDiceImages()
    {
        DICES[0] = new ImageIcon("img/dice.png");
        DICES[1] = new ImageIcon("img/one.png");
        DICES[2] = new ImageIcon("img/two.png");
        DICES[3] = new ImageIcon("img/three.png");
        DICES[4] = new ImageIcon("img/four.png");
        DICES[5] = new ImageIcon("img/five.png");
        DICES[6] = new ImageIcon("img/six.png");
    }

    /*
     * GUI
     *  - Setup decorated UI
     *  - Setup JFrame with title and dimension
     *  - Add 7 dice images to the array (idle dice image + 6 dice images)
     *  - Setup JLabel:rollImage to display images
     *  - Add board to the frame
     *  - Add menu with JLabel:rollImage to the frame
     */
    public void createAndShowGUI()
    {
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Snake & Ladder 2");
        frame.setPreferredSize(new Dimension(800, 640));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load images
        loadDiceImages();

        // Starter component
        rollImage = new JLabel(DICES[0]);

        // Listen to mouse click on rollImage
        rollImage.addMouseListener(new rollListener());

        // Get Board
        board = new Board1();

        // Get Side Menu, adding the component to hold the image
        menu = new Menu(rollImage);

        // Add Board and Side Menu to the frame
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(menu, BorderLayout.EAST);

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }

    /*
     * rollListener reacts to mouse click on the DICE-image of menu
     *  - Listener shows the gif for first click
     *  - Listener shows the dice image for second click & move piece
     *  - Repeat
     */
    private class rollListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent me)
        {
            // Rolling Dice
            if (not_rolling)
            {
                Icon dice_gif = new ImageIcon("img/dice.gif");
                rollImage.setIcon(dice_gif);
                not_rolling = false;
            }
            // Display the dice number and move piece
            else {
                // Assign random number to diceNumber
                diceNumber = rand.nextInt(6) + 1;
                // Update dice images upon new random number
                rollImage.setIcon(DICES[diceNumber]);
                not_rolling = true;
                // PieceMover move 1 square (repeat) at a time with 0.5 s delay, actionlistener taking param:diceNumber threshold
                pieceMover = new PieceMover(500, board.move(diceNumber));
                // Start running timer
                pieceMover.start();
            }
        }
    };

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI g = new GUI();
                g.createAndShowGUI();
            }
        });
    }
}