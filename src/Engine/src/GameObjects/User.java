package Engine.src.GameObjects;

import Powerups.Powerup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Yu
 */
public class User extends GameObject {

    List<Powerup> activePowerups;

    public User(double xPos, double yPos, double health, double height, double width,
                double angle, String objectName, int zIndex, double[] direction, String filename) {
        super(xPos, yPos, health, height, width, angle, objectName, zIndex, direction, filename);
        activePowerups = new ArrayList<>();
    }

    public void addPowerup(Powerup powerup) {
        activePowerups.add(powerup);
    }

    public List<Powerup> getActivePowerups() {
        return activePowerups;
    }
}
