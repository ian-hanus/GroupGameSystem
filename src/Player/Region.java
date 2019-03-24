package Player;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public abstract class Region {

//    private double myWidth;
//    private double myHeight;
    Group myGroup;
    protected ArrayList<Region> myComponents;

    public Region() {
        myGroup = new Group();
        myComponents = new ArrayList<>();

        buildRegion();
    }

    protected Group getNode() {
        return myGroup;
    }

    abstract void buildRegion();

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
