package triggers.Events.GameEvents;

import triggers.Conditionals.Conditional;
import controller.LevelManager;

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
