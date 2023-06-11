import java.awt.*;
import java.awt.geom.Path2D;

public class Tree extends StationaryObject {
    public Tree(Point position, double height, int parametr) {
        super(position, height, parametr);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(isColliding() ? Color.RED : new Color(199, 154, 1));
        g2D.fillRect((int) position.getX(), (int) position.getY(), parametr, parametr);
    }
}
