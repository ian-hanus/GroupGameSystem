package Player.src.GameStats;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class PlotBuilder {

    private XYChart.Series mySeries;
    private ScatterChart<Number,Number> sc;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private ArrayList<Double> myX;
    private ArrayList<Double> myY;
    private String xName;
    private String yName;

    public PlotBuilder(DataTracker x, DataTracker y) {
        myX = x.getData();
        xName = x.getDataName();
        myY= y.getData();
        yName = y.getDataName();
    }

    public ScatterChart<Number,Number> createPlot() {
        xAxis = new NumberAxis();
        xAxis.setLabel(xName);
        yAxis = new NumberAxis();
        yAxis.setLabel(yName);
        sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        sc.setTitle(xName + " vs " + yName);
        mySeries = new XYChart.Series();
        mySeries.setName(yName);
        for (int j = 0; j < myX.size(); j++) {
            mySeries.getData().add(new XYChart.Data(myX.get(j), myY.get(j)));
        }
        sc.getData().addAll(mySeries);
        return sc;
    }

}
