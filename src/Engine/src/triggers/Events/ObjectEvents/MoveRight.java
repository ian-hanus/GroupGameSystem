package triggers.Events.ObjectEvents;

import triggers.Conditionals.Conditional;
import ecs.EntityManager;

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