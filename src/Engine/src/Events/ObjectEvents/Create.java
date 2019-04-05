package Events.ObjectEvents;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Create extends ObjectEvent{
    public Create(List<Conditional> conditionals, GameObject obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(ObjectManager objectManager) {
        objectManager.create(myObject);
    }

    @Override
    public void activate(GameObject other, ObjectManager objectManager) {
        objectManager.create(myObject);
    }
}
