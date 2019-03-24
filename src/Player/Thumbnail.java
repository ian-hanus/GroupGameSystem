package Player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Thumbnail {

    public static final Paint DEFAULT_COLOR = Color.BLACK;

    private StackPane myPaneRoot;
    private ImageView myImage;
    private double myWidth;
    private double myHeight;

    public Thumbnail(String filename, double wd, double ht) {

//        File file = new File(filename);
        Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(filename));
        myImage = new ImageView(img);

        myPaneRoot = new StackPane();
        myWidth = wd;
        myHeight = ht;

        makeImage();
    }

    private void makeImage() {
        myPaneRoot.getChildren().add(new Rectangle(myWidth, myHeight, DEFAULT_COLOR));
        myPaneRoot.getChildren().add(myImage);
    }

    public StackPane getPane() {
        return myPaneRoot;
    }
}
