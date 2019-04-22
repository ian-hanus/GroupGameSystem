package Player.src.PlayerMain;

import Engine.src.Components.BasicComponent;
import Engine.src.Components.Component;
import Engine.src.Components.HealthComponent;
import Engine.src.Components.MotionComponent;
import Engine.src.Controller.Controller;
import Player.src.GameStats.HUD;
import Player.src.Features.SidePanel;
import Player.src.GameStats.DataTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PlayerStage {
    private final String STYLESHEET = "style.css";
    private final double HUD_WIDTH = 400;

    public final String ST_TITLE = "Cracking Open a Scrolled One with the Boys";
    public final double ST_WIDTH = 800;
    public final double ST_HEIGHT = 600;
    public final Paint ST_COLOR = Color.web("284376");

    public final double STEP_TIME = 5;
    public final double GAME_WIDTH = 1400;
    public final double GAME_HEIGHT = 800;
    public final Paint GAME_BG = Color.BLACK;

    public static final int FRAMES_PER_SECOND = 15;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;
    private GridPane myVisualRoot;
    private BorderPane myBorderPane;
    private SidePanel myLeftPanel;
    private HUD myHud;

    private Controller myGameController;
    private Group myGameRoot;
    private Map<Integer, Map<Class<? extends Component>, Component>> myGameEntityMap;
    private Map<Integer, ImageView> myImageViewMap;

    private DataTracker myXPosTracker;
    private DataTracker myYPosTracker;
    private DataTracker myTimeTracker;
    private DataTracker myYVelocityTracker;
    private DataTracker myHealthTracker;

    private double startTime;
    private double currentTime;
    private int myCount;


    public PlayerStage() {
        myVisualRoot = new GridPane();
        //mySidePanelWidth = ST_WIDTH / 3.0;
        //myLeftPanel = new SidePanel(mySidePanelWidth);
        //myBorderPane = new BorderPane();
        //myBorderPane.setLeft(myLeftPanel.getPane());
        myScene = new Scene(myVisualRoot, ST_WIDTH, ST_HEIGHT, ST_COLOR);
        //myScene = new Scene(myBorderPane, ST_WIDTH, ST_HEIGHT, ST_COLOR);
        myScene.getStylesheets().add(STYLESHEET);
    }

    public void run(String gameName) {
        Stage gameStage = new Stage();
        myLeftPanel = new SidePanel(HUD_WIDTH);
        myBorderPane = new BorderPane();
        myBorderPane.setLeft(myLeftPanel.getPane());
        myGameRoot = new Group();
        myBorderPane.setCenter(myGameRoot);

        myImageViewMap = new HashMap<>(); //FIXME go full screen
        myXPosTracker = new DataTracker("X Position");
        myYPosTracker = new DataTracker("Y Position");
        myTimeTracker = new DataTracker("Time");
        myYVelocityTracker = new DataTracker("Y Velocity");
        myHealthTracker = new DataTracker("Health");
        myGameController = new Controller(STEP_TIME, myScene.getWidth(), myScene.getHeight(), GAME_WIDTH / 3.0, GAME_HEIGHT);
        myGameEntityMap = myGameController.getEntities();

        addNewImageViews();

        Scene gameScene = new Scene(myBorderPane, GAME_BG);
        //gameScene.getStylesheets().add("style.css");
        gameScene.getStylesheets().add("hud.css");
        gameStage.setScene(gameScene);
        gameStage.show();

        gameScene.setOnKeyPressed(e -> myGameController.processKey(e.getCode().toString()));

        addHud();

        animate();
    }

    private void addHud() {
        var plotter = new Plotter(getDataTrackers(), HUD_WIDTH - 10, ST_HEIGHT);
        myHud = new HUD(HUD_WIDTH, ST_HEIGHT, "Level 1", getHUDNames(), plotter);
        myHud.update(getHUDValues());
        myLeftPanel.addRow(myHud.getNode());
    }

    private void animate() {
        startTime = (double)(java.lang.System.currentTimeMillis() / 1000);
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step() {
        currentTime = (double)(java.lang.System.currentTimeMillis() / 1000) - startTime;
        myGameController.updateScene();
        addNewImageViews();
        updateOrRemoveImageViews();

        if (myCount % 10 == 0) {
            myHud.update(getHUDValues());
            storeHeroData();
        }
        myCount++;
    }

    private void updateOrRemoveImageViews() {
        for (int id : myImageViewMap.keySet()) {
            if (!myGameEntityMap.containsKey(id))
                myGameRoot.getChildren().remove(myImageViewMap.get(id));
            updateImageView(id);
        }
    }

    private void addNewImageViews() {
        for (int id : myGameEntityMap.keySet()) {
            if (myImageViewMap.containsKey(id))
                continue;
            var newImageView = new ImageView();
            myImageViewMap.put(id, newImageView);
            myGameRoot.getChildren().add(newImageView);
            updateImageView(id);
        }
    }

    private void updateImageView(int id) {
        BasicComponent basicComponent = (BasicComponent) myGameEntityMap.get(id).get(BasicComponent.class);
        MotionComponent motionComponent = (MotionComponent) myGameEntityMap.get(id).get(MotionComponent.class);
        HealthComponent healthComponent = (HealthComponent) myGameEntityMap.get(id).get(HealthComponent.class);
        if (basicComponent == null)
            return;

        ImageView imageView = myImageViewMap.get(id);
        moveAndResize(imageView, basicComponent);
        setImageIfNecessary(imageView, basicComponent);
    }

    private void storeHeroData() {
        BasicComponent basicComponent = (BasicComponent) myGameEntityMap.get(0).get(BasicComponent.class);
        MotionComponent motionComponent = (MotionComponent) myGameEntityMap.get(0).get(MotionComponent.class);
        myTimeTracker.storeData(currentTime);
        myXPosTracker.storeData(basicComponent.getX());
        myYPosTracker.storeData(basicComponent.getY());
        myYVelocityTracker.storeData(motionComponent.getYVelocity());
    }

    private void moveAndResize(ImageView imageView, BasicComponent basicComponent) {
        imageView.setX(basicComponent.getX() - myGameController.getOffset()[0]);
        imageView.setY(basicComponent.getY() - myGameController.getOffset()[1]);
        imageView.setFitWidth(basicComponent.getWidth());
        imageView.setFitHeight(basicComponent.getHeight());
    }

    private void setImageIfNecessary(ImageView imageView, BasicComponent entity) {
        InputStream newInputStream = this.getClass().getResourceAsStream(entity.getMyFilename());
        if (newInputStream == null)
            return;

        Image newImage = new Image(newInputStream);
        if (!newImage.equals(imageView.getImage()))
            imageView.setImage(newImage);
    }

    private DataTracker[] getDataTrackers() {
        return new DataTracker[] {myXPosTracker, myYPosTracker, myYVelocityTracker, myTimeTracker};
    }

    private String[] getHUDNames() {
        return new String[] {"Lives", "X", "Y", "Powerup"};
    }

    private Object[] getHUDValues() {
        var userBasic = (BasicComponent) myGameEntityMap.get(0).get(BasicComponent.class);
        return new Object[] {3, userBasic.getX(), userBasic.getY(), "Flower"};//FIXME hardcoded
    }

    /**
     *    edit(), rate() currently placeholder. Update these methods.
     */
    public void edit(String gameName) {
//        System.out.println(gameName + " is being edited!");
    }

    public void rate(String gameName) {
//        System.out.println("Rating for " + gameName + " is being changed!");
    }

    public Stage makeStage() {
        Stage ret = new Stage();
        ret.setTitle(ST_TITLE);
        ret.setScene(myScene);
        return ret;
    }
}
