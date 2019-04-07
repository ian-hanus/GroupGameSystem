package Events.ObjectEvents;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

    public class Die extends ObjectEvent {

        public Die(List<Conditional> conditionals){
            super(conditionals);
        }

        @Override
        public void activate(ObjectManager objectManager) { objectManager.kill(myObject);}

        @Override
        public void activate(double other, ObjectManager objectManager) {
            objectManager.kill(myObject);
        }
    }


