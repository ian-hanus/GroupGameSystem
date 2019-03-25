package Player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Thumbnail {

    public static final Paint DEFAULT_COLOR = Color.RED;

    private StackPane myPaneRoot;
    private ImageView myImage;
    private double myWidth;
    private double myHeight;
    private double myOffset;

    public Thumbnail(String filename, double wd, double ht) {

        File file = new File(filename);
        myImage = new ImageView();
        try {
            myImage = new ImageView(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("oops");
        }

        myPaneRoot = new StackPane();
        myWidth = wd;
        myHeight = ht;

        makeThumbnail();
    }

    private void makeThumbnail() {
//        myPaneRoot.getChildren().add(new Rectangle(myWidth, myHeight, DEFAULT_COLOR));
        myImage.setFitWidth(myWidth);
        myImage.setFitHeight(myHeight);
        myPaneRoot.getChildren().add(myImage);
        myPaneRoot.setOnMouseClicked(e -> System.out.println("clicked thumbnail!"));
    }

    public StackPane getPane() {
        return myPaneRoot;
    }
}
