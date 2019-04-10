package PlayerMain;

import Components.BasicComponent;
import Components.Component;
import Controller.Controller;
import Regions.DescriptionRegion;
import Regions.GamesRegion;
import Regions.Thumbnail;
import Regions.TitleRegion;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public final double STEP_TIME = 30;
    public final double GAME_WIDTH = 1400;
    public final double GAME_HEIGHT = 800;
    public final Paint GAME_BG = Color.BLACK;

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

        // make an instance of the Stage

        Stage gameStage = new Stage();
        Group gameRoot = new Group();

        // put everything into gameRoot

        Map<Integer, ImageView> imageViewMap = new HashMap<>();

        Controller gameController = new Controller(STEP_TIME, GAME_WIDTH, GAME_HEIGHT, GAME_WIDTH, GAME_HEIGHT);
        Map<Integer, Map<Class<? extends Component>, Component>> gameEntities = gameController.getEntities();

        for (Integer id: gameEntities.keySet()) {
            BasicComponent ent = (BasicComponent) gameEntities.get(id).get(BasicComponent.class);
            double entWd = ent.getWidth();
            double entHt = ent.getHeight();
            double entX = ent.getX();
            double entY = ent.getY();
//            double entZIndex = ent.getZindex();
            String entFilename = ent.getMyFilename();

            ImageView iv = new ImageView();
            try {
                iv = new ImageView(new Image(this.getClass().getResourceAsStream(entFilename)));
                iv.setX(entX);
                iv.setY(entY);
                iv.setFitWidth(entWd);
                iv.setFitHeight(entHt);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("uh oh");
            }

            imageViewMap.put(id, iv);
        }

        gameRoot.getChildren().addAll(imageViewMap.values());

        Scene gameScene = new Scene(gameRoot, GAME_BG);
        gameStage.setScene(gameScene);
        gameStage.show();

        // start game loop
//        while (true) {
//            Map<Integer, ImageView> newImageViewMap = new HashMap<>();
//            Map<Integer, Map<Class<? extends Component>, Component>> newGameEntities = gameController.getEntities();
//            for (Integer newID: newGameEntities.keySet()) {
//                BasicComponent newEnt = (BasicComponent) gameEntities.get(newID).get(BasicComponent.class);
//                double newEntWd = newEnt.getWidth();
//                double newEntHt = newEnt.getHeight();
//                double newEntX = newEnt.getX();
//                double newEntY = newEnt.getY();
////            double entZIndex = ent.getZindex();
//                File entFile = newEnt.getMyFile();
//                ImageView newIV = new ImageView();
//                try {
//                    newIV = new ImageView(entFile.getAbsolutePath());
//                    newIV.setX(newEntX);
//                    newIV.setY(newEntY);
//                    newIV.setFitWidth(newEntWd);
//                    newIV.setFitHeight(newEntHt);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("uh oh");
//                }
//                imageViewMap.put(newID, newIV);
//            }
//            gameRoot.getChildren().clear();
//            gameRoot.getChildren().addAll(imageViewMap.values());
//        }

        while (true) {
            for (int id : gameEntities.keySet()) {
                // check if key is in imageViewMap
                BasicComponent newEnt = (BasicComponent) gameEntities.get(id).get(BasicComponent.class);
                double newEntWd = newEnt.getWidth();
                double newEntHt = newEnt.getHeight();
                double newEntX = newEnt.getX();
                double newEntY = newEnt.getY();
//            double entZIndex = ent.getZindex();
                String entFilename = newEnt.getMyFilename();
                if (imageViewMap.containsKey(id)) {
                    // if so check for diffs in objects
                    ImageView curr = imageViewMap.get(id);
                    double currWd = curr.getFitWidth();
                    double currHt = curr.getFitHeight();
                    double currX = curr.getX();
                    double currY = curr.getY();
                    if (newEntWd != currWd || newEntHt != currHt || newEntX != currX || newEntY != currY) {
                        ImageView newIV = new ImageView();
                        try {
                            newIV = new ImageView(new Image(this.getClass().getResourceAsStream(entFilename)));
                            newIV.setX(newEntX);
                            newIV.setY(newEntY);
                            newIV.setFitWidth(newEntWd);
                            newIV.setFitHeight(newEntHt);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("uh oh");
                        }
                        imageViewMap.put(id, newIV);
                        gameRoot.getChildren().add(imageViewMap.get(id));
                    }
                }
                // if key is not in imageViewMap add component
                else {
                    ImageView newIV = new ImageView();
                    try {
                        newIV = new ImageView(new Image(this.getClass().getResourceAsStream(entFilename)));
                        newIV.setX(newEntX);
                        newIV.setY(newEntY);
                        newIV.setFitWidth(newEntWd);
                        newIV.setFitHeight(newEntHt);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("uh oh");
                    }
                    imageViewMap.put(id, newIV);
                    gameRoot.getChildren().add(imageViewMap.get(id));
                }
            }
            for (int key : imageViewMap.keySet()) {
                // if id no longer in gameEntities remove from imageViewMap
                if (! gameEntities.containsKey(key)) {
                    gameRoot.getChildren().remove(imageViewMap.get(key));
                }
            }
        }


    }

    public void edit(String gameName) {
//        System.out.println(gameName + " is being edited!");
    }

    public void rate(String gameName) {
//        System.out.println("Rating for " + gameName + " is being changed!");
    }

    protected Stage makeStage() {
        Stage ret = new Stage();
        ret.setTitle(ST_TITLE);
        ret.setScene(myScene);
        myScene.getStylesheets().add("style.css");
        return ret;
    }
}