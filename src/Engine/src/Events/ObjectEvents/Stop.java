package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;

import java.util.List;

public class Stop extends ObjectEvent {

    public Stop(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(EntityManager entityManager) { entityManager.stop(myObject);}
}
