import javax.swing.*;
import java.awt.*;

abstract public class StationaryObject extends JPanel {
    protected Point position;
    protected int altitude;
    protected int parametr;
    private boolean colliding = false;

    public int getWidth() {
        return parametr;
    }

    public int getHeight() {
        return parametr;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    public boolean isColliding() {
        return this.colliding;
    }

    public StationaryObject(Point position, int altitude, int parametr) {
        this.position = position;
        this.altitude = altitude;
        this.parametr = parametr;
    }

    @Override
    public void paintComponent(Graphics g) { }
}
