package Engine.src.Responses;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import GameObjects.PowerupItem;
import Powerups.Powerup;

public class GivePowerup implements Response{

    @Override
    public void respond(GameObject giver, GameObject other, ObjectManager objectManager){
        objectManager.addPowerup(giver, other);
    }

    @Override
    public void respond(GameObject giver, ObjectManager objectManager){
        // TODO: throw error
    }
}
