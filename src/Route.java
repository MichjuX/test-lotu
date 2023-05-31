import java.awt.*;

// Klasa przechowująca kolejne punkty na trasie, informacje o prędkości na odcinku oraz inne wartości używane przy określaniu położenia
public class Route {
    //punkt startowy i końcowy
    public int x,y;
    public Point endPoint;
    //prędkość na odcinku
    public int velocity;
    //dlugość odcinka
    public double distance;
    //rozbicie na wektory
    public double distanceX;
    public double distanceY;
    //czas wejścia i zejścia z odcinka
    public int startTime;
    public int endTime;

    // Konstruktor tworzący kolejny odcinek na trasie
    Route(int startTime, Point startPoint, Point endPoint, int velocity){
        x = startPoint.x;
        y = startPoint.y;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.velocity = velocity;

        // Przygotowanie danych pod określanie położenia
        distanceX = endPoint.x-startPoint.x;
        distanceY = endPoint.y-startPoint.y;
        distance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
        endTime = (int) (startTime+distance/velocity);
    }

    // Test
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
