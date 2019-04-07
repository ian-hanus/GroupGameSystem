package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class DirectionChange extends ObjectEvent {
    private double myAdjustmentAngle;

    public DirectionChange(double angle){
        myAdjustmentAngle = angle;
    }

    public DirectionChange(double obj, double angle){
        super(obj);
        myAdjustmentAngle = angle;
    }

    public DirectionChange(List<Conditional> conditionals, double angle){
        super(conditionals);
        myAdjustmentAngle = angle;
    }

    public DirectionChange(List<Conditional> conditionals, double obj, double angle){
        super(conditionals, obj);
        myAdjustmentAngle = angle;
    }


    @Override
    public void activate(ObjectManager objectManager){
        objectManager.adjustDirection(myObject, myAdjustmentAngle);
    }

    @Override
    public void activate(double other, ObjectManager objectManager){
        objectManager.adjustDirection(myObject, myAdjustmentAngle);
    }
}
