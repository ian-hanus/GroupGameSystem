package GameCenter;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import static GameCenter.Dimensions.*;
import static javafx.scene.paint.Color.WHITE;

public class Thumbnail {
    private String myName;
    private Group view;
    private boolean selected;
    public Thumbnail(Image img, String name) {
        view = new Group(); view.setCursor(Cursor.HAND);
        myName = name;
        Rectangle clipRect = new Rectangle(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
        Rectangle bgRect = new Rectangle(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
        clipRect.setArcHeight(20.0);
        clipRect.setArcWidth(20.0);
        bgRect.setArcHeight(20.0);
        bgRect.setArcWidth(20.0);
        bgRect.setFill(WHITE);
        var imgView = new ImageView(img);
        imgView.setFitHeight(THUMBNAIL_WIDTH);
        imgView.setFitWidth(THUMBNAIL_WIDTH);
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

    public String getName() {return myName; }
}
