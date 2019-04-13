package auth.helpers;

import auth.screens.CanvasScreen;
import javafx.scene.text.Text;

import java.util.Random;

import static auth.Colors.DEFAULT_TEXT_COLOR;
import static gamecenter.RunGameCenter.bebasKaiMedium;

/**
 * Author: Anshu Dwibhashi
 * All methods in this class will be of the name as defined in tools.json in the data folder. Since all methods in this class will be invoked
 * by reflection, it is critical to follow this convention.
 */
public class ToolClickHandlers {
    public static void handleNewScene (CanvasScreen context) {
        var sceneText = new Text("Scene " + new Random().nextInt());
        sceneText.setFont(bebasKaiMedium);
        sceneText.setFill(DEFAULT_TEXT_COLOR);
        context.getPagination().addPage(sceneText);
        System.out.println("In handle new scene");
    }

    public static void handleNewObject () {
        System.out.println("In handle new object");
    }
}
