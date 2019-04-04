package Engine.src.Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public interface Response {
    void respond(GameObject main, ObjectManager objectManager);
    void respond(GameObject main, GameObject other, ObjectManager objectManager);
}
