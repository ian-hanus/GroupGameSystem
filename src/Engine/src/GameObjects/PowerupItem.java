package Engine.src.GameObjects;

import javafx.scene.image.ImageView;
import java.util.List;

public class PowerupItem extends GameObject{

    public PowerupItem(double myXPos, double myYPos, double myHealth, List activeObjects, ImageView myImage) {
        super(myXPos, myYPos, myHealth, activeObjects, myImage);
    }

    protected void updatePosition() {

    }

    protected void updateStats() {

    }
}
