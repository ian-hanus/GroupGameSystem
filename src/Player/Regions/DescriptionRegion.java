package Player.Regions;

import javafx.geometry.Insets;
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

        myDescPane.setContent(myGroup);

    }

    protected void buildGroup() {
        Rectangle x = new Rectangle(20, 20, myColor);
        myGroup.getChildren().add(x);



    }

    public ScrollPane getPane() {
        return myDescPane;
    }

}
