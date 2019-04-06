package GameObjects;

import GameObjects.GameObject;

import java.util.List;

public class ObjectManager {
    List<GameObject> myActiveObjects;

    public ObjectManager(List activeObjects) {
        myActiveObjects = activeObjects;
    }

    public void adjustDirection(GameObject obj, double delta){
        double angle = obj.getAngle();
        angle += delta;
        double[] directionVec = calculateDirection(angle);
        obj.setAngle(angle);
        obj.setDirection(directionVec);
    }

    public void setDirection(GameObject obj, double angle){
        double[] directionVec = calculateDirection(angle);
        obj.setAngle(angle);
        obj.setDirection(directionVec);
    }

    private double[] calculateDirection(double angle){
        double[] directionVec = new double[2];
        directionVec[0] = Math.cos(Math.toRadians(angle));
        directionVec[1] = Math.sin(Math.toRadians(angle));
        return directionVec;
    }

    public void kill(GameObject obj) {
        myActiveObjects.remove(obj);
    }

    public void stop(GameObject obj) {obj.setVelocity(0);}

    public void create(GameObject obj) {
        myActiveObjects.add(obj);
    }

    public void addPowerup(GameObject powerup, GameObject target){
        //target.addPowerup(powerup);

    }

    int getNumObjects() {
        return myActiveObjects.size();
    }

    public void move(GameObject obj) {
        obj.updatePosition();
    }

    public void updateStats(GameObject obj) {
        obj.updateStats();
    }

    public void increaseScore(GameObject obj, double myGain) {
        obj.setScore(obj.getScore() + myGain);
    }

    public void setCollide(GameObject obj, boolean collides){
        obj.setCollide(collides);
    }

    public void setXVel(GameObject obj, double newXVel) {
        double[] direction = obj.getDirection();
        double yDirec = direction[1];
        double vel = obj.getVelocity();
        double yvel = yDirec*vel;
        double newVel = Math.sqrt(newXVel*newXVel + yvel*yvel);
        direction = new double[]{newXVel/newVel, yvel/newVel};
        obj.setVelocity(newVel);
        obj.setDirection(direction);
    }

    public void setYVel(GameObject obj, double newYVel) {
        double[] direction = obj.getDirection();
        double xDirec = direction[0];
        double vel = obj.getVelocity();
        double xvel = xDirec*vel;
        double newVel = Math.sqrt(newYVel*newYVel + xvel*xvel);
        direction = new double[]{xvel/newVel, newYVel/newVel};
        obj.setVelocity(newVel);
        obj.setDirection(direction);

    }
}
