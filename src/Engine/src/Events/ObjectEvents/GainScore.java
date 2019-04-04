package Events.ObjectEvents;

import Conditionals.Conditional;
import Events.Event;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class GainScore extends ObjectEvent {

    double myGain;

    public GainScore(List<Conditional> conditionals, double scoreGain){
        super(conditionals);
        myGain = scoreGain;
    }

    @Override
    public void activate(GameObject obj, ObjectManager objectManager){
        objectManager.increaseScore(obj, myGain);
    }

    @Override
    public void activate(GameObject obj, GameObject other, ObjectManager objectManager){
        objectManager.increaseScore(obj, myGain);
    }
}
