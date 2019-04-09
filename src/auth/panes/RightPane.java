package auth.panes;

        import static auth.Styles.RIGHT_PANE_STYLE;
        import static auth.helpers.DimensionCalculator.*;

public class RightPane extends CustomPane {
    public RightPane(double y, double width, double height) {
        super(computeRightEdge(width), y, width, height, RIGHT_PANE_STYLE);
    }
}
