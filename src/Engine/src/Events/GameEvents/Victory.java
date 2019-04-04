package Events.GameEvents;

import EngineMain.LevelManager;

public class Victory extends GameEvent{

    @Override
    public void activate(LevelManager levelManager) {
        levelManager.setLevelPass(true);
    }
}
