package auth.helpers;

import auth.PropertyChangers.BlockProperties;
import auth.PropertyChangers.ObjectProperties;
import auth.panes.BottomPane;
import auth.panes.LeftPane;
import auth.panes.RightPane;
import javafx.scene.Group;
import javafx.scene.Scene;

import static auth.Dimensions.*;
import static auth.Styles.*;
import static auth.helpers.DimensionCalculator.*;
import static auth.helpers.RectangleHelpers.createStyledRectangle;

public class ScreenHelpers {
    public static void initScene(Scene scene, Group root) {
        scene.setRoot(root);
        root.setStyle(BG_STYLE);
        placePanes(root);
        placeCanvas(root);
    }

    private static void placeCanvas(Group root) {
        var canvas = createStyledRectangle(CONSOLE_HORIZONTAL_OFFSET, CANVAS_VERTICAL_OFFSET,
                CANVAS_WIDTH, CANVAS_HEIGHT, CANVAS_STYLE);
        root.getChildren().add(canvas);
    }

    private static void placePanes(Group root) {
        var toolsPane = new LeftPane(centreVertical(TOOLS_PANE_HEIGHT), TOOLS_PANE_WIDTH, TOOLS_PANE_HEIGHT);
        var propsPane = new RightPane(TOP_EDGE, RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT);
        var objLibPane = new RightPane(computeMarginToBottomEdge(propsPane.getView(), RIGHT_PANE_MARGIN), RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT);

        var objProperties = new BlockProperties("Test Block");
//        propsPane.getView().getChildren().add(objProperties.getVisualization());

        var rightPanesGroup = new Group(propsPane.getView(), objLibPane.getView());
        rightPanesGroup.setLayoutY(centreVertical(rightPanesGroup.getLayoutBounds().getHeight()));

        var consolePane = new BottomPane(CONSOLE_HORIZONTAL_OFFSET, CONSOLE_PANE_WIDTH, CONSOLE_PANE_HEIGHT);
        root.getChildren().addAll(toolsPane.getView(),
                rightPanesGroup,
                consolePane.getView());
    }
}
