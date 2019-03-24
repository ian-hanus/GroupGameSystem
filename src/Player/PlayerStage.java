package Player;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayerStage {

    public final String ST_TITLE = "Cracking Open a Scrolled One with the Boys";
    public final double ST_WIDTH = 1200;
    public final double ST_HEIGHT = 600;
    public final Paint ST_COLOR = Color.DARKSLATEGRAY;
    public final double ST_SPACING = 20;
    public final double ST_PADDING = 15;

    public final double GAMES_WIDTH = 380; // games width has priority
    public final double GAMES_HEIGHT = ST_HEIGHT - (2 * ST_PADDING);
    public final double TITLE_WIDTH = ST_WIDTH - GAMES_WIDTH - (2 * ST_PADDING) - ST_SPACING;
    public final double TITLE_HEIGHT = 100; // title ht has priority
    public final double DESC_WIDTH = TITLE_WIDTH;
    public final double DESC_HEIGHT = ST_HEIGHT - TITLE_HEIGHT - (2 * ST_PADDING) - ST_SPACING;

//    private double myWidth = ST_WIDTH;
//    private double myHeight = ST_HEIGHT;
//    private Paint myColor = ST_COLOR;
    private Scene myScene;
    private GridPane myVisualRoot;

    public PlayerStage() {

        myVisualRoot = buildRoot();
//        myScene = new Scene(myVisualRoot, myWidth, myHeight, myColor);
        myScene = new Scene(myVisualRoot, ST_WIDTH, ST_HEIGHT, ST_COLOR);

    }

    public GridPane buildRoot() {

        GridPane base = new GridPane();
        base.setBackground(new Background(new BackgroundFill(ST_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        base.setAlignment(Pos.CENTER);
        base.setPadding(new Insets(ST_PADDING, ST_PADDING, ST_PADDING, ST_PADDING));
        base.setVgap(ST_SPACING);
        base.setHgap(ST_SPACING);

        // gamesPane will be a scroll pane
        ScrollPane gamesPane = new ScrollPane();
        GamesRegion gamesRegion = new GamesRegion();
        gamesPane.setContent(gamesRegion.getNode());

        // descPane will be a scroll pane
        ScrollPane descPane = new ScrollPane();
        DescriptionRegion descRegion = new DescriptionRegion();
        descPane.setContent(descRegion.getNode());

        // titlePane will be a regular pane
        GridPane titlePane = new GridPane();
        TitleRegion titleRegion = new TitleRegion();
        titlePane.setAlignment(Pos.CENTER);
        titlePane.add(titleRegion.getNode(), 0, 0);

        base.add(titlePane, 0, 0, 1, 1);
        base.add(descPane, 0, 1, 1, 1);
        base.add(gamesPane, 1, 0, 1, 2);

//        HBox x = new HBox();
//        x.setPrefHeight(TITLE_HEIGHT);
//        x.setPrefWidth(TITLE_WIDTH);
//        x.getChildren().add(new Rectangle(TITLE_WIDTH, TITLE_HEIGHT, Color.GREEN));
////        x.setSpacing(ST_SPACING);
////        x.setPadding(new Insets(ST_PADDING));
//
//        base.add(x,0, 0, 1, 1);
//
//        HBox y = new HBox();
//        y.setPrefHeight(DESC_HEIGHT);
//        y.setPrefWidth(DESC_WIDTH);
//        y.getChildren().add(new Rectangle(DESC_WIDTH, DESC_HEIGHT, Color.BLUE));
////        y.setSpacing(ST_SPACING);
////        y.setPadding(new Insets(ST_PADDING));
//
//        base.add(y, 0, 1, 1, 1);
//
//        HBox z = new HBox();
//        z.setPrefHeight(GAMES_HEIGHT);
//        z.setPrefWidth(GAMES_WIDTH);
//        z.setMinWidth(GAMES_WIDTH);
//        z.getChildren().add(new Rectangle(GAMES_WIDTH, GAMES_HEIGHT, Color.RED));
////        z.setSpacing(ST_SPACING);
////        z.setPadding(new Insets(ST_PADDING));
//
//        base.add(z, 1, 0, 1, 2);





//        Region left = new Region();
//        HBox.setHgrow(left, Priority.ALWAYS);
//        left.setBackground(new Background(new BackgroundFill(myBGColor, CornerRadii.EMPTY, Insets.EMPTY)));
//        Region right = new Region();
//        HBox.setHgrow(right, Priority.ALWAYS);
//        right.setBackground(new Background(new BackgroundFill(myBGColor, CornerRadii.EMPTY, Insets.EMPTY)));


        return base;
    }

//    private VBox buildTitleDescRegion() {
//        VBox base = new VBox();
//
//        base.getChildren().addAll(
//                buildTitleRegion(),
//                buildDescRegion());
//
//        return base;
//    }
//
//    private HBox buildTitleRegion() {
//        HBox base = new HBox();
//
//        base.setPrefHeight(TITLE_HEIGHT);
//        base.setPrefWidth(TITLE_WIDTH);
//
//        base.getChildren().add(new Rectangle(TITLE_WIDTH, TITLE_HEIGHT, Color.GREEN));
//
//        return base;
//    }
//
//    private HBox buildDescRegion() {
//        HBox base = new HBox();
//
//        base.setPrefHeight(DESC_HEIGHT);
//        base.setPrefWidth(DESC_WIDTH);
//
//        base.getChildren().add(new Rectangle(DESC_WIDTH, DESC_HEIGHT, Color.BLUE));
//
//        return base;
//    }
//
//    private ScrollPane buildGamesRegion() {
//        ScrollPane base = new ScrollPane();
//
//        base.setPrefHeight(GAMES_HEIGHT);
//        base.setPrefWidth(GAMES_WIDTH);
//
//        Group inside = new Group();
//        inside.getChildren().add(new Rectangle(20, 20, Color.RED));
//        base.setContent(inside);
//
//        return base;
//    }

    protected Stage makeStage() {
        Stage ret = new Stage();
        ret.setTitle(ST_TITLE);
        ret.setScene(myScene);
        return ret;
    }
}