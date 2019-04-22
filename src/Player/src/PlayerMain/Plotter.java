package Player.src.PlayerMain;

import plotter_hud_utility.plotting.DataTracker;
import plotter_hud_utility.plotting.PlotBuilder;
import plotter_hud_utility.plotting.XAxisSelector;
import plotter_hud_utility.plotting.YAxisSelector;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A tool for displaying a plot with axes specified by a mini GUI.
 * Combines the functionality of XAxisSelector, YAxisSelector, and PlotBuilder into one displayable entity.
 *
 * @author Hunter Gregory
 */
public class Plotter {
    private static final double VERTICAL_SPACING = 20;
    private static final double HORIZONTAL_SPACING = 25;

    private XAxisSelector myXAxisSelector;
    private YAxisSelector myYAxisSelector;
    private VBox myVBox;
    private double myWidth;
    private double myHeight;

    /**
     * Create a Plotter with a given array of possible data to plot.
     * @param dataTrackers
     * @param width
     * @param height
     */
    public Plotter(DataTracker[] dataTrackers, double width, double height) {
        myWidth = width;
        myHeight = height;
        myXAxisSelector = new XAxisSelector(dataTrackers);
        myYAxisSelector = new YAxisSelector(dataTrackers);
        setupDisplay();
        updateGraph();
    }

    private void setupDisplay() {
        var miniGUI = new HBox(myYAxisSelector.getVBox(), myXAxisSelector.getVBox());
        miniGUI.setSpacing(HORIZONTAL_SPACING);
        miniGUI.setAlignment(Pos.CENTER);
        myVBox = new VBox(getCurrentGraph(), miniGUI);
        myVBox.setSpacing(VERTICAL_SPACING);
    }

    private ScatterChart<Number, Number> getCurrentGraph() {
        var plotBuilder = new PlotBuilder(myXAxisSelector.getSelectedTracker(), myYAxisSelector.getSelectedTrackers(), myWidth, myHeight);
        return plotBuilder.createPlot();
    }

    /**
     * Update graph with new data tracker values
     */
    public void updateGraph() {
        myVBox.getChildren().set(0, getCurrentGraph());
    }

    /**
     * @return display including plot and mini GUI
     */
    public Node getNode() {
        return myVBox;
    }
}
