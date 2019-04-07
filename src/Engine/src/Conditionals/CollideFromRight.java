package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Physics.CollisionHandler;

    public class CollideFromRight extends ObjectConditional{

        @Override
        public boolean satisfied(double other, ObjectManager objectManager){
            CollisionHandler collisionHandler = new CollisionHandler(objectManager);
            return collisionHandler.collideFromLeft(other, myObject);
        }

        @Override
        public boolean satisfied(ObjectManager objectManager){
            return false; //TODO:error
        }

    }

