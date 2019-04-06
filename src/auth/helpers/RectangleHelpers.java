package auth.helpers;

import javafx.scene.layout.Region;

public class RectangleHelpers {
    public static Region createStyledRectangle(double x, double y, double width, double height, String style) {
        var rect = new Region();
        rect.setLayoutX(x); rect.setLayoutY(y); rect.setPrefSize(width, height);
        rect.setStyle(style);
        return rect;
    }
}
