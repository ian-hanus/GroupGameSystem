package Triggers.Events.ObjectEvents;

import Triggers.Conditionals.Conditional;
import ECS.EntityManager;
import Triggers.Events.Event;

import java.util.List;

public class GivePowerup extends ObjectEvent {

    public GivePowerup(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) {
       entityManager.addPowerup(myObject, myOther);
    }

    @Override
    public Event copy() {
        return new GivePowerup(copyConditionals(), myObject);
    }
}
