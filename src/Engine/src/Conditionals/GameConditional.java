package Conditionals;

import ECS.EntityManager;
import GameObjects.ObjectManager;

public abstract class GameConditional extends Conditional{
    //must implement BOTH satisfieds in SAME WAY

    public GameConditional(boolean required){
        super(required);
    }

    @Override
    public abstract boolean satisfied(int obj, EntityManager entityManager);

    @Override
    public abstract boolean satisfied(EntityManager entityManager);
}
