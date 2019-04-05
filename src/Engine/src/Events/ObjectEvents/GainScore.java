package Events.ObjectEvents;

import Conditionals.Conditional;
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
    public void activate(ObjectManager objectManager){
        objectManager.increaseScore(myObject, myGain);
    }

    @Override
    public void activate(GameObject other, ObjectManager objectManager){
        objectManager.increaseScore(myObject, myGain);
    }
}
