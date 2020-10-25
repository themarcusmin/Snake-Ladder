
import java.awt.*;

import javax.swing.*;

public class Menu extends JPanel {

    public Menu(JLabel roll) {
        // background and size of the panel
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(200, 640));

        // Create a box to store components
        Box box = Box.createVerticalBox();
        
        // Spacing
        box.add(Box.createVerticalStrut(25));
        
        // Add title
        JLabel title = new JLabel("Dice Roller");
        box.add(title);

        // Spacing
        box.add(Box.createVerticalStrut(150));

        // Component to roll dice
        box.add(roll, BorderLayout.CENTER);

        // Add box to the Menu Panel
        add(box);
    }
}