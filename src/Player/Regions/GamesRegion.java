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
    public static final double OFFSET = 10;

    private ArrayList<String> myNames;
    private ArrayList<String> myFiles;
    private VBox myThumbnails;
    private ScrollPane myGamesPane;
    private double myThumbnailWidth;
    private double myThumbnailHeight;

    public GamesRegion(double wd, double ht, Paint color) {
        super(wd, ht, color);

        myGamesPane = new ScrollPane();
        myGamesPane.setPrefWidth(myWidth);
        myGamesPane.setPrefHeight(myHeight);
        myGamesPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        myGamesPane.setBackground(new Background(new BackgroundFill(
                myColor, new CornerRadii(5, 5, 5, 5,  false),
                new Insets(20, 20, 20, 20))));
        // TODO: un-hardcode this
        myGamesPane.setStyle("-fx-background: lightsteelblue; " +
                "-fx-background-radius: 5; " +
                "-fx-background-color: lightsteelblue; ");

        myThumbnailWidth = myWidth - (2 * OFFSET) - 15; // -15 to account for the scrollbar which is 15
        myThumbnailHeight = myThumbnailWidth / 2;

        buildLists();
        buildGroup();

        myGamesPane.setContent(myThumbnails);


    }

    protected void buildGroup() {

        addThumbnails(myNames, myFiles);

    }

    protected void addThumbnails(ArrayList<String> names, ArrayList<String> files) {
        myThumbnails = new VBox();
        myThumbnails.setSpacing(OFFSET);
        myThumbnails.setPadding(new Insets(OFFSET, OFFSET, OFFSET, OFFSET));
        for (int i = 0; i < names.size(); i++) {
            String gamename = names.get(i);
            String filename = files.get(i);
            Thumbnail thumbnail = new Thumbnail(filename, myThumbnailWidth, myThumbnailHeight);
//            Thumbnail thumbnail = new Thumbnail(filename, 20, 20);
//            thumbnail.getPane().setOnMouseClicked(e -> System.out.println("click!"));
            StackPane thumbPane = thumbnail.getPane();
            thumbPane.setLayoutX(OFFSET);
            myThumbnails.getChildren().add(thumbPane);
        }
//        myGroup.getChildren().add(myThumbnails);
//        myGamesPane.setContent(myThumbnails);
    }

    public ScrollPane getPane() {
        return myGamesPane;
    }

    private void buildLists() {
        myNames = new ArrayList<>();
        myNames.add("Flappy Bird");
        myNames.add("Flappy Bird 2");
        myNames.add("Flappy Bird 3");
        myNames.add("Flappy Bird 4");
        myFiles = new ArrayList<>();
        myFiles.add("lib/images/flappy-bird.png");
        myFiles.add("lib/images/flappy-bird.png");
        myFiles.add("lib/images/flappy-bird.png");
        myFiles.add("lib/images/flappy-bird.png");
    }

}
