import java.util.List;

public class CollisionDetector {
    private List<StationaryObject> stationaryObjects;
    private List<AirShip> airShips;

    public CollisionDetector(List<StationaryObject> stationaryObjects, List<AirShip> airShips) {
        this.stationaryObjects = stationaryObjects;
        this.airShips = airShips;
    }

    //prawdopodobnie tutaj jest spierdolone vvvv
    public boolean isCollidingWithObject(StationaryObject stationaryObject, AirShip airShip) {
        double stationaryObjectLeft = stationaryObject.position.x - (double) stationaryObject.getWidth() / 2;
        double stationaryObjectRight = stationaryObject.position.x + (double) stationaryObject.getWidth() / 2;
        double stationaryObjectTop = stationaryObject.position.y - (double) stationaryObject.getHeight() / 2;
        double stationaryObjectBottom = stationaryObject.position.y + (double) stationaryObject.getHeight() / 2;


        double airShipLeft = airShip.currentPos.x - (double) airShip.getAirshipWidth() / 2;
        double airShipRight = airShip.currentPos.x + (double) airShip.getAirshipWidth() / 2;
        double airShipTop = airShip.currentPos.y - (double) airShip.getAirShipHeight() / 2;
        double airShipBottom = airShip.currentPos.y + (double) airShip.getAirShipHeight() / 2;

        // Check for a collision
        return (stationaryObjectRight > airShipLeft
                && stationaryObjectLeft < airShipRight
                && stationaryObjectBottom > airShipTop
                && stationaryObjectTop < airShipBottom
                && stationaryObject.getAltitude()>=airShip.getAltitude()+20);
        /*double stationaryObjectCenterX = stationaryObject.position.x + (((double) stationaryObject.parametr) / 2);
        double stationaryObjectCenterY = stationaryObject.position.y + (((double) stationaryObject.parametr) / 2);
        double stationaryObjectHalfDiag = Math.sqrt(((double) stationaryObject.parametr / 2) * ((double) stationaryObject.parametr / 2)
                + ((double) stationaryObject.parametr / 2) * ((double) stationaryObject.parametr / 2));

        double airShipCenterX = airShip.currentPos.x;
        double airShipCenterY = airShip.currentPos.y;
        double airShipHalfDiag = Math.sqrt((double) ((airShip.getAirShipHeight() * airShip.getAirShipHeight()) / 4)
                + ((double) (airShip.getAirshipWidth() * airShip.getAirshipWidth()) / 4));

        double correctDistance = stationaryObjectHalfDiag + airShipHalfDiag;
        double distance = Math.sqrt((Math.abs(stationaryObjectCenterX - airShipCenterX) * Math.abs(stationaryObjectCenterX - airShipCenterX))
                + (Math.abs(stationaryObjectCenterY - airShipCenterY) * Math.abs(stationaryObjectCenterY - airShipCenterY)));

        return correctDistance >= distance;*/
    }

    public boolean isCollidingWithAirShip(AirShip airShip1, AirShip airShip2) {
        double airShip1Left = airShip1.currentPos.x - (double) airShip1.getAirshipWidth() / 2;
        double airShip1Right = airShip1.currentPos.x + (double) airShip1.getAirshipWidth() / 2;
        double airShip1Top = airShip1.currentPos.y - (double) airShip1.getAirShipHeight() / 2;
        double airShip1Bottom = airShip1.currentPos.y + (double) airShip1.getAirShipHeight() / 2;

        double airShip2Left = airShip2.currentPos.x - (double) airShip2.getAirshipWidth() / 2;
        double airShip2Right = airShip2.currentPos.x + (double) airShip2.getAirshipWidth() / 2;
        double airShip2Top = airShip2.currentPos.y - (double) airShip2.getAirShipHeight() / 2;
        double airShip2Bottom = airShip2.currentPos.y + (double) airShip2.getAirShipHeight() / 2;

        return (airShip1Right > airShip2Left
                && airShip1Left < airShip2Right
                && airShip1Bottom > airShip2Top
                && airShip1Top < airShip2Bottom
                && Math.abs(airShip1.getAltitude() - airShip2.getAltitude()) <= 100);
    }

    public void detectCollisions() {
        for (AirShip airShip : airShips) {
            for (StationaryObject stationaryObject : stationaryObjects) {
                boolean isColliding = isCollidingWithObject(stationaryObject, airShip);
                airShip.setColliding(isColliding);
                //stationaryObject.setColliding(isColliding);
                if (isColliding) break;
            }
            for (AirShip airShip1 : airShips) {
                if (airShip1 != airShip) {
                    boolean isColliding = isCollidingWithAirShip(airShip, airShip1);
                    if (!airShip.isColliding()) airShip.setColliding(isColliding);
                    if (!airShip1.isColliding()) airShip1.setColliding(isColliding);
                }
            }
        }
    }
}
