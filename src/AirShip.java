import java.awt.*;
import java.util.*;
import java.util.List;

// Klasa statku powietrznego
public class AirShip{
    Point currentPos = new Point();
    List<Route> sections = new ArrayList<Route>();
    int altitude;
    boolean ended;

    AirShip(Point startPos, List<Route> sections){
        this.sections = sections;
    }
    AirShip(Point startPos){
        // trasa testowa, zmienić po dodaniu generowania losowych tras
        sections.add(new Route(0,startPos,new Point(600,500),2));
        sections.add(new Route(sections.get(0).endTime, sections.get(0).endPoint, new Point(700,500), 2 ));
        sections.add(new Route(sections.get(1).endTime, sections.get(1).endPoint, new Point(700,100), 2));
        sections.add(new Route(sections.get(2).endTime, sections.get(2).endPoint, new Point(800,100), 2));
        sections.add(new Route(sections.get(3).endTime, sections.get(3).endPoint, new Point(800,500), 2));
        sections.add(new Route(sections.get(4).endTime, sections.get(4).endPoint, new Point(900,500), 2));
        sections.add(new Route(sections.get(5).endTime, sections.get(5).endPoint, new Point(900,600), 2));

    }

    // iterator obecnego odcinka trasy
    int i = 0;

    // aktualizacja pozycji statku w zalešności od aktualnego czasu
    public void updatePos(int currentTime) {
        if (sections.get(i).endTime <= currentTime) {
            i++;
        }

        if (i >= sections.size()) {
            ended = true;
            return;
        }

        // tymczasowe przechowywanie kopii obiektu w podprogramie
        Route tempsection = sections.get(i);
        // test route
        // System.out.println("Odcinek "+i+"\n");
        // System.out.println(tempsection.toString());;
        double ratio = ((currentTime - tempsection.startTime) / (double) (tempsection.endTime - tempsection.startTime));
        currentPos.x = (int) (tempsection.x + ratio * tempsection.distanceX) -50;
        currentPos.y = (int) (tempsection.y + ratio * tempsection.distanceY) -50;
        // wypisywanie obecnego położenia statku powietrznego w konsoli
        // System.out.println(currentPos.x + " " + currentPos.y);
    }
    // Do generowania losowej trasy
    // private int RND(int a){
    //     Random rand = new Random();
    //     return (Math.abs(rand.nextInt()%a))+1;
    // }
}
