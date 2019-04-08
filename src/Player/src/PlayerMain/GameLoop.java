package PlayerMain;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLoop extends Application {

    public void start(Stage stage) {

        PlayerStage player = new PlayerStage();
        Stage st = player.makeStage();
        st.show();

    }

    // TODO: Add methods for playing game

}