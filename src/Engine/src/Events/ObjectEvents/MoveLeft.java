package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;
import GameObjects.ObjectManager;

import java.util.List;

public class MoveLeft extends ObjectEvent {

    public MoveLeft() {super();}
    public MoveLeft(int obj){super(obj);}
    public MoveLeft(List<Conditional> conditionals){super(conditionals);}
    public MoveLeft(List<Conditional> conditionals, int obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(EntityManager entityManager) { }

    @Override
    public void activate(int other, EntityManager entityManager) {
        entityManager.setDirection(myObject, 180);
    }
}
