package Player.src.Features.ScrollableWindows;

import Player.src.GameStats.DataTracker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Arrays;

public class XAxisSelector extends LabeledNode {
    private static final String DESCRIPTION = "Select an X data";
    private static final int NUM_OPTIONS_SHOWN = 4;

    private ComboBox<String> myDropBox;
    private DataTracker mySelectedTracker;

    public XAxisSelector(DataTracker[] dataTrackers) {
        super(DESCRIPTION, dataTrackers);
        constructDropBox();
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

    public DataTracker getSelectedTracker() {
        return mySelectedTracker;
    }

    @Override
    protected Node getMainComponent() {
        return myDropBox;
    }
}
