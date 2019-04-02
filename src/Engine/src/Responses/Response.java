package Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public interface Response {
    void respond(GameObject obj, ObjectManager objectManager);
}
