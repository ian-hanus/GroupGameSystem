package helpers;

import ObjectControl.ObjectManager;
import panes.BottomPane;
import panes.LeftPane;
import panes.RightPane;
import javafx.scene.Group;
import javafx.scene.Scene;

import static basic.Dimensions.*;
import static basic.Styles.*;
import static helpers.DimensionCalculator.*;
import static helpers.RectangleHelpers.createStyledRectangle;

public class ScreenHelpers {
    private static final String STYLE_SHEET = "authoring.css";

    public static void initScene(Scene scene, Group root) {
        scene.setRoot(root);
        root.setStyle(BG_STYLE);
        placePanes(root);
        placeCanvas(root);
        scene.getStylesheets().add(STYLE_SHEET);
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

        var objectManager = new ObjectManager(objLibPane.getView());
        propsPane.getView().getChildren().add(objectManager.getCreatorView());
        objLibPane.getView().getChildren().add(objectManager.getBankView());

        var rightPanesGroup = new Group(propsPane.getView(), objLibPane.getView());
        rightPanesGroup.setLayoutY(centreVertical(rightPanesGroup.getLayoutBounds().getHeight()));

        var consolePane = new BottomPane(CONSOLE_HORIZONTAL_OFFSET, CONSOLE_PANE_WIDTH, CONSOLE_PANE_HEIGHT);
        root.getChildren().addAll(toolsPane.getView(),
                rightPanesGroup,
                consolePane.getView());
    }
}
