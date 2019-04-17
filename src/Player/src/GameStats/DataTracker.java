package Player.src.GameStats;

public abstract class DataTracker {

    public DataTracker() {

    }

    abstract public void storeData(double time, double position);

    abstract protected void saveData();

}
