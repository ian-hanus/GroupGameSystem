package auth.auth_fxml_controllers;

import auth.screens.CanvasScreen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ScenePropsController {
    private CanvasScreen context;

    public void initData(Pane propsPane, CanvasScreen context) {
        propsPane.requestFocus();
        this.context = context;
    }
}
