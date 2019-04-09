package Events.ObjectEvents;

import Conditionals.CollideFromRight;
import Conditionals.Conditional;
import ECS.CollisionDetector;
import ECS.Components.Component;
import ECS.Components.ImpassableComponent;
import ECS.EntityManager;
import Events.ObjectEvents.ObjectEvent;

import java.util.ArrayList;
import java.util.List;

public class MoveLeft extends ObjectEvent {

    public MoveLeft() {super();}
    public MoveLeft(int obj){super(obj);}
    public MoveLeft(List<Conditional> conditionals){super(conditionals);}
    public MoveLeft(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) {
            double currentPos = entityManager.getX(myObject);
            double motionXVel = entityManager.getMotionXVel(myObject);
            double stepTime = entityManager.getStepTime();
            entityManager.setX(myObject, currentPos - motionXVel * stepTime);
    }
}
