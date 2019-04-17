package network_account;

import auth.RunAuth;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class RunAccount extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        // Test identity fxml
        try{
            Parent root = FXMLLoader.load(RunAccount.class.getResource("/network_fxml/identitypane.fxml"));
            primaryStage.setTitle("Identity Pane");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(IOException e){
            System.out.println("Error in using identity fxml");
        }
        // Test login fxml
//        try {
//            Parent root = FXMLLoader.load(RunAccount.class.getResource("/network_fxml/network.fxml"));
//            primaryStage.setTitle("User Login");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.setResizable(false);
//            primaryStage.show();
//        } catch (IOException e) {
//            System.out.println("Network FXML file not found");
//        }
    }
}
