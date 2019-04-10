package Engine.src.Triggers.Events.ObjectEvents;

import Engine.src.Triggers.Conditionals.Conditional;
import Engine.src.ECS.EntityManager;

import java.util.List;

public class Deflect extends DirectionChange{

    double myAdjustmentAngle;

    public Deflect(List<Conditional> conditionals, int angle){
        super(conditionals,angle);
    }

    @Override
    public void activate(EntityManager entityManager){
        //entityManager.setYVelocity(myObject, 0);//FIXME using this to stop y velocity
        //entityManager.adjustDirection(myObject, myAdjustmentAngle);
    }
}
