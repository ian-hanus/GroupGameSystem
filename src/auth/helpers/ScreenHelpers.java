package auth.helpers;

import javafx.scene.Group;
import javafx.scene.Scene;

import static auth.Styles.*;

public class ScreenHelpers {
    public static void initScene(Scene scene, Group root) {
        scene.setRoot(root);
        root.setStyle(BG_STYLE);
    }
}
