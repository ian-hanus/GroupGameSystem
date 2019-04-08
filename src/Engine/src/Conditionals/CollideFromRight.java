package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Physics.CollisionHandler;

    public class CollideFromRight extends ObjectConditional{

        CollideFromRight(boolean required, double obj){
            super(required, obj);
        }

        @Override
        public boolean satisfied(double other, ObjectManager objectManager){
            CollisionHandler collisionHandler = new CollisionHandler(objectManager);
            return collisionHandler.collideFromLeft(other, myObject);
        }

        @Override
        public boolean satisfied(ObjectManager objectManager){
            return false; //TODO:error
        }

        @Override
        public Conditional copy() {
            return new CollideFromRight(Required, myObject);
        }

    }

