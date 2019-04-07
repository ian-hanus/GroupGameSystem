package Events;

import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Event {
    protected List<Conditional> myConditionals;

    public Event(List<Conditional> conditionals){
        myConditionals = conditionals;
    }

    /*
    public boolean conditionsSatisfied(double obj1, double obj2, ObjectManager objectManager){
        for (Conditional conditional : myConditionals){
            if (!conditional.satisfied(obj1, obj2, objectManager)) return false;
        }
        return true;
    }
    */

    public void setConditionalObject(double obj){
        for(Conditional conditional: myConditionals){
            if(conditional instanceof ObjectConditional && conditional.getMyObj() == -1) ((ObjectConditional) conditional).setMyObject(obj);
        }
    }

    public boolean conditionsSatisfied(double obj, ObjectManager objectManager){
        for (Conditional conditional : myConditionals){
            if (!conditional.satisfied(obj, objectManager)) return false;
        }
        return true;
    }

    public boolean conditionsSatisfied(ObjectManager objectManager){
        for (Conditional conditional : myConditionals){
            if (!conditional.satisfied(objectManager)) return false;
        }
        return true;
    }

    public List<Conditional> getConditionals() {
        return myConditionals;
    }
}
