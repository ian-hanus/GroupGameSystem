package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class DirectionChange extends ObjectEvent {
    private double myAdjustmentAngle;

    public DirectionChange(List<Conditional> conditionals, double angle){
        super(conditionals);
        myAdjustmentAngle = angle;
    }

    @Override
    public void activate(GameObject obj, ObjectManager objectManager){
        objectManager.adjustDirection(obj, myAdjustmentAngle);
    }

    @Override
    public void activate(GameObject obj, GameObject other, ObjectManager objectManager){
        objectManager.adjustDirection(obj, myAdjustmentAngle);
    }
}
