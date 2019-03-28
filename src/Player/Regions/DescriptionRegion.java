package Player.Regions;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DescriptionRegion extends Region {

    private Image myImage;
    private ScrollPane myDescPane;

    public DescriptionRegion(double wd, double ht, Paint color) {
        super(wd, ht, color);

        myDescPane = new ScrollPane();
        myDescPane.setPrefWidth(wd);
        myDescPane.setPrefHeight(ht);
        myDescPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        myDescPane.setBackground(new Background(new BackgroundFill(
                myColor, new CornerRadii(5, 5, 5, 5, false), Insets.EMPTY)));
        // TODO: un-hardcode this
        myDescPane.setStyle("-fx-background: lightsteelblue; -fx-background-radius: 5; -fx-background-color: lightsteelblue");

        myGroup = new Group();
        buildGroup();

        myDescPane.setContent(myGroup);

    }

    protected void buildGroup() {
        Rectangle x = new Rectangle(20, 20, myColor);
        Label t = new Label("yeet");
        myGroup.getChildren().add(x);
        myGroup.getChildren().add(t);



    }

    public ScrollPane getPane() {
        return myDescPane;
    }

}
