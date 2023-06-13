import java.util.HashMap;
import java.util.List;

public class CollisionDetector {
    private List<StationaryObject> stationaryObjects;
    private List<AirShip> airShips;

    public CollisionDetector(List<StationaryObject> stationaryObjects, List<AirShip> airShips) {
        this.stationaryObjects = stationaryObjects;
        this.airShips = airShips;
    }

    //prawdopodobnie tutaj jest spierdolone vvvv
    public boolean isColliding(StationaryObject stationaryObject, AirShip airShip) {
        /*double stationaryObjectLeft = stationaryObject.position.x - (double)stationaryObject.getWidth() / 2;
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
                && stationaryObjectTop < airShipBottom);*/
        double stationaryObjectCenterX = stationaryObject.position.x + (((double) stationaryObject.parametr) / 2);
        double stationaryObjectCenterY = stationaryObject.position.y + (((double) stationaryObject.parametr) / 2);
        double stationaryObjectHalfDiag = Math.sqrt(((double) stationaryObject.parametr / 2) * ((double) stationaryObject.parametr / 2)
                + ((double) stationaryObject.parametr / 2) * ((double) stationaryObject.parametr / 2));

        double airShipCenterX = airShip.currentPos.x;
        double airShipCenterY = airShip.currentPos.y;
        double airShipHalfDiag = Math.sqrt((double) ((airShip.getAirShipHeight() * airShip.getAirShipHeight()) / 4)
                + ((double) (airShip.getAirshipWidth() * airShip.getAirshipWidth()) / 4));
        double correctDistance = stationaryObjectHalfDiag + airShipHalfDiag;
        double distance = Math.sqrt((Math.abs(stationaryObjectCenterX - airShipCenterX) * Math.abs(stationaryObjectCenterX - airShipCenterX)))
                + (Math.abs(stationaryObjectCenterY - airShipCenterY) * Math.abs(stationaryObjectCenterY - airShipCenterY));
        return correctDistance >= distance;
    }

    public void detectCollisions() {
        for (StationaryObject stationaryObject : stationaryObjects) {
            for (AirShip airShip : airShips) {
                boolean isColliding = isColliding(stationaryObject, airShip);
                airShip.setColliding(isColliding);
                stationaryObject.setColliding(isColliding);

            }
        }
    }
}
