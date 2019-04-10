package Triggers.Events.ObjectEvents;

import Triggers.Conditionals.Conditional;
import ECS.EntityManager;
import Triggers.Events.Event;

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

    @Override
    public Event copy() {
        return new MoveDown(copyConditionals(), myObject);
    }
}