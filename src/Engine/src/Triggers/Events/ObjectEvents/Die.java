package Triggers.Events.ObjectEvents;

import Triggers.Conditionals.Conditional;
import ECS.EntityManager;
import Triggers.Events.Event;

import java.util.List;

    public class Die extends ObjectEvent {

        public Die() {super();}
        public Die(int obj){super(obj);}
        public Die(List<Conditional> conditionals){super(conditionals);}
        public Die(List<Conditional> conditionals, int obj){
            super(conditionals, obj);
        }


        @Override
        public void activate(EntityManager entityManager) { entityManager.die(myObject);}

        @Override
        public Event copy() {
            return new Die(copyConditionals(), myObject);
        }
    }


