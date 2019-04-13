package auth.screens;

import auth.RunAuth;
import auth.UIElement;
import auth.helpers.DataHelpers;
import auth.pagination.PaginationUIElement;
import gamedata.Game;
import gamedata.Resource;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static auth.Colors.*;
import static auth.Dimensions.*;
import static auth.Strings.*;
import static auth.helpers.DataHelpers.createNewScene;
import static auth.helpers.ScreenHelpers.*;

public class CanvasScreen extends Screen {
    private RunAuth context;
    private Group container;
    private Stage stage;
    private Game game;

    public VBox getObjectGrid() {
        return objectGrid;
    }

    private VBox objectGrid;

    public VBox getImageGrid() {
        return imageGrid;
    }

    private VBox imageGrid;

    public VBox getAudioGrid() {
        return audioGrid;
    }

    private VBox audioGrid;

    public VBox getColorGrid() {
        return colorGrid;
    }

    private VBox colorGrid;

    private int currentScene = 0;

    public PaginationUIElement getPagination() {
        return pagination;
    }

    public void setPagination(PaginationUIElement pagination) {
        this.pagination = pagination;
    }

    private PaginationUIElement pagination;

    private List<UIElement> possessedElements;

    public CanvasScreen() {
        possessedElements = new ArrayList<>();
        game = new Game();
        game.scenes.add(DataHelpers.createNewScene());
        objectGrid = new VBox(5);
        imageGrid = new VBox(5);
        audioGrid = new VBox(5);
        colorGrid = new VBox(5);
        initialiseGrids(this);
    }

    public int createNewScene() {
        game.scenes.add(DataHelpers.createNewScene());
        return game.scenes.size();
    }

    public Game getGame() {
        return game;
    }

    public int getResourcesCount(Resource.ResourceType type) {
        int count = 0;
        for (Resource r : game.resources) {
            if (r.resourceType == type)
                count ++;
        }
        return count;
    }

    public void switchToScene(int index) {
        currentScene = index;
        // TODO: loadScene(index);
        System.out.println("Current scene is "+currentScene);
    }

    public void registerNewUIElement(UIElement... elements) {
        for (UIElement element : elements) {
            this.possessedElements.add(element);
            this.container.getChildren().add(element.getView());
        }
    }

    public void removeUIElement(UIElement... elements) {
        for (UIElement element : elements) {
            this.container.getChildren().remove(element.getView());
            this.possessedElements.remove(element);
        }
    }

    /**
     * Method to create create a new stage
     * @param stage Parent stage
     * @param context Reference to the parent object
     * @return a stage representing the main stage
     */
    public Stage createScreen(Stage stage, RunAuth context) {
        var root = new Group();
        container = new Group();
        this.stage = stage;
        root.getChildren().add(container);

        var scene = new Scene(root, ENV_WINDOW_WIDTH, ENV_WINDOW_HEIGHT, BG_COLOR);
        initScene(this, scene, root);

        this.context = context;
        stage.setScene(scene);
        stage.setTitle(DEFAULT_TITLE);
        return stage;
    }
}
