package Responses;

import Conditionals.Conditional;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import GameObjects.PowerupItem;
import Powerups.Powerup;

import java.util.List;

public class GivePowerup extends Response{

    public GivePowerup(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void respond(GameObject giver, GameObject other, ObjectManager objectManager){
        objectManager.addPowerup(giver, other);
    }

    @Override
    public void respond(GameObject giver, ObjectManager objectManager){
        // TODO: throw error
    }
}
