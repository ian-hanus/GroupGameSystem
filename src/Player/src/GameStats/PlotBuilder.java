package Player.src.GameStats;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class PlotBuilder {

    private XYChart.Series series1;
    private ScatterChart<Number,Number> sc;
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    public PlotBuilder() {

    }

    private void createPlot() {
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Time");
        yAxis.setLabel("Position");
        sc.setTitle("Position Tracker");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("X Position");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Y Position");
        sc.getData().addAll(series1, series2);
        myLeftPanel.addRow(sc);
    }


}
