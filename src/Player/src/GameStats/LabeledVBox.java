package Player.src.GameStats;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A VBox with a label above a Node (label text and Node specified in subclass).
 *
 * @author Hunter Gregory
 */
public abstract class LabeledVBox {
    private static final String LABEL_CLASS_CSS = "labeled-vbox";

    private VBox myVBox;
    private String myLabelText;
    protected DataTracker[] myTrackers;

    public LabeledVBox(String labelText, DataTracker[] dataTrackers) {
        myTrackers = dataTrackers;
        myLabelText = labelText;
    }

    /**
     * @return VBox with label and node
     */
    public VBox getVBox() {
        if (myVBox == null) {
            var label = new Label(myLabelText);
            label.setStyle(LABEL_CLASS_CSS);
            myVBox = new VBox(label, getMainComponent());
        }
        return myVBox;
    }

    /**
     * @param dataName
     * @return DataTracker with given name
     */
    protected DataTracker getTracker(String dataName) {
        for (DataTracker tracker : myTrackers) {
            if (tracker.getDataName().equals(dataName))
                return tracker;
        }
        return null;
    }

    /**
     * @return Node to be displayed under label
     */
    abstract protected Node getMainComponent();
}
