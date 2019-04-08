package Events.ObjectEvents;

import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import ECS.EntityManager;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectEvent extends Event {

    int myObject;

    public ObjectEvent(){
        super();
    }

    public ObjectEvent(int obj){
        super();
        myObject = obj;
    }

    public ObjectEvent(List<Conditional> conditionals){
        super(conditionals);
        myObject = -1;
    }

    public ObjectEvent(List<Conditional> conditionals, int obj){
        super(conditionals);
        myObject = obj;
    }

    public void setEventObject(int obj){
        if(myObject == -1) myObject = obj;
    }

    public abstract void activate(EntityManager entityManager);
    public abstract void activate(int other, EntityManager entityManager);

}
