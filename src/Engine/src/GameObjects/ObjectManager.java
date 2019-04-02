package GameObjects;

import GameObjects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ObjectManager {
    List<GameObject> myActiveObjects;

    public ObjectManager(List activeObjects) {
        myActiveObjects = activeObjects;
    }

    public void adjustDirection(GameObject obj, double delta){
        double angle = obj.getAngle();
        angle += delta;
        obj.setAngle(angle);
        double[] directionVec = new double[2];
        directionVec[0] = Math.cos(Math.toRadians(angle));
        directionVec[1] = Math.sin(Math.toRadians(angle));
        obj.setDirection(directionVec);
    }

    public void adjustDirection(GameObject obj, double angle){
        obj.setAngle(angle);
        obj.setDirection(directionVec);
    }

    private double[] calculateDirection(GameObject obj, double Angle){
        double[] directionVec = new double[2];
        directionVec[0] = Math.cos(Math.toRadians(angle));
        directionVec[1] = Math.sin(Math.toRadians(angle));
        return directionVec;
    }


    public void kill(GameObject obj) {
        myActiveObjects.remove(obj);
    }

    public void create(GameObject obj) {
        myActiveObjects.add(obj);
    }

    int getNumObjects() {
        return myActiveObjects.size();
    }
}
