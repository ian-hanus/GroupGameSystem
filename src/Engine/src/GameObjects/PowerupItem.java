package GameObjects;

import Powerups.Powerup;

import java.util.List;

public class PowerupItem extends GameObject{
    Powerup powerup;

    public PowerupItem(double xPos, double yPos, double health, double height, double width,
                       double angle, String objectName, int zIndex, double[] direction, String filename, Powerup myPowerup) {
        super(xPos, yPos, health, height, width, angle, objectName, zIndex, direction, filename);
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
