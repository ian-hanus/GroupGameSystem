package Conditionals;

import ECS.CollisionDetector;
import ECS.EntityManager;
import ECS.NoEntityException;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Physics.CollisionHandler;

public class CollideFromTop extends ObjectConditional{

    CollideFromTop(boolean required, int obj){
        super(required, obj);
    }

    @Override
    public boolean satisfied(int other, EntityManager entityManager) {
        CollisionDetector collisionDetector = new CollisionDetector(entityManager);
        return collisionDetector.collideFromTop(myObject, other);
    }

    @Override
    public boolean satisfied(EntityManager entityManager){
        return false; //TODO:error
    }

    @Override
    public Conditional copy() {
        return new CollideFromTop(Required, myObject);
    }
}