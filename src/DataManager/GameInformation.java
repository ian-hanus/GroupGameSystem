package DataManager;

import java.util.List;
import java.util.Map;

public class GameInformation {
    private List<Level> myLevels;
    private Map<String, Integer> myScoreboard;
    private Splash mySplash;
    private Map<List<GameObject>, String> myInteractions;

    public GameInformation(List<Level> levels, Map<String, Integer> scoreboard, Splash splash, Map<List<GameObject>, String> interactions){
        myLevels = levels;
        myScoreboard = scoreboard;
        mySplash = splash;
        myInteractions = interactions;
    }
}
