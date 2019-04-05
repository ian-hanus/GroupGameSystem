package auth.screens;

import auth.RunAuth;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static auth.Dimensions.*;
import static auth.helpers.ScreenHelpers.initScene;

public class CanvasScreen extends Screen {
    private RunAuth context;
    private Group container;
    private Stage stage;

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
        initScene(scene, root);

        this.context = context;
        stage.setResizable(false);

        return stage;
    }
}
