package Player;

import demotests.RunDemo;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) {
        new RunDemo().run();
        /*
        PlayerStage player = new PlayerStage();
        Stage st = player.makeStage();
        st.show();*/

    }

}