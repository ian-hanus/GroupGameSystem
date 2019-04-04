package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class GivePowerup extends ObjectEvent {

    public GivePowerup(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(GameObject giver, GameObject other, ObjectManager objectManager){
        objectManager.addPowerup(giver, other);
    }

    @Override
    public void activate(GameObject giver, ObjectManager objectManager){
        // TODO: throw error
    }
}
