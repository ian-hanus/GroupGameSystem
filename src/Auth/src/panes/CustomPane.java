package panes;

import javafx.scene.layout.Pane;

import static helpers.RectangleHelpers.createStyledRectangle;

public abstract class CustomPane {
    private Pane view;
    public CustomPane(double x, double y, double width, double height, String style) {
        view = createStyledRectangle(x, y,
                width, height, style);
    }
    public Pane getView() {
        return view;
    }
}
