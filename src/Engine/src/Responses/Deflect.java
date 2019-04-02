package Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public class Deflect extends DirectionChange{

    private double myAdjustmentAngle;

    public Deflect(double angle){
        super(angle);
        myAdjustmentAngle = 180;
    }

    @Override
    public void respond(GameObject obj, ObjectManager objectManager){
        objectManager.adjustDirection(obj, myAdjustmentAngle);
    }
}
