package plotter_hud_utility;

import java.util.ArrayList;

/**
 * This class is used to store data for a desired numerical variable
 * @author Carter Gay
 */
public class DataTracker {

    private ArrayList<Double> myData;
    private String dataName;

    /**
     * DataTracker constructor where the name identifies the variable stored
     * @param name
     */
    public DataTracker(String name) {
        dataName = name;
        myData = new ArrayList<>();
    }

    /**
     * Add a data point to the List
     * @param x
     */
    public void storeData(double x) {
            myData.add(x);
    }

    protected void saveData() {

    }

    /**
     * Get the name of the data being stored
     * @return
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * Get the data values
     * @return
     */
    public ArrayList<Double> getData() {
        return myData;
    }
}