package basic;

import screens.CanvasScreen;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 */
public class RunAuth extends Application {
    public static  Font sofiaPro, sofiaProSmall, bebasKai, bebasKaiMedium;


    private Stage mainStage;

    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        mainStage = new CanvasScreen().createScreen(stage, this);

        stage.show();
        stage.setResizable(false);
    }
    /**
     * Starter function to serve as entry point
     * @param args: cmd line args
     */
    public static void main(String args[]) {
        // load custom font
        try {
            sofiaPro = Font.loadFont(RunAuth.class.getResource("/fonts/sofiapro-light.otf").openStream(),30);
            sofiaProSmall = Font.loadFont(RunAuth.class.getResource("/fonts/sofiapro-light.otf").openStream(),15);
            bebasKai = Font.loadFont(RunAuth.class.getResource("/fonts/bebaskai.otf").openStream(),15);
            bebasKaiMedium = Font.loadFont(RunAuth.class.getResource("/fonts/bebaskai.otf").openStream(),25);
        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
    }
}