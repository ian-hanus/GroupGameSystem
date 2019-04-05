package auth.helpers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class ScreenHelpers {
    public static void initScene(Scene scene, Group root) {
        root.getChildren().add(new Text("yolo"));
    }
}
