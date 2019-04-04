package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Kill extends ObjectEvent {

    public Kill(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(ObjectManager objectManager) { }

    @Override
    public void activate(GameObject other, ObjectManager objectManager) {
        objectManager.kill(other);
    }
}
