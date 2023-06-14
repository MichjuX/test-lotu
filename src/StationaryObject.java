import javax.swing.*;
import java.awt.*;

abstract public class StationaryObject extends JPanel {
    protected Point position;
    protected int altitude;
    protected int size;

    public int getWidth() {
        return size;
    }

    public int getHeight() {
        return size;
    }
    public int getAltitude() {
        return altitude;
    }

    public StationaryObject(Point position, int altitude, int size) {
        this.position = position;
        this.altitude = altitude;
        this.size = size;
    }
    @Override
    public void paint(Graphics g) { }
}
