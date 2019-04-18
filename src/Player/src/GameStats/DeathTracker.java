package Player.src.GameStats;

import java.util.HashMap;

public class DeathTracker extends DataTracker {

    private HashMap<Double,Double> myDeathTime;
    private HashMap<Double,Double> myDeathPos;

    public DeathTracker () {
        myDeathTime = new HashMap<>();
        myDeathPos = new HashMap<>();
    }

    @Override
    public void storeData(double time, double position, double lives) {
        myDeathTime.put(time,lives);
        myDeathPos.put(position,lives);
    }

    @Override
    // save data in a file to reuse
    protected void saveData() {

    }

    public HashMap<Double,Double> getDeathTime() {
        return myDeathTime;
    }

    public HashMap<Double,Double> getDeathPos() {
        return myDeathPos;
    }

}
