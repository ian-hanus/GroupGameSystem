package network_account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class RunAccount extends Application {
    public static void main(String[] args){
        launch(args);
    }
    public static Font sofiaPro, sofiaProSmall, bebasKai, bebasKaiMedium;
    @Override
    public void start(Stage primaryStage){
        // Test create account fxml
//        try{
//            Parent root = FXMLLoader.load(RunAccount.class.getResource("/network_fxml/createaccount.fxml"));
//            primaryStage.setTitle("Create New Account");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.setResizable(false);
//            primaryStage.show();
//        } catch(IOException e){
//            System.out.println("Error in using create account fxml");
//        }

//        Test identity fxml
//        try{
//            Parent root = FXMLLoader.load(RunAccount.class.getResource("/network_fxml/identitypane.fxml"));
//            primaryStage.setTitle("IdentityManager Pane");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.setResizable(false);
//            primaryStage.show();
//        } catch(IOException e){
//            System.out.println("Error in using identity fxml");
//        }

////      Test login fxml
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(RunAccount.class.getResource("/network_fxml/login.fxml"));
//            Parent root = loader.load();
////            Parent root = FXMLLoader.load(RunAccount.class.getResource("/network_fxml/login.fxml"));
//            primaryStage.setTitle("User Login");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.setResizable(false);
//            primaryStage.show();
//        } catch (IOException e) {
//            System.out.println("Network FXML file not found");
//        }

        IdentityManager identityManager = new IdentityManager();
        identityManager.setStageLogin();
        UserIdentity testIdentity = identityManager.getIdentity();
        System.out.println(testIdentity.getName());

        String[] gameNames = new String[]{"NotMario", "Overcooked", "Sonic"};
        Integer[] highScores = new Integer[]{100, 200, 300};
        String[] userNames = new String[]{"Me", "Myself", "I"};
        for(int k = 0; k < 3; k++){
            String highScore = "http://tmtp-spec.appspot.com/newHighScore?username=" + userNames[k] + "&gameID="
                    + gameNames[k] + "&score=" + highScores[k];
            try {
                URL url = new URL(highScore);
                URLConnection request = url.openConnection();
                request.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
