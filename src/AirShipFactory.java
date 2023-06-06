import java.awt.*;

public class AirShipFactory {
    public AirShip createAirShip(Class<? extends AirShip> type, Point position)
    {
        if (type.equals(Plane.class)) {
            return new Plane(position);
        } else if (type.equals(Helicopter.class)) {
            return new Helicopter(position);
        } else if (type.equals(Balloon.class)) {
            return new Balloon(position);
        } else if (type.equals(Glider.class)) {
            return new Glider(position);
        } else {
            throw new IllegalArgumentException("Invalid airship type");
        }
    }
}
