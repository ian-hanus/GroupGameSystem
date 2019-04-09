package Events.ObjectEvents;

import Conditionals.Conditional;
import ECS.EntityManager;

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

    }


