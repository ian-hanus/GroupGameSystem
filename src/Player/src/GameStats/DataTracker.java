package Player.src.GameStats;

import java.util.HashMap;

public class DataTracker {

    private HashMap<Double,Double> myData;
    private String dataName;

    public DataTracker(String name) {
        dataName = name;
        myData = new HashMap<>();
    }

    public void storeData(double x, double y) {
            myData.put(x,y);
    }

    protected void saveData() {

    }

    public String getDataName() {
        return dataName;
    }

    public HashMap<Double,Double> getData() {
        return myData;
    }
}