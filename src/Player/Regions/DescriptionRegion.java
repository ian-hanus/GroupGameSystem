package Player.Regions;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class DescriptionRegion extends Region {

    public static final double INVITATION_HEIGHT = 20;
    public static final double OFFSET = 10;

    private Properties myStyles;
    private Image myImage;
    private ScrollPane myDescPane;

    // TODO: unhardcode this
    public DescriptionRegion(double wd, double ht, Paint color) {
        super(wd, ht, color);

        myDescPane = new ScrollPane();
        myDescPane.setPrefWidth(wd);
        myDescPane.setPrefHeight(ht);
        myDescPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        myDescPane.setBackground(new Background(new BackgroundFill(
                myColor, new CornerRadii(5, 5, 5, 5, false), Insets.EMPTY)));
        myDescPane.setStyle("-fx-background: lightsteelblue; -fx-background-radius: 5; -fx-background-color: lightsteelblue;");

        myGroup = new Group();
        buildGroup();
        myDescPane.setContent(myGroup);

    }

    public void updateRegion(String game) {

        System.out.println("updating region");
        Description description = new Description(game);
//        myGroup.getChildren().removeAll();
        myGroup = new Group();
        myGroup.getChildren().add(description.getPane());
        myDescPane.setContent(myGroup);

    }

    // TODO: unhardcode this
    protected void buildGroup() {
        // this will be what the region looks like initially

        GridPane grid = new GridPane();
        Label invitation = new Label("Click on a game from the Games menu!");
        invitation.setStyle("-fx-font-family: 'Trebuchet MS'; -fx-font-size: 20; -fx-text-fill: 'white';");
        grid.setPadding(new Insets(OFFSET, OFFSET, OFFSET, OFFSET));
        grid.setAlignment(Pos.CENTER);
        grid.add(invitation, 0, 0, 1, 1);
        myGroup.getChildren().add(grid);

    }

    public ScrollPane getPane() {
        return myDescPane;
    }

}
