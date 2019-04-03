package Responses;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public class Kill extends Response{

    public Kill(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void respond(GameObject obj, ObjectManager objectManager) {
        objectManager.kill(obj);
    }

    @Override
    public void respond(GameObject obj, GameObject other, ObjectManager objectManager) {
        objectManager.kill(obj);
    }
}
