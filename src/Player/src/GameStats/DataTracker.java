package Player.src.GameStats;

import java.util.HashMap;

public class DataTracker {

    private HashMap<Double,Double> myData;

    public DataTracker() {
        myData = new HashMap<>();
    }

    public void storeData(double x, double y) {
            myData.put(x,y);
    }

    protected void saveData() {

    }

    public HashMap<Double,Double> getData() {
        return myData;
    }
}