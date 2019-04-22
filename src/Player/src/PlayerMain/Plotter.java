package Player.src.PlayerMain;

import Player.src.GameStats.DataTracker;
import Player.src.GameStats.PlotBuilder;
import Player.src.GameStats.XAxisSelector;
import Player.src.GameStats.YAxisSelector;
import javafx.scene.Node;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.VBox;

/**
 * A tool for displaying a plot with axes specified by a mini GUI.
 * Combines the functionality of XAxisSelector, YAxisSelector, and PlotBuilder into one displayable entity.
 *
 * @author Hunter Gregory
 */
public class Plotter {
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
        myVBox = new VBox(getCurrentGraph(), myYAxisSelector.getVBox(), myXAxisSelector.getVBox());
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
