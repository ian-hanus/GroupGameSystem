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

    /**
     * Constructor setting the UserIdentity to a default
     */
    public IdentityManager(){
        myIdentity = new UserIdentity();
        myLoginLoader = new FXMLLoader();
    }

    /**
     * Set the input stage to the login screen
     */
    public void setStageLogin(){
        try {
            myLoginLoader.setLocation(RunAccount.class.getResource("/network_fxml/login.fxml"));
            Parent root = myLoginLoader.load();
            setStage(root);
        } catch (IOException e) {
            System.out.println("Login FXML file not found");
        }
    }

    /**
     * Set the input stage to the create account screen
     */
    public void setStageCreate(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RunAccount.class.getResource("/network_fxml/createaccount.fxml"));
            Parent root = loader.load();
            setStage(root);
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
            myIdentity = loginController.getMyIdentity();
        } catch(NullPointerException e){
            myIdentity = new UserIdentity();
        }
        return myIdentity;
    }

    private void setStage(Parent root){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("User Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.showAndWait();
    }
}
