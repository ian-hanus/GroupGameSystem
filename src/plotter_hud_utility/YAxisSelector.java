package plotter_hud_utility;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * A LabeledVBox that contains a checkbox associated with each DataTracker.
 *
 * @author Hunter Gregory
 */
public class YAxisSelector extends LabeledVBox {
    private static final String DESCRIPTION = "Y data";

    private VBox myVBox;
    private ArrayList<CheckBox> myCheckBoxes;

    /**
     * Create a YAxisSelector
     * @param dataTrackers
     */
    public YAxisSelector(DataTracker[] dataTrackers) {
        super(DESCRIPTION, dataTrackers);
        constructVBox();
    }

    private void constructVBox() {
        myVBox = new VBox();
        myCheckBoxes = new ArrayList<>();
        for (DataTracker tracker : myTrackers) {
            var checkbox = new CheckBox(tracker.getDataName());
            checkbox.setAllowIndeterminate(false);
            myCheckBoxes.add(checkbox);
            myVBox.getChildren().add(checkbox);
        }
    }

    /**
     * @return list of DataTrackers currently selected
     */
    public List<DataTracker> getSelectedTrackers() {
        List<DataTracker> selectedTrackers = new ArrayList<>();
        for (int k=0; k<myTrackers.length; k++) {
            if (myCheckBoxes.get(k).isSelected())
                selectedTrackers.add(myTrackers[k]);
        }
        return selectedTrackers;
    }

    @Override
    protected Node getMainComponent() {
        return myVBox;
    }
}
