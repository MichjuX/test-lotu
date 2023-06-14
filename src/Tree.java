import java.awt.*;

public class Tree extends StationaryObject {
    public Tree(Point position, int height, int parametr) {
        super(position, height, parametr);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(199, 154, 1));
        g2D.fillRect((int) position.getX(), (int) position.getY(), size, size);
    }
}
