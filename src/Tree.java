import java.awt.*;

public class Tree extends StationaryObject {
    public Tree(Point position, int height, int parametr) {
        super(position, height, parametr);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(isColliding() ? Color.RED : new Color(199, 154, 1));
        g2D.fillRect((int) position.getX(), (int) position.getY(), parametr, parametr);
    }
}
