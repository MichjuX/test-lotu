import java.awt.*;

public class Section {
    private int x;
    private int y;
    private Point endPoint;
    private double distance;
    private double distanceX;
    private double distanceY;
    private int startTime;
    private int endTime;

    public Section(int startTime, Point startPoint, Point endPoint, int velocity) {
        this.endPoint = endPoint;
        x = startPoint.x;
        y = startPoint.y;
        this.startTime = startTime;
        distanceX = endPoint.x - startPoint.x;
        distanceY = endPoint.y - startPoint.y;
        distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        endTime = (int) (startTime + distance / velocity);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public double getDistanceX() {
        return distanceX;
    }

    public double getDistanceY() {
        return distanceY;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
