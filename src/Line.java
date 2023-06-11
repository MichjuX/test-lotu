import java.awt.*;
import javax.swing.*;

public class Line extends JPanel {
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(255, 255, 255));
        g2D.drawLine(900, 500, 900, 600);
    }
}
