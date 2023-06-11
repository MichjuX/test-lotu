import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StationaryObject extends JPanel {
    private List<Tree> trees;
    private List<Building> buildings;
    protected Point position;
    protected double height;
    protected int parametr;
    private boolean colliding = false;

    public StationaryObject() {
        this.trees = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }
    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
    public StationaryObject(Point position, double height, int parametr) {
        this.position = position;
        this.height = height;
        this.parametr = parametr;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (Tree tree : trees) {
            tree.paintComponent(g);
        }
        for (Building building : buildings) {
            building.paintComponent(g);
        }
    }

}
