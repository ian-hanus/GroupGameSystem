<<<<<<< HEAD:src/Engine/src/Parser/GameInformation.java
package Parser;
=======

package Main;
>>>>>>> f69bbaed5ff52acfe6b33c6cec697d06d4358748:src/Engine/src/DataManager/Main/GameInformation.java

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
