package Player.Regions;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public abstract class Region {

    double myWidth;
    double myHeight;
    Paint myColor;
    Group myGroup;
    protected ArrayList<Region> myComponents;

    public Region(double wd, double ht, Paint color) {
        myComponents = new ArrayList<>();
        myWidth = wd;
        myHeight = ht;
        myColor = color;

        myGroup = new Group();
        buildGroup();
    }

//    public Group getNode() {
//        return myGroup;
//    }

    abstract void buildGroup();

//    public abstract Pane getPane();

//    protected void setBackground(Paint bg, CornerRadii cr, Insets in) {
//        myHBox.setBackground(new Background(new BackgroundFill(bg, cr, in)));
//    }
//
//    protected void setSize(double wd, double ht) {
//        myWidth = wd;
//        myHeight = ht;
//
//        myHBox.setPrefWidth(wd);
//        myHBox.setPrefHeight(ht);
//    }
//
//    protected HBox getHBox() {
//        return myHBox;
//    }

}
