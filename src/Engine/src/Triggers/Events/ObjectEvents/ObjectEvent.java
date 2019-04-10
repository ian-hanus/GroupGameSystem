package Triggers.Events.ObjectEvents;

import Triggers.Conditionals.Conditional;
import ECS.EntityManager;
import Triggers.Events.Event;

import java.util.List;

public abstract class ObjectEvent extends Event {

    protected int myObject;
    protected int myOther;

    public ObjectEvent(){
        super();
    }

    public ObjectEvent(int obj){
        super();
        myObject = obj;
        myOther = -1;
    }

    public ObjectEvent(List<Conditional> conditionals){
        super(conditionals);
        myObject = -1;
        myOther = -1;
    }

    public ObjectEvent(List<Conditional> conditionals, int obj){
        super(conditionals);
        myObject = obj;
        myOther = -1;
    }

    public void setEventObject(int obj){
        if(myObject == -1) myObject = obj;
    }

    public abstract void activate(EntityManager entityManager);

    public void setOther(int other){
        if(myOther == -1) myOther = other;
    }
}
