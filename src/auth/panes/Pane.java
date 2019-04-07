package auth.panes;

import javafx.scene.layout.Region;

import static auth.helpers.RectangleHelpers.createStyledRectangle;

public abstract class Pane {
    private Region view;
    public Pane(double x, double y, double width, double height, String style) {
        view = createStyledRectangle(x, y,
                width, height, style);
    }
    public Region getView() {
        return view;
    }
}
