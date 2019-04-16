package GameCenter;

import GameCenter.GameData.DataParser;
import GameCenter.GameData.DataStruct;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Write JavaDoc
 */
public class GameCenterController {
    private List<Thumbnail> thumbnails;
    private List<DataStruct> gameData;
    private int activeThumbnail;
    private ImageView permaBgImage;
    private HBox actionsContainer;

    public Pane socialPane;
    public VBox thumbPaneContent;
    public Pane descriptionPane;
    public Text titleText;
    public ScrollPane thumbPane;

    void placeThumbnails() {
        thumbnails = new ArrayList<Thumbnail>();
        activeThumbnail = -1;
        int counter = 0;
        try {
            gameData = DataParser.parseConfig("data/player_data.json");
            for(var game : gameData) {
                final int index = counter;
                var thumbnail = new Thumbnail(new Image(GCScreen.class.getResourceAsStream("/img/" + game.imagePath)), game.name);
                thumbnails.add(thumbnail);
                thumbPaneContent.getChildren().add(thumbnail.getView());
                thumbnail.getView().setOnMouseClicked(e -> thumbnailClicked(index));
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred when placing thumbnails");
        }
    }

    private void thumbnailClicked(int index) {
        if (activeThumbnail == index) {
            activeThumbnail = -1;
            titleText.setText("Game Center");
            revertDescription();
        }
        else {
            descriptionPane.getChildren().clear();
            titleText.setText(gameData.get(index).name);
            activeThumbnail = index;
        }

        loadGameDetails(index);
    }

    private void revertDescription() {
        Button newGameButton = new Button();
        newGameButton.getStyleClass().add("button");
        newGameButton.setLayoutX(110);
        newGameButton.setLayoutY(170);
        newGameButton.setText("Create a new game");
        descriptionPane.getChildren().add(newGameButton);
    }

    private void loadGameDetails(int index) {
        return;
    }
}