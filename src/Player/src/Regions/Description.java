package Regions;

import Features.Controls.*;
import PlayerMain.PlayerStage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class Description {

    private final String VBOX_STYLE = "vbox";
    private final String DESCRIPTION_STYLESHEET = "desc";
    public final static double IMAGE_WIDTH = 330;
    public final static double IMAGE_HEIGHT = 165;
    public final static double DESC_WIDTH = IMAGE_WIDTH;
    public final static double DESC_HEIGHT = 200;

    private Map<String, String> myImageMap;
    private Map<String, String> myDescMap;

    private String myGame;
    private GridPane myPane;
    private ImageView myImage;
    private Label myDescription;
    private VBox myGameOptions;
    private PlayerStage myContext;

    public Description(String game, PlayerStage context) {
        this.myContext = context;

        // TODO: store this info differently---could be done in ResourceBundle or JSON
        myImageMap = new HashMap<>();
        myImageMap.put("Flappy Bird", "/images/flappy-bird.png");
        myImageMap.put("Mario", "/images/mario.jpg");
        myImageMap.put("Metroid", "/images/metroid.png");
        myImageMap.put("Doodle Jump", "/images/doodle-jump.jpg");

        myDescMap = new HashMap<>();
        myDescMap.put("Flappy Bird", "a bird and it flies pipes jump whoo");
        myDescMap.put("Mario", "it's a me mario");
        myDescMap.put("Metroid", "did you know samus is a girl ??");
        myDescMap.put("Doodle Jump", "ya ya yeet");

        myGame = game;

        myPane = new GridPane();
        myPane.setId(DESCRIPTION_STYLESHEET);

        placeImage();
        placeDescription();
        placeGameOptions();

    }

    private void placeImage() {

        myImage = new ImageView(new Image(this.getClass().getResourceAsStream(myImageMap.get(myGame))));
        myImage.setFitHeight(IMAGE_HEIGHT);
        myImage.setFitWidth(IMAGE_WIDTH);
        myPane.add(myImage, 0, 0, 2, 1);

    }

    private void placeDescription() {

        myDescription = new Label(myDescMap.get(myGame));
        myPane.add(myDescription, 0, 1, 2, 1);

    }

    // TODO: add Play, Edit, Rate, Rating, High Scores
    private void placeGameOptions() {
        myGameOptions = new VBox();
        myGameOptions.getStyleClass().add(VBOX_STYLE);

        Button run = new Run(myContext, myGame).getButton();
        Button edit = new Edit(myContext, myGame).getButton();
        Button rate = new Rate(myContext, myGame).getButton();

        myGameOptions.getChildren().addAll(run, edit, rate);



        myPane.add(myGameOptions, 2, 0, 1, 2);
    }


    protected GridPane getPane() {
        return myPane;
    }
}
