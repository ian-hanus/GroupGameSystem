package Player;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DescriptionRegion extends Region {

    private Image myImage;

    public DescriptionRegion() {
        super();
    }

    protected void buildRegion() {
        Rectangle x = new Rectangle(20, 20, Color.BLUE);
        myGroup.getChildren().add(x);
    }

}
