package Main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.util.Duration;

public class Launcher extends Application {
    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Start the application by setting up the front end and back end through setUpScene, creating the
     * FrontendController and BackendController
     * @param stage stage
     */
    public void start (Stage stage) {
        var root = new BorderPane();
        setUpScene(stage, root);

        stage.show();
    }

    private void setUpScene(Stage stage, BorderPane root) {
//        FrontendController front = new FrontendController(root, stage);
//        BackendController backendController = new BackendController();
//        front.setBackendController(backendController);
//        backendController.setFrontendController(front);
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle(WINDOW_TITLE);
//
//        stage.setResizable(true);
//
//        stage.setMinHeight(WINDOW_MIN_HEIGHT);
//        stage.setMinWidth(WINDOW_MIN_WIDTH);
//
//        stage.setMaxHeight(WINDOW_MAX_HEIGHT);
//        stage.setMaxWidth(WINDOW_MAX_WIDTH);
//
//        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(front));
//        var animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();
//
//        scene.getStylesheets().add("ControlStyle.css");
    }
}
