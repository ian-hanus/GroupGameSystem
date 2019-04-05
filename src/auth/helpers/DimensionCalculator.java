package auth.helpers;

import javafx.scene.Node;

import static auth.Dimensions.ENV_WINDOW_HEIGHT;

public class DimensionCalculator {
    public static double centreVertical(double height) {
        return (ENV_WINDOW_HEIGHT / 2.0) - (height / 2.0);
    }
}
