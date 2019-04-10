package Initial;

import PlayerMain.GameLoop;
import basic.RunAuth;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;

public class Launcher extends Application {
    private Stage myLauncherStage;
    private Stage myPlayerStage;
    private Stage myAuthoringStage;

    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Start the application by setting up the front end and back end through setUpScene, creating the
     * FrontendController and BackendController
     * @param stage stage
     */
    public void start (Stage stage) {
        initializeLauncherStage(stage);
        initializePlayerStage();
        initializeAuthoringStage();

        display(myPlayerStage); //FIXME display myLauncherStage and add buttons to display auth or player
    }

    private void initializeLauncherStage(Stage stage) {
        myLauncherStage = stage;
        var root = new BorderPane();
        Scene scene = new Scene(root);
        myLauncherStage.setScene(scene);
        //TODO implement actual launcher display
    }

    private void initializePlayerStage() {
        GameLoop player = new GameLoop();
        myPlayerStage = player.getStage();
        System.out.println(myPlayerStage.getScene().getStylesheets().add("player_style.css"));
    }

    private void initializeAuthoringStage() {
        RunAuth authoringEnvironment = new RunAuth();
        //myAuthoringStage =  //TODO get stage from authoring!!!!
        //myAuthoringStage.getScene().getStylesheets().add("authoring_style.css");
    }

    private void display(Stage stage) {
        stage.show();
    }
}
