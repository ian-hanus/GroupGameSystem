package auth.helpers;

import javafx.scene.layout.Region;

import static auth.Dimensions.*;

public class DimensionCalculator {
    public static double centreVertical(double height) {
        return (ENV_WINDOW_HEIGHT / 2.0) - (height / 2.0);
    }

    public static double computeRightEdge(double width) {
        return (ENV_WINDOW_WIDTH) - (width);
    }

    public static double computeMarginToBottomEdge(Region region, double desiredMargin) {
        return region.getLayoutY() + region.getPrefHeight() + desiredMargin;
    }
}
