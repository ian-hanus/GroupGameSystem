package Events.GameEvents;

import Conditionals.Conditional;
import EngineMain.LevelManager;

import java.util.List;

public class Victory extends GameEvent{

    public Victory(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(LevelManager levelManager) {
        levelManager.setLevelPass();
    }
}
