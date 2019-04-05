package auth.panes;

import static auth.Styles.*;
import static auth.helpers.DimensionCalculator.*;
import static auth.Dimensions.*;

public class BottomPane extends Pane {
    public BottomPane(double x, double width, double height) {
        super(x, ENV_WINDOW_HEIGHT - height, width, height, BOTTOM_PANE_STYLE);
    }
}
