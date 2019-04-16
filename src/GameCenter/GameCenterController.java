package GameCenter;

import GameCenter.GameData.DataParser;
import GameCenter.GameData.DataStruct;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
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
    private ImageView activeGameImageView;

    public Pane socialPane;
    public VBox thumbPaneContent;
    public Pane descriptionPane;
    public Text titleText;
    public ScrollPane thumbPane;
    public Button newGameButton;

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
            return; // we do not want to call loadGameDetails(), so we return if condition for this if statement is true
        }
        else {
            titleText.setText(gameData.get(index).name);
            activeThumbnail = index;
        }

        loadGameDetails(index);
    }

    private void revertDescription() {
        descriptionPane.getChildren().remove(activeGameImageView);
        newGameButton.setVisible(true);
    }

    private void loadGameDetails(int index) {
        if (activeGameImageView != null) {
            descriptionPane.getChildren().remove(activeGameImageView);
        }
        newGameButton.setVisible(false);
        loadGameImage(index);
    }

    private void loadGameImage(int index) {
        activeGameImageView = new ImageView(this.getClass().getResource("/img/"+gameData.get(index).imagePath).toString());
        activeGameImageView.setFitWidth(descriptionPane.getWidth());
        activeGameImageView.setFitHeight(descriptionPane.getHeight());
        activeGameImageView.setEffect(new GaussianBlur(100));
        var clipRect = new Rectangle();
        clipRect.setWidth(descriptionPane.getWidth());
        clipRect.setHeight(descriptionPane.getHeight());
        clipRect.setArcWidth(25); clipRect.setArcHeight(25);
        activeGameImageView.setClip(clipRect);
        descriptionPane.getChildren().add(activeGameImageView);
    }
}