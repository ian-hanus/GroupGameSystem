package Conditionals;

import GameObjects.GameObject;
import Physics.CollisionHandler;

    public class CollideFromRight implements Conditional{

        @Override
        public boolean satisfied() {
            return false;
        }

        @Override
        public boolean satisfied(GameObject obj1, GameObject obj2){
            CollisionHandler collisionHandler = new CollisionHandler();
            return collisionHandler.collideFromLeft(obj2, obj1);
        }
    }

