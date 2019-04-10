package Triggers.Events.ObjectEvents;

import Triggers.Conditionals.Conditional;

import ECS.EntityManager;
import Triggers.Events.Event;

import java.util.List;

public class Kill extends ObjectEvent {

    public Kill() {super();}
    public Kill(int obj){super(obj);}
    public Kill(List<Conditional> conditionals){super(conditionals);}
    public Kill(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) {
        entityManager.die(myOther);
    }

    @Override
    public Event copy() {
        return new Kill(copyConditionals(), myObject);
    }
}
