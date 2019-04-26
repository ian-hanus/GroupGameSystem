package network_account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class made to manage the identity of the user and hold all of the information of the player
 */
public class IdentityManager {
    private UserIdentity myIdentity;
    private FXMLLoader myLoginLoader;

    public IdentityManager(){
        myIdentity = new UserIdentity();
    }

    public void setStageLogin(Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RunAccount.class.getResource("/network_fxml/login.fxml"));
            Parent root = loader.load();
            setStage(primaryStage, root);
        } catch (IOException e) {
            System.out.println("Login FXML file not found");
        }
    }

    public void setStageCreate(Stage primaryStage){
        try {
            myLoginLoader = new FXMLLoader();
            myLoginLoader.setLocation(RunAccount.class.getResource("/network_fxml/createaccount.fxml"));
            Parent root = myLoginLoader.load();
            setStage(primaryStage, root);
        } catch (IOException e) {
            System.out.println("Create Account FXML file not found");
        }
    }

    /**
     * Return the identity loaded by the login loader, if applicable
     * @return the UserIdentity: may still be default identity
     */
    public UserIdentity getIdentity(){
        try {
            LoginController loginController = myLoginLoader.getController();
            return loginController.getMyIdentity();
        } catch(NullPointerException e){
            UserIdentity userIdentity = new UserIdentity();
            return userIdentity;
        }
    }

    private void setStage(Stage primaryStage, Parent root){
            primaryStage.setTitle("User Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
    }
}
