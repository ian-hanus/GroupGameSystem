package network_account;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML controller for the pane that allows users to create their own account. Links with the Google App Engine backend
 * to create and save user information
 */
public class CreateAccountController {
    @FXML
    public TextField emailTextField, usernameTextField, displayNameTextField, passwordField1, passwordField2;
    public Label failLabel;
    public Button createAccountButton;

    /**
     * Creates an account with all of the information requisite to form a user identity if all of the entered
     * information is valid; else clears all TextFields and informs the user that their information was invalid.
     */
    public void createAccount(){
        TextField[] allTextFields = new TextField[]{emailTextField, usernameTextField, displayNameTextField,
                passwordField1, passwordField2};
        for(TextField tf:allTextFields){
            tf.setText("");
        }
        failLabel.setText("Invalid account info");
    }

    /**
     * Attempt to create the account if the user presses enter while in the password confirmation textfield
     * @param e is the key that is pressed
     */
    public void createAccountEnter(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            createAccount();
        }
    }
}
