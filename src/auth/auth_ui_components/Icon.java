package auth.auth_ui_components;

import auth.Callback;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Icon {
    private Group view;
    private Circle bgCircle;
    private ImageView icon;

    public static final double BG_CIRCLE_RADIUS = 25;
    public static final double ICON_SIZE = 30;
    private static final Color BG_COLOUR = Color.WHITE;

    public Icon(Image img, String tooltipText, Callback onClickCallback) {
        view = new Group();
        makeBgCircle();
        addBgImage(img);
        addTooltip(tooltipText);
        setOnClickListener(onClickCallback);
    }

    public Icon(Color color, String tooltipText, Callback onClickCallback) {
        view = new Group();
        makeBgCircle();
        setBgCircleColor(color);
        addTooltip(tooltipText);
        setOnClickListener(onClickCallback);
    }

    public Icon(String iconID, String tooltipText, Callback onClickCallback) {
        view = new Group();
        makeBgCircle();
        addImgIcon(iconID);
        addTooltip(tooltipText);
        setOnClickListener(onClickCallback);
    }

    private void setBgCircleColor(Color color) {
        bgCircle.setFill(color);
    }

    private void addBgImage(Image img) {
        var imgView = new ImageView(img);
        imgView.setFitWidth(2*BG_CIRCLE_RADIUS);
        imgView.setFitHeight(2*BG_CIRCLE_RADIUS);
        imgView.setClip(bgCircle);
        view.getChildren().add(imgView);
    }

    public Group getView() {
        return view;
    }

    private void addTooltip(String tooltipText) {
        Tooltip t = new Tooltip(tooltipText);
        Tooltip.install(view, t);
    }

    private void setOnClickListener(Callback callback) {
        view.setOnMouseClicked(e -> callback.onCallback(null));
    }

    private void makeBgCircle() {
        bgCircle = new Circle();
        bgCircle.setRadius(BG_CIRCLE_RADIUS);
        bgCircle.setFill(BG_COLOUR);
        bgCircle.setCursor(Cursor.HAND);
        bgCircle.setCenterY(BG_CIRCLE_RADIUS); bgCircle.setCenterX(BG_CIRCLE_RADIUS);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.0, 0.0, 0.0, 0.25));

        bgCircle.setEffect(dropShadow);
        view.getChildren().add(bgCircle);
    }

    private void addImgIcon(String iconID) {
        icon = new ImageView(new Image(ToolIcon.class.getResourceAsStream("/icons/"+iconID+".png")));
        icon.setFitHeight(ICON_SIZE); icon.setFitWidth(ICON_SIZE);
        icon.setX(BG_CIRCLE_RADIUS - ICON_SIZE/2); icon.setY(BG_CIRCLE_RADIUS - ICON_SIZE/2);
        icon.setCursor(Cursor.HAND);

        view.getChildren().add(icon);
    }


}
