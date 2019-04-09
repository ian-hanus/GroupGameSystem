package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.CollisionDetector;
import ECS.EntityManager;
import Events.ObjectEvents.ObjectEvent;

import java.util.List;

public class MoveUp extends ObjectEvent {

    public MoveUp() {super();}
    public MoveUp(int obj){super(obj);}
    public MoveUp(List<Conditional> conditionals){super(conditionals);}
    public MoveUp(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) {
            double currentPos = entityManager.getY(myObject);
            double motionYVel = entityManager.getMotionYVel(myObject);
            double stepTime = entityManager.getStepTime();
            entityManager.setY(myObject, currentPos + motionYVel * stepTime);
    }
}