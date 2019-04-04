package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Stop extends ObjectEvent {

    public Stop(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(ObjectManager objectManager) { objectManager.stop(myObject);}

    @Override
    public void activate(GameObject other, ObjectManager objectManager) {
        objectManager.stop(myObject);
    }
}
