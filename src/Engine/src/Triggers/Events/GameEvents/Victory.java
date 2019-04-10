package Triggers.Events.GameEvents;

import Triggers.Conditionals.Conditional;
import Controller.LevelManager;

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
