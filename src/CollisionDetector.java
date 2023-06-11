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
        double stationaryObjectLeft = stationaryObject.position.x - (double)stationaryObject.getWidth() / 2;
        double stationaryObjectRight = stationaryObject.position.x + (double)stationaryObject.getWidth() / 2;
        double stationaryObjectTop = stationaryObject.position.y - (double)stationaryObject.getHeight() / 2;
        double stationaryObjectBottom = stationaryObject.position.y + (double)stationaryObject.getHeight() / 2;


        double airShipLeft = airShip.currentPos.x - (double)airShip.getAirshipWidth() / 2;
        double airShipRight = airShip.currentPos.x + (double)airShip.getAirshipWidth() / 2;
        double airShipTop = airShip.currentPos.y - (double)airShip.getAirShipHeight() / 2;
        double airShipBottom = airShip.currentPos.y + (double)airShip.getAirShipHeight() / 2;

        // Check for a collision
        return (stationaryObjectRight > airShipLeft
                && stationaryObjectLeft < airShipRight
                && stationaryObjectBottom > airShipTop
                && stationaryObjectTop < airShipBottom);
    }
    public void detectCollisions() {
        for (StationaryObject stationaryObject : stationaryObjects) {
            for (AirShip airShip : airShips) {
                boolean isColliding = isColliding(stationaryObject, airShip);
                stationaryObject.setColliding(isColliding);
                airShip.setColliding(isColliding);
            }
        }
    }
}
