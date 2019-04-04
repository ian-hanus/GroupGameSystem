package Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public class Deflect extends DirectionChange{

    double myAdjustmentAngle;

    public Deflect(double angle){
        super(180);
    }

    @Override
    public void respond(GameObject obj, ObjectManager objectManager){
        objectManager.adjustDirection(obj, myAdjustmentAngle);
    }

    @Override
    public void respond(GameObject obj, GameObject other, ObjectManager objectManager){
        objectManager.adjustDirection(obj, myAdjustmentAngle);
    }

}
