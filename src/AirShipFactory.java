import java.awt.*;

public class AirShipFactory {
    public AirShip createAirShip(Class<? extends AirShip> type, Point position, int height, int width)
    {
        if (type.equals(Plane.class)) {
            return new Plane(position, height, width);
        } else if (type.equals(Helicopter.class)) {
            return new Helicopter(position, height, width);
        } else if (type.equals(Balloon.class)) {
            return new Balloon(position, height, width);
        } else if (type.equals(Glider.class)) {
            return new Glider(position, height, width);
        } else {
            throw new IllegalArgumentException("Invalid airship type");
        }
    }
}
