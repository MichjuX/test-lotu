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
    int height;
    int width;
    int altitude;
    private Color color;
    private boolean colliding = false;

   // Timer timer = new Timer(10,this);
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

        //timer.start();
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
            currentPos.x = (int) (tempsection.x + ratio * tempsection.distanceX) - this.width/2;
            currentPos.y = (int) (tempsection.y + ratio * tempsection.distanceY) - this.height/2;
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
