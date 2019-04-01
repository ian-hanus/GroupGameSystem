package Responses;

import GameObjects.GameObject;

public class Die extends Response{
    @Override
    public void respond(GameObject obj) {
        obj.die();
    }
}
