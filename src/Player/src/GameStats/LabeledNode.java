package Player.src.GameStats;

import Player.src.GameStats.DataTracker;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class LabeledNode {
    private static final String LABEL_CLASS_CSS = "description";

    private VBox myVBox;
    protected DataTracker[] myTrackers;

    public LabeledNode(String labelText, DataTracker[] dataTrackers) {
        myVBox = new VBox();
        myTrackers = dataTrackers;
        var label = new Label(labelText);
        label.setStyle(LABEL_CLASS_CSS);
        myVBox.getChildren().add(label);
        myVBox.getChildren().add(getMainComponent());
    }

    public VBox getVBox() {
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
