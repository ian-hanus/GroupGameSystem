package Initial;

import PlayerMain.GameLoop;
import basic.RunAuth;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;

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
        //RunAuth authoringEnvironment = new RunAuth();
        GameLoop player = new GameLoop();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
