package Events;

import Conditionals.Conditional;
import GameObjects.GameObject;

import java.util.List;

public class Event {
    private List<Conditional> myConditionals;

    public Event(List<Conditional> conditionals){
        myConditionals = conditionals;
    }

    public boolean conditionsSatisfied(GameObject obj1, GameObject obj2){
        for (Conditional conditional : myConditionals){
            if (!conditional.satisfied(obj1, obj2)) return false;
        }
        return true;
    }

    public boolean conditionsSatisfied(){
        for (Conditional conditional : myConditionals){
            if (!conditional.satisfied()) return false;
        }
        return true;
    }
}
