package plotter_hud_utility;

import Player.src.Features.ScrollableWindows.IncompatibleArgumentLengthException;
import Player.src.PlayerMain.Plotter;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Arrays;

/**
 * A Heads Up Display utility with customizable data and an optional Plotter for visualizing data.
 * Declare the name of each data type for display purposes,
 * and provide an array of corresponding data Object to display next to each name.
 * Update the HUD by providing another array of data Objects.
 * In a game loop, we recommend implementing a getHUDValues method and calling HUD.update(getHUDValues)
 * at the end of each iteration (or every so often).
 *
 * @author Hunter Gregory
 * @author Carter Gay
 */
public class HUD {
    private static final String TITLE_ID_CSS = "hud-title";
    private static final String DATA_LABEL_CLASS_CSS = "data-label";
    private static final String SCROLL_PANE_CLASS_CSS = "scroll-pane";
    private static final double INTER_VALUES_SPACING = 2;
    private static final double PLOT_VALUES_SPACING = 20;

    private String[] myDataNames;
    private Label[] myDataLabels;
    private Label myTitle;
    private ScrollPane myScrollPane;
    private VBox myHudValuesBox;
    private VBox myPlotAndValuesBox;
    private Plotter myPlotter = null;

    /**
     * Create a basic HUD with the names of data to display
     * @param width
     * @param height
     * @param title
     * @param dataNames
     */
    public HUD(double width, double height, String title, String[] dataNames) {
        createTitle(title);
        myDataNames = dataNames;
        myHudValuesBox = new VBox();
        myHudValuesBox.setSpacing(INTER_VALUES_SPACING);
        myPlotAndValuesBox = new VBox(myHudValuesBox);
        myPlotAndValuesBox.setSpacing(PLOT_VALUES_SPACING);
        createBlankLabels();
        addLabelsToVBox();
        createScrollPane(width, height);
    }

    /**
     * Create a HUD with plotting capabilities
     * @param width
     * @param height
     * @param title
     * @param dataNames
     * @param plotter
     */
    public HUD(double width, double height, String title, String[] dataNames, Plotter plotter) {
        this(width, height, title, dataNames);
        myPlotter = plotter;
        myPlotAndValuesBox.getChildren().add(myPlotter.getNode());
    }

    /**
     * @return the HUD display
     */
    public Node getNode() {
        return myScrollPane;
    }

    /**
     * Updates the values displayed in the HUD and updates any plots if applicable.
     * @param values
     * @throws IncompatibleArgumentLengthException if the number of values does not equal the number of data labels
     */
    public void update(Object[] values) throws IncompatibleArgumentLengthException {
        if (values.length != myDataNames.length)
            throw new IncompatibleArgumentLengthException();
        clearText();
        for (int k = 0; k< myDataNames.length; k++)
            myDataLabels[k].setText(myDataNames[k] + ": " + values[k].toString());

        if (myPlotter != null)
            myPlotter.updateGraph();
    }

    /**
     * Update the title of the HUD
     * @param title
     */
    public void setNewTitle(String title) {
        myTitle.setText(title);
    }

    private void createTitle(String title) {
        myTitle = new Label(title);
        myTitle.setStyle(DATA_LABEL_CLASS_CSS);
        myTitle.setId(TITLE_ID_CSS);
    }

    private void addLabelsToVBox() {
        myHudValuesBox.getChildren().add(myTitle);
        for (Label label : myDataLabels)
            myHudValuesBox.getChildren().add(label);
    }

    private void createBlankLabels() {
        myDataLabels = new Label[myDataNames.length];
        for (int k = 0; k< myDataLabels.length; k++) {
            myDataLabels[k] = new Label();
            myDataLabels[k].setStyle(DATA_LABEL_CLASS_CSS);
        }
    }

    private void createScrollPane(double width, double height) {
        myScrollPane = new ScrollPane(myPlotAndValuesBox);
        myScrollPane.setStyle(SCROLL_PANE_CLASS_CSS);
        setDimensions(myScrollPane, width, height);
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setDisable(false);
    }

    private void setDimensions(Region region, double width, double height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMinWidth(width);
        region.setMaxWidth(width);
    }

    private void clearText() {
        Arrays.stream(myDataLabels).forEach(label -> label.setText(""));
    }
}
