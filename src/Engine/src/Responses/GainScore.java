package Engine.src.Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public class GainScore implements Response{

    double myGain;

    public GainScore(double scoreGain){
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
