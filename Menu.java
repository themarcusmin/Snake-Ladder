
import java.awt.*;

import javax.swing.*;

public class Menu extends JPanel {

    private static final long serialVersionUID = 10l;

    public Menu(JLabel roll, JLabel announcer) {
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
        box.add(Box.createVerticalStrut(120));

        // Add announcer to indicate players' turn
        box.add(announcer);

        // Spacing
        box.add(Box.createVerticalStrut(80));
        
        // Component to roll dice
        box.add(roll, BorderLayout.CENTER);
        // Add box to the Menu Panel
        add(box);
    }
}