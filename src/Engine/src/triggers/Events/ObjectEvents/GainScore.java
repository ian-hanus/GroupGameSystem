package triggers.Events.ObjectEvents;

import triggers.Conditionals.Conditional;
import ecs.EntityManager;

import java.util.List;

public class GainScore extends ObjectEvent {

    double myGain;

    public GainScore(double scoreGain) {
        super();
        myGain = scoreGain;
    }
    public GainScore(int obj, double scoreGain){
        super(obj);
        myGain = scoreGain;
    }
    public GainScore(List<Conditional> conditionals, double scoreGain){
        super(conditionals);
        myGain = scoreGain;
    }
    public GainScore(List<Conditional> conditionals, int obj, double scoreGain){
        super(conditionals, obj);
        myGain = scoreGain;
    }

    @Override
    public void activate(EntityManager entityManager){
        entityManager.increaseScore(myObject, myGain);
    }

}
