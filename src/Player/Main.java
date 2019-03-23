package Player;

public class Main extends Application {

    public void start(Stage stage) {
        
        PlayerStage player = new PlayerStage();
        Stage st = player.makeStage();
        st.show();

    }

}