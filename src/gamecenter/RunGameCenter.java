package gamecenter;

import auth.RunAuth;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RunGameCenter extends Application {
    public static Font sofiaPro, sofiaProSmall, bebasKai, bebasKaiMedium;
    @Override
    public void start(Stage stage) {
        try {
            sofiaPro = Font.loadFont(RunAuth.class.getResource("/fonts/sofiapro-light.otf").openStream(),30);
            sofiaProSmall = Font.loadFont(RunAuth.class.getResource("/fonts/sofiapro-light.otf").openStream(),15);
            bebasKai = Font.loadFont(RunAuth.class.getResource("/fonts/bebaskai.otf").openStream(),15);
            bebasKaiMedium = Font.loadFont(RunAuth.class.getResource("/fonts/bebaskai.otf").openStream(),25);
        } catch (Exception e) {
            e.printStackTrace();
        }

        var gc = new GCScreen();
        gc.show();
    }
}
