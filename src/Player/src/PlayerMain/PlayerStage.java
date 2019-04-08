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
    private final String STYLESHEET = "style.css";
    private final String GRIDPANE_STYLESHEET = "gridPane";
    private final String GAMES_STYLESHEET = "gamesRegion";
    private final String DESC_STYLESHEET = "descRegion";
    private final String TITLE_STYLESHEET = "titleRegion";

    public final String ST_TITLE = "Cracking Open a Scrolled One with the Boys";
    public final double ST_WIDTH = 1200;
    public final double ST_HEIGHT = 600;
    public final Paint ST_COLOR = Color.web("284376");
    public final double ST_SPACING = 20;

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

        GamesRegion gamesRegion = new GamesRegion(GAMES_STYLESHEET);
        DescriptionRegion descRegion = new DescriptionRegion(DESC_STYLESHEET, this);
        TitleRegion titleRegion = new TitleRegion(TITLE_STYLESHEET);

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

    /**
     * Run(), edit(), rate() currently placeholder. Update these methods.
     */
    public void run(String gameName) {
        System.out.println(gameName + " is running!");
    }

    public void edit(String gameName) {
        System.out.println(gameName + " is being edited!");
    }

    public void rate(String gameName) {
        System.out.println("Rating for " + gameName + " is being changed!");
    }

    protected Stage makeStage() {
        Stage ret = new Stage();
        ret.setTitle(ST_TITLE);
        ret.setScene(myScene);
        return ret;
    }
}