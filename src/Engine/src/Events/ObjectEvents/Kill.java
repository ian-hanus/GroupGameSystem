package Events.ObjectEvents;

import Conditionals.Conditional;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Kill extends ObjectEvent {

    public Kill() {super();}
    public Kill(double obj){super(obj);}
    public Kill(List<Conditional> conditionals){super(conditionals);}
    public Kill(List<Conditional> conditionals, double obj){
        super(conditionals, obj);
    }

    @Override
    public void activate(ObjectManager objectManager) { }

    @Override
    public void activate(double other, ObjectManager objectManager) {
        objectManager.kill(other);
    }
}
