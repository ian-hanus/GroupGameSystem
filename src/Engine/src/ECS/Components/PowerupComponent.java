package ECS.Components;

import Powerups.Powerup;

public class PowerupComponent extends Component {
    private Powerup myPowerup;

    public PowerupComponent(Powerup powerup) {
        myPowerup = powerup;
    }
}
