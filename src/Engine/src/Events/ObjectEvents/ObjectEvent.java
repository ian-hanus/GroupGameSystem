package Events.ObjectEvents;

import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectEvent extends Event {

    double myObject;

    public ObjectEvent(){
        super();
    }

    public ObjectEvent(double obj){
        super();
        myObject = obj;
    }

    public ObjectEvent(List<Conditional> conditionals){
        super(conditionals);
        myObject = -1;
    }

    public ObjectEvent(List<Conditional> conditionals, double obj){
        super(conditionals);
        myObject = obj;
    }

    public void setEventObject(double obj){
        if(myObject == -1) myObject = obj;
    }

    public abstract void activate(ObjectManager objectManager);
    public abstract void activate(double other, ObjectManager objectManager);

}
