package GameObjects;

import Powerups.Powerup;

import java.util.List;

public class PowerupItem extends GameObject{
    Powerup powerup;

    public PowerupItem(double myXPos, double myYPos, double myHealth, double myHeight, double myWidth,
                       String objectName, Powerup myPowerup) {
        super(myXPos, myYPos, myHealth, myHeight, myWidth, objectName);
        powerup = myPowerup;
    }

    public void updatePosition() {

    }

    public void updateStats() {

    }

    protected void setPowerup(Powerup powerup1) {
        powerup = powerup1;
    }

    public Powerup getPowerup() {
        return powerup;
    }
}
