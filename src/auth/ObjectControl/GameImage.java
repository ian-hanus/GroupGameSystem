package auth.ObjectControl;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameImage extends ImageView {

    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    private Bounds myBounds;

    public GameImage(double x, double y, Image image, Bounds bounds){
        super(image);
        super.setX(x);
        super.setY(y);
        myBounds = bounds;
        setUpImage();
    }

    public void setUpImage(){
        super.setPreserveRatio(true);
        super.setFitHeight(50);
        super.setOnMousePressed(m -> {
            orgSceneX = m.getSceneX();
            orgSceneY = m.getSceneY();
            orgTranslateX = ((GameImage)(m.getSource())).getTranslateX();
            orgTranslateY = ((GameImage)(m.getSource())).getTranslateY();
            m.consume();
        });
        super.setOnMouseDragged(m -> {
            double offsetX = m.getSceneX() - orgSceneX;
            double offsetY = m.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            ((GameImage)(m.getSource())).setTranslateX(newTranslateX);
            ((GameImage)(m.getSource())).setTranslateY(newTranslateY);
            m.consume();
        });
    }

}
