package panes;

import static basic.Dimensions.*;
import static basic.Styles.LEFT_PANE_STYLE;

public class LeftPane extends CustomPane {
    public LeftPane(double y, double width, double height) {
        super(LEFT_EDGE, y, width, height, LEFT_PANE_STYLE);
    }
}