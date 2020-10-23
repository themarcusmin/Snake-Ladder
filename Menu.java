
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Random;

public class Menu extends JPanel {

    public Menu() {
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(250, 640));

        // Create a box to store components
        Box box = Box.createVerticalBox();
        
        // Add title
        box.add(new JLabel("Snake Pit"));

        // Spacing
        box.add(Box.createVerticalStrut(200));

        // Create a button to r oll and add it to the Menu
        JButton roll = new JButton("ROLL");
        box.add(roll);

        // Call 'Random' API for dice
        Random rand = new Random();

        // Message to show the dice number
        JLabel dice_number = new JLabel();
        box.add(dice_number);

        // Event to show message upon click
        roll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = rand.nextInt(6) + 1;
                dice_number.setText(String.valueOf(x));
            }
        });

        add(box);
    }
}