package Player.src.Features.ScrollableWindows;

import Player.src.GameStats.DataTracker;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class YAxisSelector extends LabeledNode {
    private static final String DESCRIPTION = "Select 1 or more Y data";

    private VBox myVBox;
    private ArrayList<CheckBox> myCheckBoxes;

    public YAxisSelector(DataTracker[] dataTrackers) {
        super(DESCRIPTION, dataTrackers);
        mySelectedTrackers = new ArrayList<>();
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

    public DataTracker[] getSelectedDataTrackers() {
        ArrayList<DataTracker> selectedTrackers = new ArrayList<>();
        for (int k=0; k<myTrackers.length; k++) {
            if (myCheckBoxes.get(k).isSelected())
                selectedTrackers.add(myTrackers[k]);
        }
        return selectedTrackers.toArray(new DataTracker[0]);
    }

    @Override
    protected Node getMainComponent() {
        return myVBox;
    }
}
