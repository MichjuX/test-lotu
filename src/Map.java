import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map extends JPanel {
    private final List<StationaryObject> stationaryObjects = new ArrayList<>();

    public Map(String fileName) throws Exception {
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter(";");
        while (scanner.hasNext()) {
            String kind = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int altitude = scanner.nextInt();
            int parametr = scanner.nextInt();

            if (kind.contains("t")) {
                Tree tree = new Tree(new Point(x, y), altitude, parametr);
                stationaryObjects.add(tree);
            } else if (kind.contains("b")) {
                Building building = new Building(new Point(x, y), altitude, parametr);
                stationaryObjects.add(building);
            }
        }
    }

    public List<StationaryObject> getStationaryObjects() {
        return stationaryObjects;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for (StationaryObject stationaryObject : stationaryObjects) {
            stationaryObject.paint(g2D);
        }
    }
}
