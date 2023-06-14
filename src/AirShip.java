import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

abstract public class AirShip extends JPanel implements ActionListener{
    Point startPos;
    Point currentPos;
    List<Section> sections = new ArrayList<>();
    int endTime;
    int height;
    int width;
    int altitude;
    int currentTime = 0;
    private Color color;
    private boolean colliding = false;
    int sectionNumber = 0;

    public boolean isColliding() {
        return this.colliding;
    }
    AirShip(Point startPos, Color color, int height, int width, int altitude){
        this.color = color;
        this.height = height;
        this.width = width;
        this.altitude=altitude;
        sections.add(new Section(0,
                startPos,
                new Point(RND(1280),RND(720)),
                5
        ));
        Random random = new Random();
        for(int i=0; i<random.nextInt(5)+5; i++){
            int velocity = random.nextInt(10)+5;
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

    }
    public void changeRouteToRandom(){
        int x = RND(1280);
        int y = RND(720);
        currentTime = 0;
        sectionNumber = 0;
        currentPos = new Point(x,y);
        startPos = new Point(x,y);
        Random random = new Random();
        sections.set(0, new Section(0, new Point(x,y), new Point(RND(1280),RND(720)), random.nextInt(5)+3));
        for(int i=1; i<sections.size(); i++){
            int velocity = random.nextInt(5)+3;
                sections.set(i, new Section(sections.get(i-1).endTime, sections.get(i-1).endPoint, new Point(RND(1280),RND(720)), velocity));
        }
        for(Section section : sections){
            this.endTime = section.endTime;
        }
    }
    public void changeRouteByHand(){
        Scanner scanner = new Scanner(System.in);

        for(int i=0; i<sections.size(); i++) {
            if(i==0) {
                System.out.println("Współrzędna x startowa: ");
                int x1 = scanner.nextInt();
                System.out.println("Współrzędna y startowa: ");
                int y1 = scanner.nextInt();
                System.out.println("Współrzędna x końcowa odcinka numer 1: ");
                int x2 = scanner.nextInt();
                System.out.println("Współrzędna y końcowa odcinka numer 1: ");
                int y2 = scanner.nextInt();
                System.out.println("Prędkość na odcinku: ");
                int velocity = scanner.nextInt();
                currentTime = 0;
                sectionNumber = 0;
                currentPos = new Point(x1,y1);
                startPos = new Point(x1,y1);
                sections.set(0, new Section(0, new Point(x1, y1), new Point(x2, y2), velocity));
            }
            else{
                System.out.println("Współrzędna x końcowa odcinka numer " + i+1 + ":");
                int x1 = scanner.nextInt();
                System.out.println("Współrzędna y końcowa odcinka numer " + i+1 + ":");
                int y1 = scanner.nextInt();
                System.out.println("Prędkość na odcinku " + i+1 + ":");
                int velocity = scanner.nextInt();
                sections.set(i, new Section(sections.get(i-1).endTime, sections.get(i-1).endPoint, new Point(x1, y1), velocity));
            }
        }
        for(Section section : sections){
            this.endTime = section.endTime;
        }
    }
    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public int getAirShipHeight()
    {
        return height;
    }
    public int getAirshipWidth()
    {
        return width;
    }

    public int getAltitude() {
        return altitude;
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
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(255, 255, 255));
        g2D.drawLine(900, 500, 900, 600);
    }
    boolean ended = false;
    public void actionPerformed(ActionEvent event){
        if(sectionNumber <sections.size()) {

            if (sectionNumber-1 >= sections.size()) {
                ended = true;
                return;
            }
            Section tempsection = sections.get(sectionNumber);
            double ratio = ((currentTime - tempsection.startTime) / (double) (tempsection.endTime - tempsection.startTime));
            currentPos.x = (int) (tempsection.x + ratio * tempsection.distanceX) - this.width/2;
            currentPos.y = (int) (tempsection.y + ratio * tempsection.distanceY) - this.height/2;
            currentTime++;
            if (sections.get(sectionNumber).endTime <= currentTime) {
                sectionNumber++;
            }
        }
        else ended = true;
    }
    public boolean stillMoving(){
//        return this.endTime >= this.currentTime;
        return ended;
    }
    public Color getColor() {
        return this.color;
    }
}
