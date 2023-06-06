import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirShip extends JPanel implements ActionListener{
    Point startPos;
    Point currentPos;
    List<Section> sections = new ArrayList<>();
    int endTime;
    //int altitude;
    Rectangle hitbox;
    private Color color;
    Timer timer = new Timer(10,this);


    AirShip(Point startPos, List<Section> sections){
        this.startPos = startPos;
        this.sections = sections;
        for(Section section : sections){
            this.endTime = section.endTime;
        }
        this.currentPos = startPos;
    }
    AirShip(Point startPos, Color color){
        this.color = color;
        sections.add(new Section(0,
                startPos,
                new Point(RND(1280),RND(720)),
                5
        ));
        //bedziesz chcial to zmienisz sobie ale z RND nie dziala poprawnie
        Random random = new Random();
        for(int i=0; i<random.nextInt(5)+5; i++){
            int velocity = random.nextInt(5)+3;
            sections.add(new Section(sections.get(i).endTime,
                    sections.get(i).endPoint,
                    new Point(RND(1280),RND(720)),
                    velocity
            ));
        }

        this.startPos = startPos;
        for(Section section : sections){
            this.endTime = section.endTime;
        }
        this.currentPos = startPos;

        timer.start();
    }
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
    }
    private int RND(int a){
        Random rand = new Random();
        int x = (Math.abs((rand.nextInt()%a)-100));
        if(a==1280){
            System.out.println("x:" + x);
        }
        else System.out.println("y:" + x);
        return x;
    }
    int currentTime = 0;
    int i = 0;
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(255, 255, 255));
        g2D.drawLine(900, 500, 900, 600);
    }
    public void actionPerformed(ActionEvent event){
        if(i<sections.size()) {

            if (i-1 >= sections.size()) {
                return;
            }
            Section tempsection = sections.get(i);

    //        System.out.println("SEKCJA "+i+"\n");
    //        System.out.println(tempsection.toString());;
            double ratio = ((currentTime - tempsection.startTime) / (double) (tempsection.endTime - tempsection.startTime));
            currentPos.x = (int) (tempsection.x + ratio * tempsection.distanceX) -50;
            currentPos.y = (int) (tempsection.y + ratio * tempsection.distanceY) -50;
            System.out.println(currentPos.x + " " + currentPos.y);
            currentTime++;
            if (sections.get(i).endTime <= currentTime) {
                i++;
            }
        }
        Line line = new Line();
    }

    public Paint getColor() {
        return this.color;
    }
}
