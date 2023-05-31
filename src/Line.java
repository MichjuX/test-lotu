import java.awt.*;

public class Line {
    public void drawLine(Graphics2D g2D, Section startPoint, Section endPoint){
        g2D.setPaint(new Color(255, 255, 255));
        g2D.drawLine(startPoint.x,startPoint.y,endPoint.x,endPoint.y);
    }
}
