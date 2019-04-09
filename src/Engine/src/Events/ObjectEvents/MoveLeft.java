package Events.ObjectEvents;

import Conditionals.CollideFromRight;
import Conditionals.Conditional;
import ECS.CollisionDetector;
import ECS.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class MoveLeft extends ObjectEvent {

    public MoveLeft() {super();}
    public MoveLeft(int obj){super(obj);}
    public MoveLeft(List<Conditional> conditionals){super(conditionals);}
    public MoveLeft(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    private void setConditional(){
        if (myConditionals == null) myConditionals = new ArrayList<>();
        CollisionDetector collisionDetector = new CollisionDetector();
        myConditionals.add(new CollideFromRight());
    }

    @Override
    public void activate(EntityManager entityManager) {
        double currentPos = entityManager.getX(myObject);
        double motionXVel = entityManager.getMotionXVel(myObject);
        double stepTime = entityManager.getStepTime(myObject);
        entityManager.setX(myObject, currentPos + motionXVel * stepTime);
    }
}
