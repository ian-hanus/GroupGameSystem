package Player;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GamesRegion extends Region {

    private ArrayList<Thumbnail> myGames;

    public GamesRegion() {
        super();
    }

    protected void buildRegion() {
        Rectangle x = new Rectangle(20, 20, Color.RED);
        myGroup.getChildren().add(x);
    }



}
