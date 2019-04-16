package GameCenter;

import GameCenter.GameData.DataParser;
import GameCenter.GameData.DataStruct;
import Player.src.PlayerMain.PlayerStage;
import auth.RunAuth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private int myIndex;
    private ImageView activeGameImageView;

    public Pane socialPane;
    public Pane newGamePane;
    public Pane descriptionPane;
    public ScrollPane thumbPane;
    public VBox thumbPaneContent;
    public Text titleText;
    public Text descriptionText;
    public Button newGameButton;
    public Button playButton;
    public Button editButton;
    public Button rateButton;

    void initGameCenter() {
        placeThumbnails();
    }

    private void placeThumbnails() {
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
        this.myIndex = index;
        if (activeThumbnail == myIndex) {
            activeThumbnail = -1;
            titleText.setText("Game Center");
            revertDescription();
            return; // we do not want to call loadGameDetails(), so we return if condition for this if statement is true
        }
        else {
            titleText.setText(gameData.get(myIndex).name);
            activeThumbnail = myIndex;
        }

        loadGameDetails();
    }

    private void revertDescription() {
        newGamePane.getChildren().remove(activeGameImageView);
        descriptionPane.setVisible(false);
    }

    private void loadGameDetails() {
        if (activeGameImageView != null) {
            newGamePane.getChildren().remove(activeGameImageView);
        }
        loadGameImage();
        loadGameText();
        descriptionPane.setVisible(true);
    }

    private void loadGameImage() {
        activeGameImageView = new ImageView(this.getClass().getResource("/img/"+gameData.get(myIndex).imagePath).toString());
        activeGameImageView.setFitWidth(newGamePane.getWidth());
        activeGameImageView.setFitHeight(newGamePane.getHeight());
        activeGameImageView.setEffect(new GaussianBlur(100));
        var clipRect = new Rectangle();
        clipRect.setWidth(newGamePane.getWidth());
        clipRect.setHeight(newGamePane.getHeight());
        clipRect.setArcWidth(25); clipRect.setArcHeight(25);
        activeGameImageView.setClip(clipRect);
        newGamePane.getChildren().add(activeGameImageView);
    }

    private void loadGameText() {
        descriptionText.setText(gameData.get(myIndex).desc);
    }

    public void launchAuthEnv() {
        new RunAuth().start(new Stage());
    }

    public void launchPlayer() {
        new PlayerStage().run(gameData.get(myIndex).name);
    }
}