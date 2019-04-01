package Engine.src.Responses;

import Engine.src.GameObjects.GameObject;

public class Die implements Response{
    @Override
    public void respond(GameObject obj) {
        obj.die();
    }
}
