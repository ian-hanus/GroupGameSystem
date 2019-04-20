package Player.src.PlayerMain;

import Engine.src.Components.BasicComponent;
import Engine.src.Components.Component;
import Engine.src.Controller.Controller;
import Player.src.Features.ScrollableWindows.HUD;
import Player.src.Features.SidePanel;
import Player.src.GameStats.DeathTracker;
import Player.src.GameStats.EnemyTracker;
import Player.src.GameStats.PositionTracker;
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
    private final double HUD_WIDTH = 200;

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
    private Integer myIntObjectX;  //FIXME remove
    private Integer myIntObjectY; //FIXME remove

    private Controller myGameController;
    private Group myGameRoot;
    private Map<Integer, Map<Class<? extends Component>, Component>> myGameEntityMap;
    private Map<Integer, ImageView> myImageViewMap;

    private PositionTracker myXPosTracker;
    private PositionTracker myYPosTracker;
    private DeathTracker myDeathTracker;
    private EnemyTracker myEnemyTracker;

    private double startTime;
    private double currentTime;

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
        myXPosTracker = new PositionTracker();
        myYPosTracker = new PositionTracker();
        myDeathTracker = new DeathTracker();
        myEnemyTracker = new EnemyTracker();
        myGameController = new Controller(STEP_TIME, myScene.getWidth(), myScene.getHeight(), GAME_WIDTH / 3.0, GAME_HEIGHT);
        myGameEntityMap = myGameController.getEntities();

        addNewImageViews();

        Scene gameScene = new Scene(myBorderPane, GAME_BG);
        //gameScene.getStylesheets().add("style.css");
        gameStage.setScene(gameScene);
        gameStage.show();

        gameScene.setOnKeyPressed(e -> myGameController.processKey(e.getCode().toString()));

        addHud();

        animate();
    }

    private void addHud() {
        myIntObjectX = 0;
        myIntObjectY = 0;
        myHud = new HUD(HUD_WIDTH, ST_HEIGHT, getHUDNames(), getHUDValues());
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
        myIntObjectX += 1;
        myIntObjectY -= 1;
        myHud.update(getHUDValues());
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
        if (basicComponent == null)
            return;

        if (id == 0) {
            storeHeroData(basicComponent);
        }

        ImageView imageView = myImageViewMap.get(id);
        moveAndResize(imageView, basicComponent);
        setImageIfNecessary(imageView, basicComponent);
    }

    private void storeHeroData(BasicComponent basicComponent) {
        myXPosTracker.storeData(currentTime, basicComponent.getX());
        myYPosTracker.storeData(currentTime, basicComponent.getY());
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

    //FIXME add legit HUD values
    private String[] getHUDNames() {
        return new String[] {"X", "Y"};
    }

    private Object[] getHUDValues() {
        var userBasic = (BasicComponent) myGameEntityMap.get(0).get(BasicComponent.class);
        return new Object[] {userBasic.getX(), userBasic.getY()};
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