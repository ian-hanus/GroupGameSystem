package Player.src.GameStats;

import java.util.ArrayList;
import java.util.HashMap;

public class DataTracker {

    private ArrayList<Double> myData;
    private String dataName;

    public DataTracker(String name) {
        dataName = name;
        myData = new ArrayList<>();
    }

    public void storeData(double x) {
            myData.add(x);
    }

    protected void saveData() {

    }

    public String getDataName() {
        return dataName;
    }

    public ArrayList<Double> getData() {
        return myData;
    }
}