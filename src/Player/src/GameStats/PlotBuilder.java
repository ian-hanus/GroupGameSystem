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
    private ArrayList<Double> myY[];
    private String xName;
    private String yName[];

    public PlotBuilder(DataTracker x, ArrayList<DataTracker> y) {
        myX = x.getData();
        xName = x.getDataName();
        myY = new ArrayList[y.size()];
        yName = new String[y.size()];
        for (int i = 0; i < y.size(); i++) {
            myY[i]= y.get(i).getData();
            yName[i] = y.get(i).getDataName();
        }
    }

    public ScatterChart<Number,Number> createPlot() {
        xAxis = new NumberAxis();
        xAxis.setLabel(xName);
        yAxis = new NumberAxis();
        yAxis.setLabel(yName[0]);
        sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        sc.setTitle(xName + " vs " + yName[0]);
        for (int i = 0; i < myY.length; i++) {
            mySeries = new XYChart.Series();
            mySeries.setName(yName[i]);
            for (int j = 0; j < myX.size(); j++) {
                mySeries.getData().add(new XYChart.Data(myX.get(j), myY[i].get(j)));
            }
            sc.getData().addAll(mySeries);
        }
        return sc;
    }

}
