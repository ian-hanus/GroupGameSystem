package Responses;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class DirectionChange extends Response{
    private double myAdjustmentAngle;

    public DirectionChange(List<Conditional> conditionals, double angle){
        super(conditionals);
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
