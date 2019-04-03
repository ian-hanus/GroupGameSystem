package Responses;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Deflect extends DirectionChange{

    double myAdjustmentAngle;

    public Deflect(List<Conditional> conditionals, double angle){
        super(conditionals,180);
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
