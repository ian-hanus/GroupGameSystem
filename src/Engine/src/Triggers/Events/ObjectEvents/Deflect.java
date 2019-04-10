package Engine.src.Triggers.Events.ObjectEvents;

import Triggers.Conditionals.Conditional;
import ECS.EntityManager;
import Triggers.Events.Event;

import java.util.List;

public class Deflect extends DirectionChange{

    double myAdjustmentAngle;

    public Deflect(List<Conditional> conditionals, double angle) {
        super(conditionals, 180);
    }

    @Override
    public void activate(EntityManager entityManager){
        //entityManager.setYVelocity(myObject, 0);//FIXME using this to stop y velocity
        //entityManager.adjustDirection(myObject, myAdjustmentAngle);
    }

    @Override
    public Event copy() {
        return new Deflect(copyConditionals(), myAdjustmentAngle);
    }
}
