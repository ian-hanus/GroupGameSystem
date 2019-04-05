package auth.helpers;

import javafx.scene.Group;
import javafx.scene.Scene;

import static auth.Dimensions.*;
import static auth.Styles.*;
import static auth.helpers.DimensionCalculator.*;
import static auth.helpers.RectangleHelpers.*;

public class ScreenHelpers {
    public static void initScene(Scene scene, Group root) {
        scene.setRoot(root);
        root.setStyle(BG_STYLE);
        placeBackgroundRectangles(root);
    }

    private static void placeBackgroundRectangles(Group root) {
        var toolsPane = createStyledRectangle(LEFT_EDGE, centreVertical(TOOLS_PANE_HEIGHT),
                TOOLS_PANE_WIDTH, TOOLS_PANE_HEIGHT, TOOLS_PANE_STYLE);

        root.getChildren().addAll(toolsPane);
    }
}
