package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class GivePowerup extends ObjectEvent {

    public GivePowerup(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(ObjectManager objectManager){
        //TODO: throw error
    }

    @Override
    public void activate(int other, EntityManager entityManager){
        entityManager.addPowerup(myObject, other);
    }
}
