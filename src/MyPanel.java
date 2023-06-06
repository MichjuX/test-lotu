import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.MarshalException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MyPanel extends JPanel implements ActionListener {
    public final int PANEL_WIDTH = 1280;
    public final int PANEL_HEIGHT = 720;
    Image backgroundImage;
    Timer timer;
    int Velocity = 2;
    Point startPos = new Point(230, 550);
    Point endPos = new Point(300, 300);
    Point endPost2 = new Point(1000, 200);
    int x = startPos.x;
    int y = startPos.y;
    int y1 = 0, y2 = 50;
    int delay = 10;
    int refreshrate = 1000 / delay;
    List<AirShip> airships;


    //
    double distanceX = endPos.x - startPos.x;
    double distanceY = endPos.y - startPos.y;
    double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    double expectedTime = distance / Velocity;
    double currentTime = 0;

    Map map = new Map("src\\mapa.txt", new StationaryObject());
    MyPanel() throws Exception {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        timer = new Timer(delay, this);
        timer.start();
        this.airships = new ArrayList<>();
    }

    private int RND(int a) {
        Random rand = new Random();
        int x = (Math.abs((rand.nextInt() % a)));
        if (a == 1280) {
            System.out.println("x:" + x);
        } else System.out.println("y:" + x);
        return x;
    }
    public void createAirShip(HashMap<Class<? extends AirShip>, Integer> airshipTypes) {
        AirShipFactory factory = new AirShipFactory();
        for (HashMap.Entry<Class<? extends AirShip>, Integer> entry : airshipTypes.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                int x = RND(PANEL_WIDTH); // losowe x
                int y = RND(PANEL_HEIGHT); // losowe y
                airships.add(factory.createAirShip(entry.getKey(), new Point(x, y)));
            }
        }
    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(255, 255, 255));
        map.paint(g);
        for (AirShip airShip : airships) {
            g2D.setPaint(airShip.getColor());
            g2D.drawRect(airShip.currentPos.x, airShip.currentPos.y, 100, 100);
                for (Section section : airShip.sections) {
                    g2D.setPaint(airShip.getColor());
                    g2D.drawLine(section.x, section.y, section.endPoint.x, section.endPoint.y);
                }
        }
    }

    int counter = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
//            airShip.updatePos(counter);
//            counter++;
        repaint();
    }
//    if(currentTime<=expectedTime) {
//        x = (int) (startPos.x + (currentTime / expectedTime) * distanceX) - 50;
//        y = (int) (startPos.y + (currentTime / expectedTime) * distanceY) - 50;
//    }
//    currentTime+=1;
//        System.out.println("Current Position: (" + x + ", " + y + ")");
//    repaint();

}
