package Initial;

import DataManager.Level;

import java.util.List;
import java.util.Map;

public class GameInformation {
    private List<Level> myLevels;
    private Map<String, Integer> myScoreboard;
    private Initial.Splash mySplash;

    public GameInformation(List<Level> levels, Map<String, Integer> scoreboard, Initial.Splash splash){
        myLevels = levels;
        myScoreboard = scoreboard;
        mySplash = splash;
    }
}
