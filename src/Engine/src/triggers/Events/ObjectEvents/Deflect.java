package triggers.Events.ObjectEvents;

import triggers.Conditionals.Conditional;
import ecs.EntityManager;

import java.util.List;

public class Deflect extends DirectionChange{

    double myAdjustmentAngle;

    public Deflect(List<Conditional> conditionals, int angle){
        super(conditionals,180);
    }

    @Override
    public void activate(EntityManager entityManager){
        entityManager.adjustDirection(myObject, myAdjustmentAngle);
    }
}
