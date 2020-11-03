import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Square extends JPanel {

    private static final long serialVersionUID = 10l;
    
    private int N;

    public Square(int N) {
        setLayout(new BorderLayout());
        setVisible(true);
        this.N = N;
    }

    public void updateColor(Color c) {
        setBackground(c);
    }

    // Paint number on square with adjusted X & Y
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String number = String.valueOf(N);

        // Code Snippet by Mad Programmer
        // https://stackoverflow.com/questions/23729944/java-how-to-visually-center-a-specific-string-not-just-a-font-in-a-rectangle
        Graphics2D g2d = (Graphics2D) g.create();
        FontRenderContext context = g2d.getFontRenderContext();

        Font font = new Font("MONOSPACED", Font.BOLD, 20);
        TextLayout txt = new TextLayout(number, font, context);
        Rectangle2D bounds = txt.getBounds();
        int x = (int) ((getWidth() - (int) bounds.getWidth()) / 2);
        int y = (int) ((getHeight() - (bounds.getHeight() - txt.getDescent())) / 2);
        y += txt.getAscent() - txt.getDescent();

        // Even number as white, odd number as black
        Color color = (N % 2 == 0) ? Color.WHITE : Color.BLACK;
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(number, x, y);
    }
}