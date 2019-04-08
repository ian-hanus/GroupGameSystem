package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.Components.Component;
import ECS.EntityManager;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.ArrayList;
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

    @Override
    public void activate(int other, EntityManager entityManager) {
        entityManager.create(myObject, myComponents);
    }
}
