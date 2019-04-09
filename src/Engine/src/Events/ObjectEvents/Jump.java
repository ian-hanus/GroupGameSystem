package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;

import java.util.List;

public class Jump extends ObjectEvent {

    public Jump() {
        super();
    }
    public Jump(int obj){super(obj);}
    public Jump(List<Conditional> conditionals){super(conditionals);}
    public Jump(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) {
        entityManager.jump(myObject);
    }

    @Override
    public void activate(int other, EntityManager entityManager) {
        entityManager.jump(myObject);
    }


}
