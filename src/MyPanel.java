import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MyPanel extends JPanel implements ActionListener {
    private final int PANEL_WIDTH = 1280;
    private final int PANEL_HEIGHT = 720;
    private Timer timer;
    private Point startPos = new Point(230, 550);
    private Point endPos = new Point(300, 300);
    private int delay = 10;
    private List<AirShip> airships;
    private Map map = new Map("src\\mapa.txt");
    private CollisionDetector collisionDetector;

    public MyPanel() throws Exception {
        super();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        timer = new Timer(delay, this);
        timer.start();
        this.airships = new ArrayList<>();
        this.collisionDetector = new CollisionDetector(map.getStationaryObjects(), airships);

    }
    public JButton createStartStop() {                          //przycisk kontrolujący
        JButton startButton = new JButton("Stop");
        startButton.addActionListener(event -> {
            if (timer.isRunning()) {
                timer.stop();
                startButton.setText("Start");
            } else {
                timer.start();
                startButton.setText("Stop");
            }
        });
        return startButton;
    }
    public JButton createClearButton() {                              //przycisk czyszczący planszę z statków powietrznych
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener( event -> airships.clear());
        return clearButton;
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
                airships.add(factory.createAirShip(entry.getKey(), new Point(x, y), RND(100) + 50, RND(100) + 50, RND(1000) + 500));
            }
            collisionDetector = new CollisionDetector(map.getStationaryObjects(), airships);
        }
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(255, 255, 255));
        map.paint(g);
        for (AirShip airShip : airships) {
            g2D.setPaint(airShip.isColliding() ? Color.RED : airShip.getColor());
            g2D.drawRect((int) airShip.currentPos.getX(), (int) airShip.currentPos.getY(), airShip.getAirshipWidth(), airShip.getAirShipHeight());
            for (Section section : airShip.sections) {
                g2D.setPaint(airShip.getColor());
                g2D.drawLine(section.getX(), section.getY(), (int) section.getEndPoint().getX(), (int) section.getEndPoint().getY());
            }
        }
        for(int i=0; i<airships.size(); i++){
            if(airships.get(i).stillMoving())
                airships.remove(i);
        }

    }

    public List<AirShip> getAirships() {
        return airships;
    }

    // no usages trzeba usunąć

    @Override
    public void actionPerformed(ActionEvent e) {
        for (AirShip airShip : airships) {
            airShip.actionPerformed(e);  // add this line
        }
        collisionDetector.detectCollisions();
        repaint();

    }


}
