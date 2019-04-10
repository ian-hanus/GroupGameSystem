package Engine.src.Triggers.Events.ObjectEvents;

import Engine.src.Triggers.Conditionals.Conditional;
import Engine.src.ECS.EntityManager;

import java.util.List;

public class GivePowerup extends ObjectEvent {

    public GivePowerup(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(EntityManager entityManager) {
       entityManager.addPowerup(myObject, myOther);
    }
}
