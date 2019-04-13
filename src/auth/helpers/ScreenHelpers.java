package auth.helpers;

import auth.Callback;
import auth.RunAuth;
import auth.UIElementWrapper;
import auth.auth_ui_components.*;
import auth.pagination.PaginationUIElement;
import gamedata.Game;
import gamedata.GameObject;
import gamedata.Resource;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
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
import static gamecenter.Dimensions.*;
import static gamecenter.RunGameCenter.bebasKai;
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
            final int index = (Integer) arg[0];
            context.switchToScene(index);
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

    private static void populateObLibPane(CanvasScreen context, Pane objLibPane) {
        var containerPane = new BorderPane();
        containerPane.setPrefWidth(RIGHT_PANE_WIDTH - RIGHT_PANE_MARGIN/2);
        containerPane.setPrefHeight(RIGHT_PANE_HEIGHT - RIGHT_PANE_MARGIN);
        containerPane.setLayoutX(RIGHT_PANE_MARGIN/4);
        containerPane.setLayoutY(RIGHT_PANE_MARGIN/2);

        String[] titles = {OBJECTS_TITLE, IMG_RES_TITLE, AUD_RES_TITLE, COLORS_TITLE};

        var titleText = new Text(titles[0]);
        titleText.setFont(bebasKai);
        titleText.setFill(DEFAULT_TEXT_COLOR);
        containerPane.setTop(titleText);
        BorderPane.setAlignment(titleText, Pos.CENTER);

        var pagination = new PaginationUIElement(wrapInScrollView(context.getObjectGrid()), (arg) -> {
            final int index = (Integer) arg[0];
            titleText.setText(titles[index]);
        }, SCENE_PAGINATION);

        pagination.addPage(wrapInScrollView(context.getImageGrid())); // for images
        pagination.addPage(wrapInScrollView(context.getAudioGrid())); // for audio
        pagination.addPage(wrapInScrollView(context.getColorGrid())); // for colour palette
        pagination.goToPage(0); // Switch back to objects

        containerPane.setCenter(pagination.getView());

        objLibPane.getView().getChildren().addAll(containerPane);
    }

    private static void repopulatePropertiesPane(CanvasScreen context) {
        System.out.println("Properties for " + context.selectedType + " with ID " + context.selectedID);
    }

    private static ScrollPane wrapInScrollView(VBox v) {
        var sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setMaxHeight(60*3+15*2);
        sp.setContent(v);
        sp.setStyle("-fx-background: #333333;\n" +
                "   -fx-border-color: transparent;" +
                "-fx-background-color: #33333300;");

        return sp;
    }

    public static void initialiseGrids(CanvasScreen context) {
        initialiseObjectsGrid(context);
        initialiseImagesGrid(context);
        initialiseAudioGrid(context);
        initialiseColorGrid(context);

    }

    private static void initialiseColorGrid(CanvasScreen context) {
        if (context.getColorGrid().getChildren().size() != 0) {
            context.getColorGrid().getChildren().clear(); // Remove all the HBox's within this VBox
        }

        var row = new HBox(5);
        for (var r : context.getGame().resources) {
            if (row.getChildren().size() == 3) {
                VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
                context.getColorGrid().getChildren().add(row);
                row = new HBox(5);
            }
            if (r.resourceType == Resource.ResourceType.COLOR_RESOURCE) {
                var icon = new ColorIcon(getColorByID(context.getGame(), r.id), r.id, e -> {
                    // TODO
                    var thisIcon = (Selectable) e[0];
                    if (context.currentlySelected != null && context.currentlySelected != thisIcon) {
                        context.currentlySelected.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                    if (context.currentlySelected != thisIcon) {
                        context.currentlySelected = thisIcon;
                        thisIcon.select();
                        context.selectedID = r.id;
                        context.selectedType = Color.class;
                        repopulatePropertiesPane(context);
                        System.out.println("Color icon clicked for " + r.id);
                    } else {
                        thisIcon.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                });
                row.getChildren().add(icon.getView());
            }
        }
        VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
        context.getColorGrid().getChildren().add(row);
    }

    private static void clearSelection(CanvasScreen context) {
        context.currentlySelected = null;
        context.selectedID = null;
        context.selectedType = null;
    }

    private static void initialiseAudioGrid(CanvasScreen context) {
        if (context.getAudioGrid().getChildren().size() != 0) {
            context.getAudioGrid().getChildren().clear(); // Remove all the HBox's within this VBox
        }

        var row = new HBox(5);
        for (var r : context.getGame().resources) {
            if (row.getChildren().size() == 3) {
                VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
                context.getAudioGrid().getChildren().add(row);
                row = new HBox(5);
            }
            if (r.resourceType == Resource.ResourceType.AUDIO_RESOURCE) {
                var icon = new ToolIcon("audio", r.id, e -> {
                    // TODO
                    var thisIcon = (Selectable) e[0];
                    if (context.currentlySelected != null && context.currentlySelected != thisIcon) {
                        context.currentlySelected.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                    if (context.currentlySelected != thisIcon) {
                        context.currentlySelected = thisIcon;
                        thisIcon.select();
                        context.selectedID = r.id;
                        context.selectedType = AudioClip.class;
                        repopulatePropertiesPane(context);
                        System.out.println("Audio icon clicked for " + r.id);
                    } else {
                        thisIcon.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                }, true);
                row.getChildren().add(icon.getView());
            }
        }
        VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
        context.getAudioGrid().getChildren().add(row);
    }

    private static void initialiseImagesGrid(CanvasScreen context) {
        if (context.getImageGrid().getChildren().size() != 0) {
            context.getImageGrid().getChildren().clear(); // Remove all the HBox's within this VBox
        }

        var row = new HBox(5);
        for (var r : context.getGame().resources) {
            if (row.getChildren().size() == 3) {
                VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
                context.getImageGrid().getChildren().add(row);
                row = new HBox(5);
            }
            if (r.resourceType == Resource.ResourceType.IMAGE_RESOURCE) {
                var icon = new ImageIcon(getImageById(context.getGame(), r.id), r.id, e -> {
                    // TODO
                    var thisIcon = (Selectable) e[0];
                    if (context.currentlySelected != null && context.currentlySelected != thisIcon) {
                        context.currentlySelected.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                    if (context.currentlySelected != thisIcon) {
                        context.currentlySelected = thisIcon;
                        thisIcon.select();
                        context.selectedID = r.id;
                        context.selectedType = Image.class;
                        repopulatePropertiesPane(context);
                        System.out.println("Image icon clicked for " + r.id);
                    } else {
                        thisIcon.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                });
                row.getChildren().add(icon.getView());
            }
        }
        VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
        context.getImageGrid().getChildren().add(row);
    }

    private static void initialiseObjectsGrid(CanvasScreen context) {
        if (context.getObjectGrid().getChildren().size() != 0) {
            context.getObjectGrid().getChildren().clear(); // Remove all the HBox's within this VBox
        }

        var row = new HBox(5);
        for (var o : context.getGame().gameObjects) {
            if (row.getChildren().size() == 3) {
                VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
                context.getObjectGrid().getChildren().add(row);
                row = new HBox(5);
            }
            var callback = new Callback(){
                @Override
                public void onCallback(Object... optionalArgs) {
                    // TODO
                    var thisIcon = (Selectable) optionalArgs[0];
                    if (context.currentlySelected != null && context.currentlySelected != thisIcon) {
                        context.currentlySelected.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                    if (context.currentlySelected != thisIcon) {
                        context.currentlySelected = thisIcon;
                        thisIcon.select();
                        context.selectedID = o.objectID;
                        context.selectedType = GameObject.class;
                        repopulatePropertiesPane(context);
                        System.out.println("Object icon clicked for "+o.objectID);
                    } else {
                        thisIcon.deselect();
                        clearSelection(context);
                        repopulatePropertiesPane(context);
                    }
                }
            };
            if (o.bgImage.isEmpty() || o.bgImage.isBlank()) {
                // No image, use bgColor
                var icon = new ColorIcon(getColorByID(context.getGame(), o.bgColor), o.objectID, callback);
                row.getChildren().add(icon.getView());
            } else {
                var icon = new ImageIcon(getImageById(context.getGame(), o.bgColor), o.objectID, callback);
                row.getChildren().add(icon.getView());
            }
        }
        VBox.setMargin(row, new Insets(0, 0, 0, (30)/2.0));
        context.getObjectGrid().getChildren().add(row);
    }

    private static Image getImageById(Game game, String id) {
        try {
            for (var r : game.resources) {
                if (r.resourceType == Resource.ResourceType.IMAGE_RESOURCE && r.id.equals(id)) {
                    return new Image(new File(r.src).toURI().toURL().toExternalForm());
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static Color getColorByID(Game game, String id) {
        for (var r : game.resources) {
            if (r.resourceType == Resource.ResourceType.COLOR_RESOURCE && r.id.equals(id)) {
                return Color.valueOf(r.src);
            }
        }
        return Color.WHITE;
    }

    private static void populatePropsPane(CanvasScreen context, Pane propsPane) {
        var containerPane = new BorderPane();
        containerPane.setPrefWidth(RIGHT_PANE_WIDTH - RIGHT_PANE_MARGIN/2);
        containerPane.setPrefHeight(RIGHT_PANE_HEIGHT - RIGHT_PANE_MARGIN);
        containerPane.setLayoutX(RIGHT_PANE_MARGIN/4);
        containerPane.setLayoutY(RIGHT_PANE_MARGIN/2);

        var titleText = new Text(SCENE_PROPERTIES_TITLE);
        titleText.setFont(bebasKai);
        titleText.setFill(DEFAULT_TEXT_COLOR);
        containerPane.setTop(titleText);
        BorderPane.setAlignment(titleText, Pos.CENTER);

        repopulatePropertiesPane(context);

        propsPane.getView().getChildren().addAll(containerPane);
    }

    private static void placePanes(CanvasScreen context) {
        var toolsPane = new LeftPane(centreVertical(TOOLS_PANE_HEIGHT), TOOLS_PANE_WIDTH, TOOLS_PANE_HEIGHT, TOOLS_PANE_ID);
        var propsPane = new RightPane(TOP_EDGE, RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT, PROPS_PANE_ID);
        var objLibPane = new RightPane(computeMarginToBottomEdge((Region) propsPane.getView(), RIGHT_PANE_MARGIN), RIGHT_PANE_WIDTH, RIGHT_PANE_HEIGHT, OBJ_LIB_PANE_ID);

        populateToolsPane(context, toolsPane);
        populateObLibPane(context, objLibPane);
        populatePropsPane(context, propsPane);

        var rightPanesGroup = new Group(propsPane.getView(), objLibPane.getView());
        rightPanesGroup.setLayoutY(centreVertical(rightPanesGroup.getLayoutBounds().getHeight()));

        var consolePane = new BottomPane(CONSOLE_HORIZONTAL_OFFSET, CONSOLE_PANE_WIDTH, CONSOLE_PANE_HEIGHT);
        context.registerNewUIElement(toolsPane, new UIElementWrapper(rightPanesGroup, RIGHT_PANES_GROUP_ID), consolePane);
    }
}
