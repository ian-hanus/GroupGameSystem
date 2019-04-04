package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

    public class Die extends ObjectEvent {

        public Die(List<Conditional> conditionals){
            super(conditionals);
        }

        @Override
        public void activate(GameObject obj, ObjectManager objectManager) { objectManager.kill(obj);}

        @Override
        public void activate(GameObject obj, GameObject other, ObjectManager objectManager) {
            objectManager.kill(obj);
        }
    }


