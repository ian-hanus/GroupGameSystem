package Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public class DirectionChange implements Response{
    private double myAdjustmentAngle;

    public DirectionChange(double angle){
        myAdjustmentAngle = angle;
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
