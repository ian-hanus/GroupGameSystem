package triggers.Events.ObjectEvents;

import triggers.Conditionals.Conditional;
import ecs.EntityManager;

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
