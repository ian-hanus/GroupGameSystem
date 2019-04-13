package auth.helpers;

import auth.screens.CanvasScreen;
import gamedata.Resource;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

import static auth.Colors.DEFAULT_TEXT_COLOR;
import static auth.helpers.ScreenHelpers.initialiseGrids;
import static gamecenter.RunGameCenter.bebasKaiMedium;
import static javafx.application.Application.setUserAgentStylesheet;

/**
 * Author: Anshu Dwibhashi
 * All methods in this class will be of the name as defined in tools.json in the data folder. Since all methods in this class will be invoked
 * by reflection, it is critical to follow this convention.
 */
public class ToolClickHandlers {
    public static void handleNewScene (CanvasScreen context) {
        int newSceneCount = context.createNewScene();
        var sceneText = new Text("Scene " + newSceneCount);
        sceneText.setFont(bebasKaiMedium);
        sceneText.setFill(DEFAULT_TEXT_COLOR);
        context.getPagination().addPage(sceneText);
    }

    public static void handleNewObject (CanvasScreen context) {
        System.out.println("In handle new object");
    }

    public static void handleNewColor(CanvasScreen context) {
        final var stage = new Stage();

        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            stage.close();
            Color c = colorPicker.getValue();
            var r = new Resource();
            r.id = "color_"+(context.getResourcesCount(Resource.ResourceType.COLOR_RESOURCE)+1);
            r.src = c.toString();
            context.getGame().resources.add(r);
            initialiseGrids(context);
        });
        var scene = new Scene(colorPicker);
        stage.setScene(scene);
        stage.show();
    }
}
