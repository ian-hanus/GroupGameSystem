package network_account;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;

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
        try {
            String webRequest = "http://black-abode-xxxx.appspot.com/login?username=" + usernameTextField.getText() +
                    "&name=" + displayNameTextField.getText() + "&password=" + passwordField1.getText() + "&email=" +
                    emailTextField.getText();
            URL url = new URL(webRequest);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jParser = new JsonParser();
            JsonElement jElement = jParser.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jObject = jElement.getAsJsonObject();
            System.out.println(jObject);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
