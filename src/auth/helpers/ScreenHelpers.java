package auth.helpers;

import auth.Callback;
import auth.RunAuth;
import auth.UIElementWrapper;
import auth.pagination.PaginationUIElement;
import auth.panes.*;
import auth.screens.CanvasScreen;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static auth.Colors.DEFAULT_TEXT_COLOR;
import static auth.Dimensions.*;
import static auth.Styles.*;
import static auth.Strings.*;
import static auth.helpers.DimensionCalculator.*;
import static auth.helpers.RectangleHelpers.createStyledRectangle;

public class ScreenHelpers {
    public static void initScene(CanvasScreen context, Scene scene, Group root) {
        scene.setRoot(root);
        root.setStyle(BG_STYLE);
        placePanes(context);
        placeCanvas(context);
        placeScenePagination(context);
    }

    private static void placeScenePagination(CanvasScreen context) {
        var sceneText = new Text("Scene 1");
        sceneText.setFont(RunAuth.bebasKaiMedium);
        sceneText.setFill(DEFAULT_TEXT_COLOR);
        var pagination = new PaginationUIElement(sceneText, (arg) -> {
            // TODO
            var index = arg[0];
            System.out.println("New index " + index);
        }, SCENE_PAGINATION);
        var pane = placeScenePaginationPane();
        pagination.getView().setLayoutX(12);
        pagination.getView().setLayoutY(9.5);
        pane.getView().getChildren().add(pagination.getView());
        context.registerNewUIElement(pane);
    }

    private static Pane placeScenePaginationPane() {
        return new CentrePane(CONSOLE_HORIZONTAL_OFFSET + CANVAS_WIDTH/2 - SCENE_PAGINATION_WIDTH/2,
                PAGINATION_VERTICAL_OFFSET,
                SCENE_PAGINATION_WIDTH,
                SCENE_PAGINATION_HEIGHT,
                SCENE_PAGINATION_PANE_ID);
    }

    private static void placeCanvas(CanvasScreen context) {
        var canvas = createStyledRectangle(CONSOLE_HORIZONTAL_OFFSET, CANVAS_VERTICAL_OFFSET,
                CANVAS_WIDTH, CANVAS_HEIGHT, CANVAS_STYLE);
        context.registerNewUIElement(new UIElementWrapper(canvas, CANVAS_ID));
    }

    private static void placePanes(CanvasScreen context) {
        var toolsPane = new LeftPane(centreVertical(TOOLS_PANE_HEIGHT), TOOLS_PANE_WIDTH, TOOLS_PANE_HEIGHT, TOOLS_PANE_ID);
        var propsPane = new RightPane(TOP_EDGE, RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT, PROPS_PANE_ID);
        var objLibPane = new RightPane(computeMarginToBottomEdge((Region) propsPane.getView(), RIGHT_PANE_MARGIN), RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT, OBJ_LIB_PANE_ID);

        var rightPanesGroup = new Group(propsPane.getView(), objLibPane.getView());
        rightPanesGroup.setLayoutY(centreVertical(rightPanesGroup.getLayoutBounds().getHeight()));

        var consolePane = new BottomPane(CONSOLE_HORIZONTAL_OFFSET, CONSOLE_PANE_WIDTH, CONSOLE_PANE_HEIGHT);
        context.registerNewUIElement(toolsPane, new UIElementWrapper(rightPanesGroup, RIGHT_PANES_GROUP_ID), consolePane);
    }
}
