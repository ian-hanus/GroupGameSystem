package panes;

import static basic.Styles.*;
import static basic.Dimensions.*;

public class BottomPane extends CustomPane {
    public BottomPane(double x, double width, double height) {
        super(x, ENV_WINDOW_HEIGHT - height, width, height, BOTTOM_PANE_STYLE);
    }
}
