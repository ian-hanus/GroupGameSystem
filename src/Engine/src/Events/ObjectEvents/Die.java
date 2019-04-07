package Events.ObjectEvents;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

    public class Die extends ObjectEvent {

        public Die() {super();}
        public Die(double obj){super(obj);}
        public Die(List<Conditional> conditionals){super(conditionals);}
        public Die(List<Conditional> conditionals, double obj){
            super(conditionals, obj);
        }


        @Override
        public void activate(ObjectManager objectManager) { objectManager.kill(myObject);}

        @Override
        public void activate(double other, ObjectManager objectManager) {
            objectManager.kill(myObject);
        }
    }


