package GameObjects;

public abstract class GameObject {
    private double xPos;
    private double yPos;
    private double health;

    public GameObject(double myXPos, double myYPos, double myHealth) {
        xPos = myXPos;
        yPos = myYPos;
        health = myHealth;
    }

    
}
