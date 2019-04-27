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
        IdentityManager identityManager = new IdentityManager();
        identityManager.setStageLogin();
        System.out.println(identityManager.getIdentity().getName());
        identityManager.setStageIdentity("mygame1");
        System.out.println("Past identity stage");
    }
}
