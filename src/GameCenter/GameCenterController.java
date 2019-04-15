package GameCenter;

import GameCenter.GameData.DataParser;
import GameCenter.GameData.DataStruct;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Write JavaDoc
 */
public class GameCenterController {
    private List<DataStruct> gameData;
    private List<Thumbnail> thumbnails;

    public Pane socialPane;
    public VBox thumbPaneContent;
    public Pane descriptionPane;
    public Text titleText;
    public ScrollPane thumbPane;

//    @FXML protected void selectGame(MouseEvent event) {
//        Thumbnail thumbnail = (Thumbnail) event.getSource();
//        titleText.setText(thumbnail.getName());
//    }

    public void placeThumbnails() {
        thumbnails = new ArrayList<Thumbnail>();
        try {
            gameData = DataParser.parseConfig("data/player_data.json");
            thumbPane.setContent(thumbPaneContent);
            for(var d : gameData) {
                var thumbnail = new Thumbnail(new Image(GCScreen.class.getResourceAsStream("/img/"+d.imagePath)), d.name);
                thumbnails.add(thumbnail);
                thumbPaneContent.getChildren().add(thumbnail.getView());
            }
        } catch (FileNotFoundException e) {
            // Shouldn't ever happen
        }
    }
}