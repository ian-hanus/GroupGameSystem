package Regions;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.File;

public class Thumbnail {

    public static final Paint DEFAULT_COLOR = Color.RED;

    private String myName;
    private StackPane myPaneRoot;
    private ImageView myImage;
    private double myWidth;
    private double myHeight;
    private double myOffset;

    public Thumbnail(String gamename, String filename, double wd, double ht) {

        myName = gamename;
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

    public String getName() {
        return myName;
    }

    private void makeThumbnail() {
//        myPaneRoot.getChildren().add(new Rectangle(myWidth, myHeight, DEFAULT_COLOR));
        myImage.setFitWidth(myWidth);
        myImage.setFitHeight(myHeight);
        myPaneRoot.getChildren().add(myImage);
    }

    public StackPane getPane() {
        return myPaneRoot;
    }
}
