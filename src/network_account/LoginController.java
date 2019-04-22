package network_account;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

//TODO: Reformat Login class to reflect only users top score, along with universal top scores

/**
 * FXML Controller for the user login pane (res/network_fxml/loginpane.fxml), allowing user's to connect with the
 * Google App Engine backend to login and load information such as display name and personal high scores
 */
public class LoginController {
    @FXML
    public TextField usernameTextField, passwordTextField;
    public Label loginFailLabel;
    public Button loginButton;

    /**
     * Check to see if login information is valid: if it is not tell the user that the information they provided was
     * invalid and prompt again
     */
    public void login(){
        loginFailLabel.setText("Login Information Invalid");
        usernameTextField.setText("");
        passwordTextField.setText("");
    }

    /**
     * Attempt to login if the user presses enter while their cursor is in the password textfield
     * @param e is the key that they press, which will result in inaction unless the key pressed is enter
     */
    public void loginEnter(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            login();
        }
    }
}
