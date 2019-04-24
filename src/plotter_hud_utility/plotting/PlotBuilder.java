package plotter_hud_utility.plotting;

import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to construct plots to dynamically display game variables
 * @author Carter Gay
 */
public class PlotBuilder {

    private XYChart.Series mySeries;
    private ArrayList<Double> myX;
    private ArrayList<Double> myY[];
    private String xName;
    private String yName[];
    private double myWidth;
    private double myHeight;
    private NumberAxis xAxis;
    private NumberAxis yAxis;


    /**
     * PlotBuilder constructor that takes in an x feature and any number of y features
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public PlotBuilder(DataTracker x, List<DataTracker> y, double width, double height) {
        myX = x.getData();
        xName = x.getDataName();
        myY = new ArrayList[y.size()];
        yName = new String[y.size()];
        myWidth = width;
        myHeight = height;
        for (int i = 0; i < y.size(); i++) {
            myY[i]= y.get(i).getData();
            yName[i] = y.get(i).getDataName();
        }
        xAxis = new NumberAxis();
        xAxis.setLabel(xName);
        yAxis = new NumberAxis();
    }

    /**
     * Creates the scatter plot of the passed in x and y features with appropriate titles/labels
     * @return
     */
    public XYChart<Number,Number> createPlot(String plotType) {
        XYChart myPlot;
        if (plotType == "Scatter") {
            myPlot = new ScatterChart<>(xAxis,yAxis);
        }
        else if (plotType == "Line") {
            myPlot = new LineChart<>(xAxis,yAxis);
        }
        else  {
            myPlot = new AreaChart<>(xAxis,yAxis);
        }
        if (myY.length != 0)
            plotData(yAxis, myPlot);
        else
            myPlot.setTitle("Please select y");
        return myPlot;
    }

    private void plotData(NumberAxis yAxis, XYChart<Number, Number> myPlot) {
        String yString = yName[0];
        for (int k = 1; k < yName.length; k++) {
            yString += ", " + yName[k];
        }
        yAxis.setLabel(yString);
        myPlot.setTitle(yString + " vs " + xName);
        for (int i = 0; i < myY.length; i++) {
            mySeries = new XYChart.Series();
            mySeries.setName(yName[i]);
            for (int j = 0; j < myX.size(); j++) {
                mySeries.getData().add(new XYChart.Data(myX.get(j), myY[i].get(j)));
            }
            myPlot.getData().addAll(mySeries);
        }
    }

}
