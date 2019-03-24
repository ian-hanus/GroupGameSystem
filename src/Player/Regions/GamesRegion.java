package Player.Regions;

import Player.Thumbnail;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GamesRegion extends Region {

//    public static final double THUMB_WD = 300;
//    public static final double THUMB_HT = 150;

    private ArrayList<String> myNames;
    private ArrayList<String> myFiles;
    private VBox myThumbnails;
    private ScrollPane myGamesPane;
    private double myThumbnailWidth;
    private double myThumbnailHeight;

    public GamesRegion(double wd, double ht, Paint color) {
        super(wd, ht, color);

        myThumbnailWidth = wd - 20;
        myThumbnailHeight = myThumbnailHeight / 2;

        myGamesPane = new ScrollPane();
        myGamesPane.setPrefWidth(wd);
        myGamesPane.setPrefHeight(ht);
        myGamesPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        myGamesPane.setBackground(new Background(new BackgroundFill(
                myColor, new CornerRadii(5, 5, 5, 5,  false), Insets.EMPTY)));

        myGamesPane.setContent(myGroup);
    }

    protected void buildGroup() {
        myNames = new ArrayList<>();
        myNames.add("Flappy Bird");
        myFiles = new ArrayList<>();
        myFiles.add("/lib/images/flappybird.jpg");

        addGames(myNames, myFiles);
    }

    protected void addGames(ArrayList<String> names, ArrayList<String> files) {
        myThumbnails = new VBox();
        for (int i = 0; i < names.size(); i++) {
            String n = names.get(i);
            String f = files.get(i);
            Thumbnail thumbnail = new Thumbnail(f, myThumbnailWidth, myThumbnailHeight);
            thumbnail.getPane().setOnMouseClicked(e -> System.out.println("click!"));
            myThumbnails.getChildren().add(thumbnail.getPane());
        }
        myGamesPane.setContent(myThumbnails);
    }

    public ScrollPane getPane() {
        return myGamesPane;
    }


}
