package uiutils.panes;

import static auth.Styles.CENTRE_PANE_STYLE;

public class CentrePane extends Pane{
    public CentrePane(double x, double y, double width, double height) {
        super(x, y, width, height, CENTRE_PANE_STYLE);
    }
    public CentrePane(double x, double y, double width, double height, String ID) {
        super(x, y, width, height, CENTRE_PANE_STYLE, ID);
    }
}
