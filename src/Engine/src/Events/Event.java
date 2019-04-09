package Events;

import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import ECS.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class Event {
    protected List<Conditional> myConditionals;

    public Event(){
        myConditionals = new ArrayList<>();
    }

    public Event(List<Conditional> conditionals){
        myConditionals = conditionals;
    }

    public Event copy(){
        List<Conditional> conditionalsCopy = new ArrayList<>();
        for(Conditional conditional: myConditionals){
            conditionalsCopy.add(conditional.copy());
        }
        return new Event(conditionalsCopy);
    }

    public void setConditionalObject(int obj){
        for(Conditional conditional: myConditionals){
            if (conditional instanceof ObjectConditional){
                if (((ObjectConditional) conditional).getMyObj() == -1) ((ObjectConditional) conditional).setMyObject(obj);
            }
            }
    }

    public boolean conditionsSatisfied(int obj, EntityManager entityManager){
        for (Conditional conditional : myConditionals){
            if (!conditional.satisfied(obj, entityManager) && conditional.required()) return false;
        }
        return true;
    }

    public boolean conditionsSatisfied(EntityManager entityManager){
        for (Conditional conditional : myConditionals){
            if (!conditional.satisfied(entityManager) && conditional.required()) return false;
        }
        return true;
    }

    public List<Conditional> getConditionals() {
        return myConditionals;
    }
}
