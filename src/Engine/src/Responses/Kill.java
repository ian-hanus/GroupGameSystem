package Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public class Kill implements Response{
    @Override
    public void respond(GameObject obj, ObjectManager objectManager) {
        objectManager.kill(obj);
    }
}
