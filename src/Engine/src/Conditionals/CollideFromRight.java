package Conditionals;

import ECS.CollisionDetector;
import ECS.EntityManager;
import ECS.NoEntityException;
import Physics.CollisionHandler;

public class CollideFromRight extends ObjectConditional{

        CollideFromRight(boolean required, int obj){
            super(required, obj);
        }

        @Override
        public boolean satisfied(int other, EntityManager entityManager) {
            CollisionDetector collisionDetector = new CollisionDetector(entityManager);
            return collisionDetector.collideFromLeft(other, myObject);
        }

        @Override
        public boolean satisfied(EntityManager entityManager){
            return false; //TODO:error
        }

        @Override
        public Conditional copy() {
            return new CollideFromRight(Required, myObject);
        }

    }

