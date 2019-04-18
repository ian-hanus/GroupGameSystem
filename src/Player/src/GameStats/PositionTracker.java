package Player.src.GameStats;

import java.util.HashMap;

public class PositionTracker extends DataTracker {

    private HashMap<Double,Double> myPositions;

    public PositionTracker () {
        myPositions = new HashMap<>();
    }

    public void storeData(double time, double position) {
            myPositions.put(time,position);
    }

    @Override
    // save data in a file to reuse
    protected void saveData() {

    }

    public HashMap<Double,Double> getData() {
        return myPositions;
    }
}
