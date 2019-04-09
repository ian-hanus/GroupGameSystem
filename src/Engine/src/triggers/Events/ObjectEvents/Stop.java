package triggers.Events.ObjectEvents;

import triggers.Conditionals.Conditional;
import ecs.EntityManager;

import java.util.List;

public class Stop extends ObjectEvent {

    public Stop(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(EntityManager entityManager) { entityManager.stop(myObject);}
}
