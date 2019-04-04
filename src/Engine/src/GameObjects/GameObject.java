package Engine.src.GameObjects;

import Engine.src.Powerups.Powerup;

import java.util.List;

/**
 * @author David Liu and Jonathan Yu
 */
public class GameObject {
    private double myXPos;
    private double myYPos;
    private double myHealth;
    private double myHeight;
    private double myWidth;
    private double myAngle;
    private String myName;
    private int myZIndex;
    private double[] myDirection;
    private String myFilename;
    private double myScore;


    public GameObject(double xPos, double yPos, double health, double height, double width, double angle,
                      String objectName, int zIndex, double[] direction, String filename) {
        myXPos = xPos;
        myYPos = yPos;
        myHealth = health;
        myHeight = height;
        myWidth = width;
        myAngle = angle;
        myName = objectName;
        myZIndex = zIndex;
        myDirection = direction;
        myFilename = filename;
        myScore = 0;
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

    public void updatePosition() {
        double currXPos = getX();
        double currYPos = getY();
        double[] direction = getDirection();

        setX(currXPos + direction[0]);
        setY(currYPos + direction[1]);
    }

    public void updateStats(){

    }

    /*
    Getters:
     */

    public double getHeight() {
        return myHeight;
    }

    public double getWidth() {
        return myWidth;
    }

    public String getName() {
        return myName;
    }

    public double getAngle(){
        return myAngle;
    }

    public double getScore() {
        return myScore;
    }

    public double getX() {
        return myXPos;
    }

    public double getY() {
        return myYPos;
    }

    public double getHealth() {
        return myHealth;
    }

    public double[] getDirection() {
        return myDirection;
    }


    /*
    Setters:
     */

    protected void setScore(double score){
        myScore = score;
    }

    protected void setAngle(double angle) {
        myAngle = angle;
    }

    protected void setDirection(double[] directionVec) {
        myDirection = directionVec;
    }

    protected void setHeight(double height) {
        myHeight = height;
    }

    protected void setWidth(double width) {
        myWidth = width;
    }

    protected void setName(String name) {
        myName = name;
    }

    protected void setX(double xPos) {
        myXPos = xPos;
    }

    protected void setY(double yPos) {
        myYPos = yPos;
    }

    protected void setHealth(double health) {
        myHealth = health;
    }

    //gameobject.apply(powerup);
}
