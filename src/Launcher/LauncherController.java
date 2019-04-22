package Launcher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LauncherController {
    public Button createAccountButton;
    public Button forgotPasswordButton;
    public Button goBackButton;
    public PasswordField confirmPassword;
    public TextField email;

    @FXML
    private void createAccount() {
        creatingAccount(true);
    }

    @FXML
    private void goBack() {
        creatingAccount(false);
    }

    private void creatingAccount(boolean bool) {
        forgotPasswordButton.setVisible(!bool);
        createAccountButton.setVisible(!bool);
        goBackButton.setVisible(bool);
        confirmPassword.setVisible(bool);
        email.setVisible(bool);
    }
}
