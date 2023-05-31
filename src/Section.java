import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Section{
    public final Point startPoint;
    public int x,y;
    public Point endPoint;
    public int velocity;
    public double distance;
    public double distanceX;
    public double distanceY;
    public int startTime;
    public int endTime;
    Section(int startTime, Point startPoint, Point endPoint, int velocity){
        this.endPoint = endPoint;
        this.startPoint = startPoint;
        x = startPoint.x;
        y = startPoint.y;
        this.startTime = startTime;
        this.velocity = velocity;
        distanceX = endPoint.x-startPoint.x;
        distanceY = endPoint.y-startPoint.y;
        distance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
        endTime = (int) (startTime+distance/velocity);

    }

    // @Override
    // public String toString() {
    //     return "startPoint.x: " + startPoint.x + "\n"
    //             + "startPoint.y: "+ startPoint.y + "\n"
    //             + "endPoint.x: " + endPoint.x + "\n"
    //             + "endPoint.y: " + endPoint.y + "\n"
    //             + "velocity: " + velocity + "\n"
    //             + "distance: " + distance + "\n"
    //             + "distanceX: " + distanceX + "\n"
    //             + "distanceY: " + distanceY + "\n"
    //             + "startTime:" + startTime + "\n"
    //             + "endTime:" + endTime + "\n";

    // }
}
