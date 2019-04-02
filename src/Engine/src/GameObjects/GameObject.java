package GameObjects;

import Powerups.Powerup;

import java.util.List;
public abstract class GameObject {
    private double xPos;
    private double yPos;
    private double health;
    private double height;
    private double width;
    private String name;
    private int zindex;

    public GameObject(double myXPos, double myYPos, double myHealth, double myHeight, double myWidth, String objectName, int myZIndex) {
        xPos = myXPos;
        yPos = myYPos;
        health = myHealth;
        name = objectName;
        height = myHeight;
        width = myWidth;
        zindex = myZIndex;
    }

    public abstract void updatePosition();

    public abstract void updateStats();

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public double getHealth() {
        return health;
    }

    protected void setX(double xPos1) {
        xPos = xPos1;
    }

    protected void setY(double yPos1) {
        yPos = yPos1;
    }

    protected void setHealth(double health1) {
        health = health1;
    }

    //Implement basic physics somewhere else?
    //protected double getVel() {

    //}

    //Implement basic physics somewhere else?
    //protected abstract void move() {

    //}
    /*
    public String getFilename() {

    }
    */

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    protected void setHeight(double height1) {
        height = height1;
    }

    protected void setWidth(double width1) {
        width = width1;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name1) {
        name = name1;
    }

    public void apply(Powerup powerup) {

    }

    //gameobject.apply(powerup);
}
