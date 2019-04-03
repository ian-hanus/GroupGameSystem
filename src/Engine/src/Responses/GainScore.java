package Responses;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class GainScore extends Response{

    double myGain;

    public GainScore(List<Conditional> conditionals, double scoreGain){
        super(conditionals);
        myGain = scoreGain;
    }

    @Override
    public void respond(GameObject obj, ObjectManager objectManager){
        objectManager.increaseScore(obj, myGain);
    }

    @Override
    public void respond(GameObject obj, GameObject other, ObjectManager objectManager){
        objectManager.increaseScore(obj, myGain);
    }
}
