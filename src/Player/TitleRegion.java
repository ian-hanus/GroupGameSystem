package Player;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TitleRegion extends Region {

    public TitleRegion() {
        super();
    }

    protected void buildRegion() {
        Rectangle x = new Rectangle(20, 20, Color.GREEN);
        myGroup.getChildren().add(x);
    }
}
