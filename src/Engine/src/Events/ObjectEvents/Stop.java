package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Stop extends Event {

    public Stop(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(GameObject obj, ObjectManager objectManager) { objectManager.stop(obj);}

    @Override
    public void activate(GameObject obj, GameObject other, ObjectManager objectManager) {
        objectManager.kill(other);
    }
}
