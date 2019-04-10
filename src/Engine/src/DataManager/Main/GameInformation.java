<<<<<<< HEAD:src/DataManager/src/GameInformation.java

=======
package Main;
>>>>>>> e8f3cab84551a57c141de56be919e2df8395ea17:src/Engine/src/DataManager/Main/GameInformation.java

import java.util.List;
import java.util.Map;

public class GameInformation {
    private List<Level> myLevels;
    private Map<String, Integer> myScoreboard;
    private Splash mySplash;

    public GameInformation(List<Level> levels, Map<String, Integer> scoreboard, Splash splash){
        myLevels = levels;
        myScoreboard = scoreboard;
        mySplash = splash;
    }
}
