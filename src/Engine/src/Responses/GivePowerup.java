package Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Powerups.Powerup;

public class GivePowerup implements Response{

    @Override
    public void respond(GameObject giver, GameObject other, ObjectManager objectManager){
        objectManager.addPowerup(giver, other);
    }
}
