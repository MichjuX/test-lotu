import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirShip extends JPanel implements ActionListener{
    Point startPos = new Point();
    Point currentPos = new Point();
    List<Section> sections = new ArrayList<Section>();
    int endTime;
    int altitude;
    Timer timer = new Timer(10,this);

    AirShip(Point startPos, List<Section> sections){
        this.startPos = startPos;
        this.sections = sections;
        for(Section section : sections){
            this.endTime = section.endTime;
        }
        this.currentPos = startPos;
    }
    AirShip(Point startPos){
//        sections.add(new Section(0,
//                new Point(RND(1280),RND(720)),
//                new Point(RND(1280),RND(720)),
//
//                ));
//        for(int i=0; i<3; i++){
//            sections.add(new Section())
//        }
        sections.add(new Section(0,
                startPos,
                new Point(600,500),
                2
        ));

        sections.add(new Section(sections.get(0).endTime,
                sections.get(0).endPoint,
                new Point(700,500),
                2
        ));
        sections.add(new Section(sections.get(1).endTime, sections.get(1).endPoint, new Point(700,100), 2));
        sections.add(new Section(sections.get(2).endTime, sections.get(2).endPoint, new Point(800,100), 2));
        sections.add(new Section(sections.get(3).endTime, sections.get(3).endPoint, new Point(800,500), 2));
        sections.add(new Section(sections.get(4).endTime, sections.get(4).endPoint, new Point(900,500), 2));
        sections.add(new Section(sections.get(5).endTime, sections.get(5).endPoint, new Point(900,600), 2));
        for(int i=0; i<5; i++){
            sections.add(new Section(sections.get(i).endTime,
                    sections.get(i).endPoint,
                    new Point(),
                    2
            ));
        }

        this.startPos = startPos;
        for(Section section : sections){
            this.endTime = section.endTime;
        }
        this.currentPos = startPos;

        timer.start();
    }
    int i = 0;
    public void updatePos(int currentTime) {
//        if (sections.get(i).endTime <= currentTime) {
//            i++;
//        }
//
//        if (i >= sections.size()) {
//            ended = true;
//            return;
//        }
//        Section tempsection = sections.get(i);
//        System.out.println("SEKCJA "+i+"\n");
//        System.out.println(tempsection.toString());;
//        double ratio = ((currentTime - tempsection.startTime) / (double) (tempsection.endTime - tempsection.startTime));
//        currentPos.x = (int) (tempsection.x + ratio * tempsection.distanceX) -50;
//        currentPos.y = (int) (tempsection.y + ratio * tempsection.distanceY) -50;
////        currentPos.x = (int) (tempsection.startPoint.x + ratio * tempsection.distanceX);
////        currentPos.y = (int) (tempsection.startPoint.y + ratio * tempsection.distanceY);
//        System.out.println(currentPos.x + " " + currentPos.y);
//    }
//    private int RND(int a){
//        Random rand = new Random();
//        return (Math.abs(rand.nextInt()%a))+1;
    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(255, 255, 255));
        g2D.drawRect(currentPos.x, currentPos.y, 100, 100);
        g2D.drawLine(600, 600, 600, 500);
        g2D.drawLine(600, 500, 700, 500);
        g2D.drawLine(700, 500, 700, 100);
        g2D.drawLine(700, 100, 800, 100);
        g2D.drawLine(800, 100, 800, 500);
        g2D.drawLine(800, 500, 900, 500);
        g2D.drawLine(900, 500, 900, 600);
    }
    int currentTime = 0;
    public void actionPerformed(ActionEvent event){
        if (sections.get(i).endTime <= currentTime) {
            i++;
        }

        if (i >= sections.size()) {
            return;
        }
        Section tempsection = sections.get(i);
//        System.out.println("SEKCJA "+i+"\n");
//        System.out.println(tempsection.toString());;
        double ratio = ((currentTime - tempsection.startTime) / (double) (tempsection.endTime - tempsection.startTime));
        currentPos.x = (int) (tempsection.x + ratio * tempsection.distanceX) -50;
        currentPos.y = (int) (tempsection.y + ratio * tempsection.distanceY) -50;
//        currentPos.x = (int) (tempsection.startPoint.x + ratio * tempsection.distanceX);
//        currentPos.y = (int) (tempsection.startPoint.y + ratio * tempsection.distanceY);
//        System.out.println(currentPos.x + " " + currentPos.y);
        currentTime++;
    }
}
