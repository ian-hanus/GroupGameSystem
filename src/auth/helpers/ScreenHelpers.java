package auth.helpers;

import auth.RunAuth;
import auth.UIElementWrapper;
import auth.auth_ui_components.ToolIcon;
import auth.pagination.PaginationUIElement;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import org.json.JSONArray;
import uiutils.panes.*;
import auth.screens.CanvasScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import uiutils.panes.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static auth.Colors.DEFAULT_TEXT_COLOR;
import static auth.Dimensions.*;
import static auth.Styles.*;
import static auth.Strings.*;
import static auth.auth_ui_components.ToolIcon.BG_CIRCLE_RADIUS;
import static auth.helpers.DimensionCalculator.*;
import static auth.helpers.RectangleHelpers.createStyledRectangle;
import static gamecenter.RunGameCenter.bebasKaiMedium;

public class ScreenHelpers {
    private static final String STYLE_SHEET = "authoring.css";
    public static void initScene(CanvasScreen context, Scene scene, Group root) {
        scene.setRoot(root);
        root.setStyle(BG_STYLE);
        placePanes(context);
        placeCanvas(context);
        placeScenePagination(context);
        scene.getStylesheets().add(STYLE_SHEET);
    }

    private static void placeScenePagination(CanvasScreen context) {
        var sceneText = new Text("Scene 1");
        sceneText.setFont(bebasKaiMedium);
        sceneText.setFill(DEFAULT_TEXT_COLOR);
        var pagination = new PaginationUIElement(sceneText, (arg) -> {
            // TODO call context.setScene(index);
            var index = arg[0];
        }, SCENE_PAGINATION);
        var pane = placeScenePaginationPane();
        var borderPane = new BorderPane();
        borderPane.setCenter(pagination.getView());
        borderPane.setMinWidth(CONSOLE_PANE_WIDTH - 24);
        borderPane.setMaxWidth(CONSOLE_PANE_WIDTH - 24);
        borderPane.setLayoutY(7);
        borderPane.setLayoutX(12);
        pane.getView().getChildren().add(borderPane);
        context.registerNewUIElement(pane);
        context.setPagination(pagination);
    }

    private static Pane placeScenePaginationPane() {
        return new TopPane(CONSOLE_HORIZONTAL_OFFSET,
                CONSOLE_PANE_WIDTH,
                SCENE_PAGINATION_HEIGHT,
                SCENE_PAGINATION_PANE_ID);
    }

    private static void placeCanvas(CanvasScreen context) {
        var canvas = createStyledRectangle(CONSOLE_HORIZONTAL_OFFSET, CANVAS_VERTICAL_OFFSET,
                CANVAS_WIDTH, CANVAS_HEIGHT, CANVAS_STYLE);
        context.registerNewUIElement(new UIElementWrapper(canvas, CANVAS_ID));
    }

    private static void populateToolsPane(CanvasScreen context, LeftPane toolsPane) {
        var vboxParent = new VBox(5);

        try {
            JSONArray tools = new JSONArray(new Scanner(new File(TOOLS_CONFIG_FILE_PATH)).useDelimiter("\\Z").next());
            for (int i = 0; i < tools.length(); i++) {
                var tool = tools.getJSONObject(i);
                var name = tool.getString("name");
                var tooltip = tool.getString("tooltip");
                var handler = tool.getString("handler");

                vboxParent.getChildren().add(new ToolIcon(name, tooltip, callback -> {
                    try {
                        var method = ToolClickHandlers.class.getMethod(handler, CanvasScreen.class);
                        method.invoke(null, context);
                    } catch (Exception e) {
                        // Would never happen
                    }
                }).getView());
            }

            vboxParent.setLayoutX(TOOLS_PANE_WIDTH/2 - BG_CIRCLE_RADIUS - 5);
            vboxParent.setLayoutY(TOOLS_PANE_HEIGHT/2 - (tools.length()*2*BG_CIRCLE_RADIUS + (tools.length()-1)*15)/2);

            toolsPane.getView().getChildren().add(vboxParent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void placePanes(CanvasScreen context) {
        var toolsPane = new LeftPane(centreVertical(TOOLS_PANE_HEIGHT), TOOLS_PANE_WIDTH, TOOLS_PANE_HEIGHT, TOOLS_PANE_ID);
        var propsPane = new RightPane(TOP_EDGE, RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT, PROPS_PANE_ID);
        var objLibPane = new RightPane(computeMarginToBottomEdge((Region) propsPane.getView(), RIGHT_PANE_MARGIN), RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT, OBJ_LIB_PANE_ID);

        populateToolsPane(context, toolsPane);

        var rightPanesGroup = new Group(propsPane.getView(), objLibPane.getView());
        rightPanesGroup.setLayoutY(centreVertical(rightPanesGroup.getLayoutBounds().getHeight()));

        var consolePane = new BottomPane(CONSOLE_HORIZONTAL_OFFSET, CONSOLE_PANE_WIDTH, CONSOLE_PANE_HEIGHT);
        context.registerNewUIElement(toolsPane, new UIElementWrapper(rightPanesGroup, RIGHT_PANES_GROUP_ID), consolePane);
    }
}
