package triggers.Events.ObjectEvents;

import triggers.Conditionals.Conditional;
import ecs.EntityManager;

import java.util.List;

public class DirectionChange extends ObjectEvent {
    private double myAdjustmentAngle;

    public DirectionChange(int angle){
        myAdjustmentAngle = angle;
    }

    public DirectionChange(int obj, double angle){
        super(obj);
        myAdjustmentAngle = angle;
    }

    public DirectionChange(List<Conditional> conditionals, double angle){
        super(conditionals);
        myAdjustmentAngle = angle;
    }

    public DirectionChange(List<Conditional> conditionals, int obj, double angle){
        super(conditionals, obj);
        myAdjustmentAngle = angle;
    }


    @Override
    public void activate(EntityManager entityManager){
        entityManager.adjustDirection(myObject, myAdjustmentAngle);
    }
}
