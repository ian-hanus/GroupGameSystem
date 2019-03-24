package Player;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) {

        PlayerStage player = new PlayerStage();
        Stage st = player.makeStage();
        st.show();

    }

}