package plotter_hud_utility.plotting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A LabeledVBox that contains a dropdown selector listing all possible DataTrackers.
 *
 * @author Hunter Gregory
 */
public class XAxisSelector extends LabeledVBox {
    private static final String DESCRIPTION = "Select X data";
    private static final int NUM_OPTIONS_SHOWN = 4;

    private ComboBox<String> myDropBox;
    private DataTracker mySelectedTracker;

    /**
     * Create an XAxisSelector
     * @param dataTrackers
     */
    public XAxisSelector(DataTracker[] dataTrackers) {
        super(DESCRIPTION, dataTrackers);
        constructDropBox();
        mySelectedTracker = dataTrackers[0];
    }

    private void constructDropBox() {
        myDropBox = new ComboBox<>();
        myDropBox.setEditable(false);
        myDropBox.itemsProperty().set(getItemList());
        myDropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        myDropBox.getSelectionModel().selectFirst();
        myDropBox.valueProperty().addListener((o, oldVal, newVal) -> mySelectedTracker = getTracker(newVal));
    }

    private ObservableList<String> getItemList() {
        ArrayList<String> itemList = new ArrayList<>();
        Arrays.stream(myTrackers).forEach(tracker -> itemList.add(tracker.getDataName()));
        return FXCollections.observableArrayList(itemList);
    }

    /**
     * @return the currently selected DataTracker
     */
    public DataTracker getSelectedTracker() {
        return mySelectedTracker;
    }

    @Override
    protected Node getMainComponent() {
        return myDropBox;
    }
}
