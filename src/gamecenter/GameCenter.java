package gamecenter;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class GameCenter extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GameCenter.fxml"));

        Scene scene = new Scene(root, 300, 300);

        stage.setTitle("Game Center");
        stage.setScene(scene);
        stage.show();
    }

    public void main(String[] args) {
        launch(args);
    }
}
