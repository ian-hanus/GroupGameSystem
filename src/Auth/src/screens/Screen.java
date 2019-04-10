package screens;

import basic.RunAuth;
import javafx.stage.Stage;

public abstract class Screen {
    public abstract Stage createScreen(Stage s, RunAuth a);
}
