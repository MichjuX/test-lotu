import java.awt.*;


public class Building extends StationaryObject {
    public Building(Point position, int altitude, int size) {
        super(position, altitude, size);
    }

    public int getHeight() {
        return size + 50;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(100, 255, 255));
        g2D.fillRect((int) position.getX(), (int) position.getY(), size, size + 50);
    }
}
