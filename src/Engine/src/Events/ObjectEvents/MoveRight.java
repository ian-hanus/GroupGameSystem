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

public class MoveRight extends ObjectEvent {

    public MoveRight() {super();}
    public MoveRight(int obj){super(obj);}
    public MoveRight(List<Conditional> conditionals){super(conditionals);}
    public MoveRight(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) {
        entityManager.moveHorizontal(myObject, true);
    }
}