package Engine.src.GameObjects;

import java.util.List;
public abstract class GameObject {
    private double xPos;
    private double yPos;
    private double health;
    private List myActiveObjects;

    public GameObject(double myXPos, double myYPos, double myHealth, List activeObjects) {
        xPos = myXPos;
        yPos = myYPos;
        health = myHealth;
        myActiveObjects = activeObjects;
    }

    public void die() {
        myActiveObjects.remove(this);
    }
}
