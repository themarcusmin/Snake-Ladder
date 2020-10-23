import javax.swing.*;

import java.awt.*;

public class Board extends JPanel {

    private JPanel[][] squares = new JPanel[10][10];
    private int N = 100;

    public Board() {
        // Layout for squares
        setLayout(new GridLayout(0, 10));

        // Border
        setBorder(new LineBorder(Color.BLACK));

        // For each square of squares[][], add color and text
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                JPanel square = new JPanel();
            
                if ((row % 2 != 0 && col % 2 != 0) || (row % 2 == 0 && col % 2 == 0)) {
                    square.setBackground(new Color(139, 69, 19));
                }

                // NUMBERING
                // Layout to center text
                square.setLayout(new BorderLayout());

                // Label square with number
                JLabel num_Label = new JLabel(String.valueOf(N));
                num_Label.setFont(new Font("MONOSPACED", Font.BOLD, 24));
                
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
                
                // Change color of font to white
                if ((row % 2 != 0 && col % 2 != 0) || (row % 2 == 0 && col % 2 == 0)) {
                    num_Label.setForeground(Color.WHITE);
                }
                // , BorderLayout.CENTER
                num_Label.setHorizontalAlignment(JLabel.CENTER);
                square.add(num_Label);

                squares[row][col] = square;
                // Add square to the squares
                //add(square);
            }
        }

        // Add all squares to the board
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                add(squares[row][col]);
            }
        }
        //System.out.println(squares[9][0]);
        //System.out.println("New");
        JLabel jb = new JLabel(new ImageIcon(this.getClass().getResource("chess-piece.png")));
        squares[9][0].add(jb);
        //System.out.println(squares[9][0]);
        // Component[] clist = squares[9][0].getComponents();
        // for (Component c: clist) {
        //     if (c instanceof JButton) {
        //         squares[9][0].remove(c);
        //     };
        // }
        squares[9][0].remove(jb);
        squares[9][0].revalidate();
        //sqaures[9][0].remove();
        // int len = squares[9][0].getComponents().length;
        // squares[9][0].remove(squares[9][0].getComponent(1));
    }

    // @Override
    // public void paint(Graphics g) {
    //     Graphics2D g2d = (Graphics2D) g;
    //     g2d.setColor(Color.RED);
    //     g2d.fillRect(0, 0, len, len);
    //     g2d.setColor(Color.WHITE);
    //     g2d.drawString(String.valueOf(100), 0 + 15, 0 + 30);

    //     g2d.setColor(Color.MAGENTA);
    //     g2d.fillRect(50, 0, len, len);
    // }
}