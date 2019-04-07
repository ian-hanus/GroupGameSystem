package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Physics.CollisionHandler;

public class CollideFromTop extends ObjectConditional{

    CollideFromTop(double obj){
        super(obj);
    }

    @Override
    public boolean satisfied(double other, ObjectManager objectManager){
        CollisionHandler collisionHandler = new CollisionHandler(objectManager);
        return collisionHandler.collideFromTop(myObject, other);
    }

    @Override
    public boolean satisfied(ObjectManager objectManager){
        return false; //TODO:error
    }

    @Override
    public Conditional copy() {
        return new CollideFromTop(myObject);
    }
}