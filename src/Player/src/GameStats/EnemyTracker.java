package Player.src.GameStats;

import java.util.HashMap;

public class EnemyTracker extends DataTracker {

    private HashMap<Double,Double> myEnemyCount;

    public EnemyTracker () {
        myEnemyCount = new HashMap<>();
    }

    public void storeData(double time, double position, double alive) {
        myEnemyCount.put(time,alive);
    }

    @Override
    // save data in a file to reuse
    protected void saveData() {

    }

    public HashMap<Double,Double> getEnemyCount() {
        return myEnemyCount;
    }

}