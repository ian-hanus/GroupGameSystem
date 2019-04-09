package Regions;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.util.Properties;

public class DescriptionRegion extends Region {

    public static final double INVITATION_HEIGHT = 20;
    public static final double OFFSET = 10;

    private Properties myStyles;
    private Image myImage;
    private ScrollPane myDescPane;

    public DescriptionRegion(String regionID) {
        super();

        myDescPane = new ScrollPane();
        myDescPane.setId(regionID);

        myGroup = new Group();
        buildGroup();
        myDescPane.setContent(myGroup);

    }

    public void updateRegion(String game) {

        System.out.println("updating region");
        myGroup.getChildren().clear();
        Description description = new Description(game);
//        myGroup.getChildren().add(description.getPane());
        myDescPane.setContent(description.getPane());

    }

    // TODO: unhardcode this
    protected void buildGroup() {
        // this will be what the region looks like initially

        GridPane grid = new GridPane();
        Label invitation = new Label("Click on a game from the Games menu!");

        grid.add(invitation, 0, 0, 1, 1);
        myGroup.getChildren().add(grid);

    }

    public ScrollPane getPane() {
        return myDescPane;
    }

}
