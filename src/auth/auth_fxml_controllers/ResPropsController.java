package auth.auth_fxml_controllers;

import auth.screens.CanvasScreen;
import gamedata.Game;
import gamedata.Resource;
import gamedata.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import static auth.helpers.DataHelpers.*;

public class ResPropsController extends JXMLController{
    private Game game;
    private String selectedID;
    private Class selectedType;

    @FXML
    public TextField resourceIDField, srcField;

    @Override
    public void initData(Pane propsPane, CanvasScreen context) {
        super.initData(propsPane, context);
        game = context.getGame();
        selectedID = context.selectedID;
        selectedType = context.selectedType;

        if (selectedType == Image.class) {
            populateFormUsingResourceInfo(getResourceByType(game, selectedID, Resource.ResourceType.IMAGE_RESOURCE));
        } else if (selectedType == AudioClip.class) {
            populateFormUsingResourceInfo(getResourceByType(game, selectedID, Resource.ResourceType.AUDIO_RESOURCE));
        } else if (selectedType == Color.class) {
            populateFormUsingResourceInfo(getResourceByType(game, selectedID, Resource.ResourceType.COLOR_RESOURCE));
        }
    }

    private void populateFormUsingResourceInfo(Resource resource) {
        resourceIDField.setText(resource.resourceID);
        if (resource.src.startsWith("0x")) {
            // It's a colour, show hex code
            srcField.setText("#"+resource.src.substring(resource.src.indexOf("0x")+2));
        } else {
            if (resource.src.contains("/")) {
                srcField.setText(resource.src.substring(resource.src.lastIndexOf("/")+1));
            } else {
                srcField.setText(resource.src);
            }
        }
    }
}
