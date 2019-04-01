package GameObjects;

import javafx.scene.image.ImageView;

import java.util.List;
public abstract class GameObject {
    private double xPos;
    private double yPos;
    private double health;
    //Please change it to be part of a different package (ObjectManager)
    private List myActiveObjects;
    private ImageView image;

    public GameObject(double myXPos, double myYPos, double myHealth, List activeObjects, ImageView myImage) {
        xPos = myXPos;
        yPos = myYPos;
        health = myHealth;
        myActiveObjects = activeObjects;
        image = myImage;
    }

    //Please change it to be part of a different package (ObjectManager)
    public void die() {
        myActiveObjects.remove(this);
    }

    protected abstract void updatePosition();

    protected abstract void updateStats();

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
    //protected void move() {

    //}

    public double getHeight() {
        return image.getFitHeight();
    }

    public double getWidth() {
        return image.getFitWidth();
    }
}
