package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;

import java.util.List;

public class MoveLeft extends ObjectEvent {

    public MoveLeft() {super();}
    public MoveLeft(int obj){super(obj);}
    public MoveLeft(List<Conditional> conditionals){super(conditionals);}
    public MoveLeft(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) { }

    @Override
    public void activate(int other, EntityManager entityManager) {
        double currentPos = entityManager.getX(myObject);
        double motionXVel = entityManager.getMotionXVel(myObject);
        double stepTime = entityManager.getStepTime(myObject);
        entityManager.setX(myObject, currentPos + motionXVel * stepTime);
    }
}
