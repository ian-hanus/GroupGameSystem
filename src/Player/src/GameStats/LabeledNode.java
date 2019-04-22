package Player.src.GameStats;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class LabeledNode {
    private static final String LABEL_CLASS_CSS = "description";

    private VBox myVBox;
    private String myLabelText;
    protected DataTracker[] myTrackers;

    public LabeledNode(String labelText, DataTracker[] dataTrackers) {
        myVBox = new VBox();
        myTrackers = dataTrackers;
        myLabelText = labelText;
    }

    public VBox getVBox() {
        if (myVBox == null) {
            var label = new Label(myLabelText);
            label.setStyle(LABEL_CLASS_CSS);
            myVBox = new VBox(label, getMainComponent());
        }
        return myVBox;
    }

    protected DataTracker getTracker(String dataName) {
        for (DataTracker tracker : myTrackers) {
            if (tracker.getDataName().equals(dataName))
                return tracker;
        }
        return null;
    }

    abstract protected Node getMainComponent();
}
