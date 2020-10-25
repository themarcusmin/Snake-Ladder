import java.awt.*;
import javax.swing.*;

public class Square extends JPanel {
    public Square(int N) {
        setLayout(new BorderLayout());
        setVisible(true);

        // Create label for numbers
        JLabel sqNumber = new JLabel(String.valueOf(N));
        sqNumber.setFont(new Font("MONOSPACED", Font.BOLD, 24));
        sqNumber.setHorizontalAlignment(JLabel.CENTER);

        // Color even number white
        if (N % 2 == 0) {
            sqNumber.setForeground(Color.WHITE);
        }
        
        // Add label tothe square
        add(sqNumber);
    }

    public void updateColor(Color c) {
        setBackground(c);
    }

    // @Override
    // public void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     g.setColor(Color.BLACK);
    // }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    } 
}