import java.util.HashMap;
import java.util.List;
public class CollisionDetector {
    private List<StationaryObject> stationaryObjects;
    private List<AirShip> airShips;
    public CollisionDetector(List<StationaryObject> stationaryObjects, List<AirShip> airShips)
    {
        this.stationaryObjects = stationaryObjects;
        this.airShips = airShips;
    }
    //prawdopodobnie tutaj jest spierdolone vvvv
    public boolean isColliding(StationaryObject stationaryObject,AirShip airShip) {
        double stationaryObjectLeft = stationaryObject.position.getX() - (double)stationaryObject.getWidth() / 2;
        double stationaryObjectRight = stationaryObject.position.getX() + (double)stationaryObject.getWidth() / 2;
        double stationaryObjectTop = stationaryObject.position.getY() - (double)stationaryObject.getHeight() / 2;
        double stationaryObjectBottom = stationaryObject.position.getY() + (double)stationaryObject.getHeight() / 2;

        double airShipLeft = airShip.getX() - (double)airShip.getWidth() / 2;
        double airShipRight = airShip.getX() + (double)airShip.getWidth() / 2;
        double airShipTop = airShip.getY() - (double)airShip.getHeight() / 2;
        double airShipBottom = airShip.getY() + (double)airShip.getHeight() / 2;

        // Check for a collision
        return (stationaryObjectRight > airShipLeft
                && stationaryObjectLeft < airShipRight
                && stationaryObjectBottom > airShipTop
                && stationaryObjectTop < airShipBottom);
    }
}
