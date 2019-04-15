package GameCenter;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class GameCenter extends Application {
    public static Font sofiaPro, sofiaProSmall, bebasKai, bebasKaiMedium;

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/GameCenter.fxml"));

        try {
            sofiaPro = Font.loadFont(this.getClass().getResourceAsStream("/fonts/sofiapro-light.otf"),30);
            sofiaProSmall = Font.loadFont(this.getClass().getResourceAsStream("/fonts/sofiapro-light.otf"),15);
            bebasKai = Font.loadFont(this.getClass().getResourceAsStream("/fonts/bebaskai.otf"),15);
            bebasKaiMedium = Font.loadFont(this.getClass().getResourceAsStream("/fonts/bebaskai.otf"),25);
        } catch (Exception e) {
            System.out.println("A problem occurred when loading fonts.");
        }

        Scene scene = new Scene(root, 975, 500);
        scene.getStylesheets().add("GameCenter.css");

        stage.setTitle("Game Center");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
