import java.awt.*;


public class Building extends StationaryObject {
    public Building(Point position, int altitude, int parametr) {
        super(position, altitude, parametr);
    }

    public int getHeight() {
        return parametr + 50;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(isColliding() ? Color.RED : new Color(100, 255, 255));
        g2D.fillRect((int) position.getX(), (int) position.getY(), parametr, parametr + 50);
    }
}
