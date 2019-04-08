package Events.ObjectEvents;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class GainScore extends ObjectEvent {

    double myGain;

    public GainScore(double scoreGain) {
        super();
        myGain = scoreGain;
    }
    public GainScore(double obj, double scoreGain){
        super(obj);
        myGain = scoreGain;
    }
    public GainScore(List<Conditional> conditionals, double scoreGain){
        super(conditionals);
        myGain = scoreGain;
    }
    public GainScore(List<Conditional> conditionals, double obj, double scoreGain){
        super(conditionals, obj);
        myGain = scoreGain;
    }

    @Override
    public void activate(ObjectManager objectManager){
        objectManager.increaseScore(myObject, myGain);
    }

    @Override
    public void activate(double other, ObjectManager objectManager){
        objectManager.increaseScore(myObject, myGain);
    }
}
