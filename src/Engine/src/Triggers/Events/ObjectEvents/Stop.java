package Engine.src.Triggers.Events.ObjectEvents;

import Triggers.Conditionals.Conditional;
import ECS.EntityManager;
import Triggers.Events.Event;

import java.util.List;

public class Stop extends ObjectEvent {

    public Stop(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) { entityManager.stop(myObject);}

    @Override
    public Event copy() {
        return new Stop(copyConditionals(), myObject);
    }
}
