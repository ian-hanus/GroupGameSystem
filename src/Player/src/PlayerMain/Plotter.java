package Player.src.PlayerMain;

import Player.src.GameStats.DataTracker;
import Player.src.GameStats.PlotBuilder;
import Player.src.GameStats.XAxisSelector;
import Player.src.GameStats.YAxisSelector;
import javafx.scene.Node;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.VBox;

public class Plotter {
    private XAxisSelector myXAxisSelector;
    private YAxisSelector myYAxisSelector;
    private ScatterChart<Number, Number> myScatterChart;
    private VBox myVBox;

    public Plotter(DataTracker[] dataTrackers) {
        myXAxisSelector = new XAxisSelector(dataTrackers);
        myYAxisSelector = new YAxisSelector(dataTrackers);
        updateGraph();
        myVBox = new VBox(myScatterChart, myYAxisSelector.getVBox(), myXAxisSelector.getVBox());
    }

    public void updateGraph() {
        var plotBuilder = new PlotBuilder(myXAxisSelector.getSelectedTracker(), myYAxisSelector.getSelectedDataTrackers());
        myScatterChart = plotBuilder.createPlot();
    }

    public Node getNode() {
        return myVBox;
    }
}
