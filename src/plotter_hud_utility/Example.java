package plotter_hud_utility;

import Player.src.PlayerMain.Plotter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import plotter_hud_utility.plotting.DataTracker;

/**
 * An example application that runs a game using a game loop and:
 *  - includes a HUD in the display
 *  - updates the HUD in the game loop every so often
 *
 * HUD can either include a plot of data that's being tracked,
 * or can place that plot outside of the hud on the opposite side of the screen.
 *
 * @author Hunter Gregory
 */
public class Example extends Application {
    private static final String STYLESHEET = "plotter-hud.css";
    private static final Paint BACKGROUND = Color.BLACK;
    private static final double HUD_WIDTH = 400;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;

    private static final int FRAMES_PER_SECOND = 15;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int HUD_UPDATE_DELAY = 10;


    private BorderPane myBorderPane;
    private HUD myHud;
    //add private instance variable of engine
    private Pane myGameRoot;

    private Plotter myPlotter;
    private DataTracker myXPosTracker;
    private DataTracker myYPosTracker;
    private DataTracker myTimeTracker;
    //etc...
    private int myGameLoopCount;
    private boolean myHudIncludesPlotter;

    /**
     * Create an Example application
     * @param hudIncludesPlotter
     */
    public Example(boolean hudIncludesPlotter) {
        myHudIncludesPlotter = hudIncludesPlotter;
    }

    @Override
    public void start(Stage stage) {
        //initialize game engine

        initDataTrackers();
        myPlotter = new Plotter(getDataTrackers(), HUD_WIDTH - 10, SCREEN_HEIGHT);
        initBorderPane();

        //add all image views to myGameRoot

        Scene gameScene = new Scene(myBorderPane, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        gameScene.getStylesheets().add(STYLESHEET);
        stage.setScene(gameScene);
        stage.show();

        gameScene.setOnKeyPressed(e -> {}); //add key events if necessary

        animate();
    }

    private void setHud() {
        if (myHudIncludesPlotter)
            myHud = new HUD(HUD_WIDTH, SCREEN_HEIGHT, getHudTitle(), getHUDNames(), myPlotter);
        else
            myHud = new HUD(HUD_WIDTH, SCREEN_HEIGHT, getHudTitle(), getHUDNames());
        myHud.update(getHUDValues());
    }

    private void initBorderPane() {
        myBorderPane = new BorderPane();
        myGameRoot = new Pane();
        myBorderPane.setCenter(myGameRoot);
        setHud();
        myBorderPane.setLeft(myHud.getNode());
        if (!myHudIncludesPlotter)
            myBorderPane.setRight(myPlotter.getNode());
    }

    private void animate() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step() {
        //iterate once through the game loop

        if (myGameLoopCount % HUD_UPDATE_DELAY == 0) {
            updateDataTrackers();
            myHud.update(getHUDValues());
            if (!myHudIncludesPlotter)
                myPlotter.updateGraph();
        }
        myGameLoopCount++;
    }

    //TODO include your own trackers
    private void initDataTrackers() {
        myXPosTracker = new DataTracker("X Position");
        myYPosTracker = new DataTracker("Y Position");
        myTimeTracker = new DataTracker("Time");
    }

    //TODO include your own trackers and update data trackers with real values
    private void updateDataTrackers() {
        myTimeTracker.storeData(myGameLoopCount); //TODO convert to actual time with millisecond delay/fps
        myXPosTracker.storeData(getXPosition()); //TODO include your own data
        myYPosTracker.storeData(getYPosition()); //TODO include your own data
        //...store other data
    }

    //TODO include your own trackers
    private DataTracker[] getDataTrackers() {
        return new DataTracker[] {myXPosTracker, myYPosTracker, myTimeTracker};
    }

    //TODO include your own HUD data names and associated values
    private String[] getHUDNames() {
        return new String[] {"Lives", "X", "Y", "Powerup"};
    }

    //TODO include your own HUD data names and associated values
    private Object[] getHUDValues() {
        return new Object[] {getNumLives(), getXPosition(), getYPosition(), getCurrentPowerup()};
    }

    //low level, example data getters
    private String getHudTitle() { return "Level " + getCurrentLevelNumber(); }
    private int getCurrentLevelNumber() { return 1; }
    private double getXPosition() {return 0;}
    private double getYPosition() {return 0;}
    private int getNumLives() { return 3; }
    private String getCurrentPowerup() { return "Lasers"; }
}
