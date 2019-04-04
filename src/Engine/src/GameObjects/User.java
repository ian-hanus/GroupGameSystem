package Engine.src.GameObjects;

import Engine.src.Powerups.Powerup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Yu
 */
public class User extends Character {

    private List<Powerup> activePowerups;

    public User(double xPos, double yPos, double health, double height, double width,
                double angle, String objectName, int zIndex, double[] direction, String filename, double jumpSpeed) {
        super(xPos, yPos, health, height, width, angle, objectName, zIndex, direction, filename, jumpSpeed);
        activePowerups = new ArrayList<>();
    }

    public void addPowerup(Powerup powerup) {
        activePowerups.add(powerup);
    }

    public List<Powerup> getActivePowerups() {
        return activePowerups;
    }
}
