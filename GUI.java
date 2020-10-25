import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GUI {

    // Board
    private Board1 board;

    // Image container
    private JLabel roll;

    // Toggle dice images
    private boolean not_rolling = true;

    // Array to store dice images
    private ImageIcon[] DICES = new ImageIcon[7];

    // API to roll random
    private Random rand = new Random();

    // Store diceNumber
    private int diceNumber;

    // Load dice images into the array
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
        roll = new JLabel(DICES[0]);

        // OnClick event on roll
        roll.addMouseListener(new rollListener());

        // Get Board
        board = new Board1(diceNumber);

        // Get Side Menu
        Menu menu = new Menu(roll);

        // Add Board and Side Menu to the frame
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(menu, BorderLayout.EAST);

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }

    private class rollListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent me)
        {
            // Get dice rolling gif
            if (not_rolling)
            {
                Icon dice_gif = new ImageIcon("img/dice.gif");
                roll.setIcon(dice_gif);
                not_rolling = false;
            }
            // Get random dice number
            else {
                diceNumber = rand.nextInt(6) + 1;
                System.out.println(diceNumber);
                roll.setIcon(DICES[diceNumber]);
                not_rolling = true;
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