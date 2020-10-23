import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

public class GUI {
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Snake & Ladder");
        frame.setPreferredSize(new Dimension(800, 640));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JLabel emptyLabel = new JLabel("t");
        // emptyLabel.setPreferredSize(new Dimension(200, 100));
        // emptyLabel.setBackground    (Color.ORANGE);;

        Board board = new Board();
        Menu menu = new Menu();
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(menu, BorderLayout.EAST);

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}