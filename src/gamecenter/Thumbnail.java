package gamecenter;

import auth.helpers.RectangleHelpers;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static gamecenter.Dimensions.*;
import static gamecenter.Styles.*;
import static javafx.scene.paint.Color.WHITE;

public class Thumbnail {
    private Group view;
    private boolean selected;
    public Thumbnail(Image img) {
        view = new Group(); view.setCursor(Cursor.HAND);
        Rectangle clipRect = new Rectangle(THUMBNAIL_SIZE,THUMBNAIL_SIZE);
        Rectangle bgRect = new Rectangle(THUMBNAIL_SIZE,THUMBNAIL_SIZE);
        clipRect.setArcHeight(20.0);
        clipRect.setArcWidth(20.0);
        bgRect.setArcHeight(20.0);
        bgRect.setArcWidth(20.0);
        bgRect.setFill(WHITE);
        var imgView = new ImageView(img);
        imgView.setFitHeight(THUMBNAIL_SIZE);
        imgView.setFitWidth(THUMBNAIL_SIZE);
        imgView.setLayoutX(0); imgView.setLayoutY(0);
        imgView.setClip(clipRect);
        view.getChildren().addAll(bgRect, imgView);
    }

    public void select() {
        DropShadow d = new DropShadow();
        d.setRadius(20);
        d.setColor(WHITE);
        view.setEffect(d);
        selected = true;
    }

    public void deselect() {
        DropShadow d = new DropShadow();
        d.setRadius(0);
        d.setColor(WHITE);
        view.setEffect(d);
        selected = false;
    }

    public Node getView () {
        return view;
    }
}
