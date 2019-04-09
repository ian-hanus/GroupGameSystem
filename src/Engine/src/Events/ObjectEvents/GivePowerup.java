package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;

import java.util.List;

public class GivePowerup extends ObjectEvent {

    public GivePowerup(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(EntityManager entityManager) {
        //TODO: throw error
    }

    @Override
    public void activate(int other, EntityManager entityManager){
        entityManager.addPowerup(myObject, other);
    }
}
