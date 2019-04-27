package network_account;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class RunAccount extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){

//        String[] gameNames = new String[]{"mygame1", "mygame1", "mygame1"};
//        Integer[] highScores = new Integer[]{100, 200, 300};
//        String[] userNames = new String[]{"ianhanus", "ianhanus", "ianhanus"};
//        for(int k = 0; k < 3; k++){
//            String highScore = "http://tmtp-spec.appspot.com/newHighScore?username=" + userNames[k] + "&gameID="
//                    + gameNames[k] + "&score=" + highScores[k];
//
//        }


        IdentityManager identityManager = new IdentityManager();
        identityManager.setStageLogin();
        UserIdentity testIdentity = identityManager.getIdentity();
        System.out.println(testIdentity.getName());
    }
}
