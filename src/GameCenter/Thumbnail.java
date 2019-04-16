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
    private Group myView;
    private Image myImage;
    private boolean selected;

    public Thumbnail(Image img, String name) {
        myView = new Group(); myView.setCursor(Cursor.HAND);
        myName = name;
        myImage = img;
        selected = false;

        Rectangle clipRect = new Rectangle(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
        Rectangle bgRect = new Rectangle(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
        clipRect.setArcHeight(20.0);
        clipRect.setArcWidth(20.0);
        bgRect.setArcHeight(20.0);
        bgRect.setArcWidth(20.0);
        bgRect.setFill(WHITE);
        ImageView myImageView = new ImageView(img);
        myImageView.setFitHeight(THUMBNAIL_WIDTH);
        myImageView.setFitWidth(THUMBNAIL_WIDTH);
        myImageView.setLayoutX(0); myImageView.setLayoutY(0);
        myImageView.setClip(clipRect);
        myView.getChildren().addAll(bgRect, myImageView);
    }

    public Node getView () {
        return myView;
    }

    public Image getImage() { return myImage; }

    public String getName() {return myName; }

    public boolean isSelected() {return selected;}

    public void deselect() {
        selected = false;
    }

    public void select() {
        selected = true;
    }
}
