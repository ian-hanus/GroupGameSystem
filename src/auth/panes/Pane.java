package auth.panes;

import static auth.helpers.RectangleHelpers.createStyledRectangle;

public abstract class Pane {
    private javafx.scene.layout.Pane view;
    public Pane(double x, double y, double width, double height, String style) {
        view = createStyledRectangle(x, y,
                width, height, style);
    }
    public javafx.scene.layout.Pane getView() {
        return view;
    }
}
