package Player.src.GameStats;

public abstract class DataTracker {

    public DataTracker() {

    }

    abstract public void storeData(double time, double position, double lives);

    abstract protected void saveData();

}
