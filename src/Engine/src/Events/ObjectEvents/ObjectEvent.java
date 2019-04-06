package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public abstract class ObjectEvent extends Event {

    GameObject myObject;

    public ObjectEvent(List<Conditional> conditionals){
        super(conditionals);
    }

    public ObjectEvent(List<Conditional> conditionals, GameObject obj){
        super(conditionals);
        myObject = obj;
    }

    public void setMyObject(GameObject obj){
        myObject = obj;
    }
    public abstract void activate(ObjectManager objectManager);
    public abstract void activate(GameObject other, ObjectManager objectManager);
}
