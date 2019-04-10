package Engine.src.Triggers.Events.ObjectEvents;

import Engine.src.Triggers.Conditionals.Conditional;
import Engine.src.ECS.EntityManager;

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
