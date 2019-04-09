package triggers.Events.ObjectEvents;

import triggers.Conditionals.Conditional;
import components.Component;
import ecs.EntityManager;

import java.util.List;
import java.util.Map;

public class Create extends ObjectEvent{

    Map<Class<? extends Component>, Component> myComponents;

    public Create(int obj, Map<Class<? extends Component>, Component> components){
        super(obj);
        myComponents = components;
    }

    public Create(List<Conditional> conditionals, int obj, Map<Class<? extends Component>, Component> components){
        super(conditionals, obj);
        myComponents = components;
    }

    @Override
    public void activate(EntityManager entityManager) {
        entityManager.create(myObject, myComponents);
    }

}
