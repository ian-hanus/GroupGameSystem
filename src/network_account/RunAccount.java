package network_account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class RunAccount extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(RunAccount.class.getResource("network_fxml/network.fxml"));
        } catch (IOException e) {
            System.out.println("Network FXML file not found");
        }
    }
}
