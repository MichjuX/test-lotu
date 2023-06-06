import java.awt.*;
import java.awt.geom.Path2D;

public class Tree extends StationaryObject {
    public Tree(Point position, double height, int parametr) {
        super(position, height, parametr);
    }

    public double getHeihgt() {
        return height;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        int sides = 10;

        double angle = 2 * Math.PI / sides;
        Path2D tree = new Path2D.Double();
        tree.moveTo(position.getX() + parametr * Math.cos(0), position.getY() + parametr * Math.sin(0));
        for (int i = 1; i <= sides; i++) {
            double x = position.getX() + parametr * Math.cos(angle * i);
            double y = position.getY() + parametr * Math.sin(angle * i);
            tree.lineTo(x, y);
        }
        tree.closePath();
        g2D.setPaint(new Color(199, 154, 1));
        g2D.draw(tree);
    }
}
