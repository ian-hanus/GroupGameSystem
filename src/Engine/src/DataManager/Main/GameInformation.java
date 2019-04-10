package Main;

import DataManager.Level;

import java.util.List;
import java.util.Map;

public class GameInformation {
    private List<Level> myLevels;
    private Map<String, Integer> myScoreboard;
    private Main.Splash mySplash;

    public GameInformation(List<Level> levels, Map<String, Integer> scoreboard, Main.Splash splash){
        myLevels = levels;
        myScoreboard = scoreboard;
        mySplash = splash;
    }
}
