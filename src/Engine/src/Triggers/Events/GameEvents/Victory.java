package Triggers.Events.GameEvents;

import Triggers.Conditionals.Conditional;
import Controller.LevelManager;
import Triggers.Events.Event;

import java.util.List;

public class Victory extends GameEvent{

    public Victory(List<Conditional> conditionals){
        super(conditionals);
    }

    @Override
    public void activate(LevelManager levelManager) {
        levelManager.setLevelPass();
    }

    @Override
    public Event copy(){
        return new Victory(copyConditionals());
    }
}
