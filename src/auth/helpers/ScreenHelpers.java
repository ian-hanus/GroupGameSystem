package auth.helpers;

import auth.panes.LeftPane;
import auth.panes.RightPane;
import javafx.scene.Group;
import javafx.scene.Scene;

import static auth.Dimensions.*;
import static auth.Styles.*;
import static auth.helpers.DimensionCalculator.*;

public class ScreenHelpers {
    public static void initScene(Scene scene, Group root) {
        scene.setRoot(root);
        root.setStyle(BG_STYLE);
        placePanes(root);
    }

    private static void placePanes(Group root) {
        var toolsPane = new LeftPane(centreVertical(TOOLS_PANE_HEIGHT), TOOLS_PANE_WIDTH, TOOLS_PANE_HEIGHT);
        var propsPane = new RightPane(TOP_EDGE, RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT);
        var objLibPane = new RightPane(computeMarginToBottomEdge(propsPane.getView(), RIGHT_PANE_MARGIN), RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT);

        var rightPanesGroup = new Group(propsPane.getView(), objLibPane.getView());
        rightPanesGroup.setLayoutY(centreVertical(rightPanesGroup.getLayoutBounds().getHeight()));

        //var consolePane = new ConsolePane();
        //var canvasPane = new CanvasPane();
        root.getChildren().addAll(toolsPane.getView(),
                rightPanesGroup);
                //consolePane.getView(),
                //canvasPane.getView());
    }

}
