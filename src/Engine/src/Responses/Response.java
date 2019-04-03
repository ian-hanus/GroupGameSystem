package Responses;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public abstract class Response {
    private List<Conditional> myConditionals;

    public Response(List<Conditional> conditionals){
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

    public abstract void respond(GameObject main, ObjectManager objectManager);
    public abstract void respond(GameObject main, GameObject other, ObjectManager objectManager);
}
