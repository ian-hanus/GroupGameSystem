package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;
import Events.ObjectEvents.ObjectEvent;

import java.util.List;

public class MoveDown extends ObjectEvent {

    public MoveDown() {super();}
    public MoveDown(int obj){super(obj);}
    public MoveDown(List<Conditional> conditionals){super(conditionals);}
    public MoveDown(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) {
        entityManager.moveVertical(myObject, true);
    }
}