package GameCenter.main;

import GameCenter.gameData.DataParser;
import GameCenter.gameData.DataStruct;
import GameCenter.utilities.Thumbnail;
import Player.src.PlayerMain.PlayerStage;
import auth.RunAuth;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import network_account.UserIdentity;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * The Controller for the GameCenter. Works in conjunction with GameCenter.java and GameCenter.fxml, which can be found
 * under the resources folder.
 *
 * This controller defines all actions that occur when a user interacts with the GUI. It also defines several parts of
 * the GUI that cannot be done in fxml, such as placing images parsed from a .json file.
 */
public class GameCenterController {
    private List<DataStruct> gameData;
    private int activeThumbnail;
    private int myIndex;
    private Number ratingVal;
    private ImageView activeGameImageView;

    @FXML
    public Pane socialPane, newGamePane, descriptionPane, ratingPane;
    public ScrollPane thumbPane;
    public GridPane friendPane;
    public Slider ratingSlider;
    public VBox thumbPaneContent;
    public Text titleText, descriptionText, ratingText;
    public Button newGameButton, playButton, editButton, rateButton, returnButton;
    public Label nameLabel, score1, score2, score3;

    void initGameCenter() {
        initListeners();
        placeThumbnails();
    }

    private void initListeners() {
        ratingSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                ratingVal = new_val;
                ratingText.setText(String.format("%.2f", ratingVal));
            }
        });
    }

    private void placeThumbnails() {
        activeThumbnail = -1;
        int counter = 0;
        try {
            gameData = DataParser.parseConfig("data/player_data.json");
            for(var game : gameData) {
                final int index = counter;
                var thumbnail = new Thumbnail(new Image(this.getClass().getResourceAsStream(game.getImagePath())), game.getName());
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
//        writeRatingToJSON();

        if (activeThumbnail == myIndex) {
            activeThumbnail = -1;
            titleText.setText("Game Center");
            revertDescription();
            return; // we do not want to call loadGameDetails(), so we return if condition for this if statement is true
        }
        else {
            titleText.setText(gameData.get(myIndex).getName());
            activeThumbnail = myIndex;
        }

        loadGameDetails();
    }

    private void revertDescription() {
        newGamePane.getChildren().remove(activeGameImageView);
        descriptionPane.setVisible(false);
        ratingPane.setVisible(false);
    }

    private void loadGameDetails() {
        if (activeGameImageView != null) {
            newGamePane.getChildren().remove(activeGameImageView);
        }
        loadGameImage();
        loadGameText();
        descriptionPane.setVisible(true);
        ratingPane.setVisible(false);
    }

    private void loadGameImage() {
        activeGameImageView = new ImageView(this.getClass().getResource(gameData.get(myIndex).getImagePath()).toString());
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
        descriptionText.setText(gameData.get(myIndex).getDescription());
    }

    private void updateIdentity(UserIdentity userIdentity, String gameName){
        nameLabel.setText("Hello" + userIdentity.getName());
        for(String s:userIdentity.getFriends()){
            Label friendName = new Label(s);
            friendName.getStyleClass().add("socialScoreLabel");
            friendPane.getChildren().add(friendName);
        }
        score1.setText(userIdentity.getHighScores(gameName).get(0));
        score2.setText(userIdentity.getHighScores(gameName).get(1));
        score3.setText(userIdentity.getHighScores(gameName).get(2));
    }

    @FXML
    private void launchAuthEnv() {
        new RunAuth().start(new Stage());
    }

    @FXML
    private void launchPlayer() {
        new PlayerStage().run(gameData.get(myIndex).getSourcePath());
    }

    @FXML
    private void rateGame() {
        ratingPane.setVisible(true);
        descriptionPane.setVisible(false);
    }

    @FXML
    private void login() {
        // TODO: integrate with Ian's login
    }

    @FXML
    private void returnToDescription() {
        ratingPane.setVisible(false);
        descriptionPane.setVisible(true);
    }

}