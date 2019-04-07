package PlayerMain;

import Regions.DescriptionRegion;
import Regions.GamesRegion;
import Regions.Thumbnail;
import Regions.TitleRegion;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlayerStage {
    public final String STYLESHEET = "style.css";
    public final String GRIDPANE_STYLESHEET = "gridPane";
    public final String ST_TITLE = "Cracking Open a Scrolled One with the Boys";
    public final double ST_WIDTH = 1200;
    public final double ST_HEIGHT = 600;
    public final Paint ST_COLOR = Color.web("284376");
    public final Paint PANE_COLOR = Color.web("677DA5");
    public final double ST_SPACING = 20;
    public final double ST_PADDING = 15;

    public final double GAMES_WIDTH = 380; // games width has priority
    public final double GAMES_HEIGHT = ST_HEIGHT - (2 * ST_PADDING);
    public final double TITLE_WIDTH = ST_WIDTH - GAMES_WIDTH - (2 * ST_PADDING) - ST_SPACING;
    public final double TITLE_HEIGHT = 75; // title ht has priority
    public final double DESC_WIDTH = TITLE_WIDTH;
    public final double DESC_HEIGHT = ST_HEIGHT - TITLE_HEIGHT - (2 * ST_PADDING) - ST_SPACING;

    private Scene myScene;
    private GridPane myVisualRoot;
    private ArrayList<String> myGames;
    private ArrayList<String> myImageFiles;

    public PlayerStage() {

        myVisualRoot = buildRoot();
        myScene = new Scene(myVisualRoot, ST_WIDTH, ST_HEIGHT, ST_COLOR);
        myScene.getStylesheets().add(STYLESHEET);

    }

    public GridPane buildRoot() {

        GridPane base = new GridPane();
        base.getStyleClass().add(GRIDPANE_STYLESHEET);
        base.setVgap(ST_SPACING);
        base.setHgap(ST_SPACING);

        GamesRegion gamesRegion = new GamesRegion(GAMES_WIDTH, GAMES_HEIGHT, PANE_COLOR);
        DescriptionRegion descRegion = new DescriptionRegion(DESC_WIDTH, DESC_HEIGHT, PANE_COLOR);
        TitleRegion titleRegion = new TitleRegion(TITLE_WIDTH, TITLE_HEIGHT, ST_COLOR);

        ScrollPane gamesPane = gamesRegion.getPane();
        setLambdas(gamesRegion.getThumbnails(), descRegion);
        ScrollPane descPane = descRegion.getPane();
        GridPane titlePane = titleRegion.getPane();

        base.add(titlePane, 0, 0, 1, 1);
        base.add(descPane, 0, 1, 1, 1);
        base.add(gamesPane, 1, 0, 1, 2);

        return base;
    }

    private void setLambdas(ArrayList<Thumbnail> thumbnails, DescriptionRegion descRegion) {
        for (Thumbnail thumb : thumbnails) {
            StackPane thumbPane = thumb.getPane();
            thumbPane.setOnMouseClicked(e -> descRegion.updateRegion(thumb.getName()));
        }
    }

    protected Stage makeStage() {
        Stage ret = new Stage();
        ret.setTitle(ST_TITLE);
        ret.setScene(myScene);
        return ret;
    }
}