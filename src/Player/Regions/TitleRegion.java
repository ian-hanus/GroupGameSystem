package Player.Regions;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TitleRegion extends Region {

    public static final String TEXT_STR = "Cracking Open a Scrolled One";
    public static final String TEXT_FONT = "Trebuchet MS";
    public static final double TEXT_HEIGHT = 50;
    public static final String TEXT_COLOR = "white";
    public static final String TEXT_STYLE =
            "-fx-font-family: '" + TEXT_FONT + "'; " +
            "-fx-font-size: " + TEXT_HEIGHT + "; " +
            "-fx-text-fill: '" + TEXT_COLOR + "';" ;

    private GridPane myTitlePane;

    public TitleRegion(double wd, double ht, Paint color) {
        super(wd, ht, color); // this calls buildGroup, so anything that needs to be set must be set in Region()

        myTitlePane = new GridPane();
        myTitlePane.setPrefWidth(myWidth);
        myTitlePane.setPrefHeight(myHeight);
        myTitlePane.setAlignment(Pos.BOTTOM_LEFT);
        myTitlePane.setBackground(new Background(new BackgroundFill(
                myColor, new CornerRadii(5, 5, 5, 5, false), Insets.EMPTY)));

        myTitlePane.add(myGroup, 0, 0);

        buildGroup();
    }

    protected void buildGroup() {

        Label titleLabel = new Label(TEXT_STR);
        titleLabel.setStyle(TEXT_STYLE);

        myGroup.getChildren().add(titleLabel);

    }

    public GridPane getPane() {
        return myTitlePane;
    }
}
