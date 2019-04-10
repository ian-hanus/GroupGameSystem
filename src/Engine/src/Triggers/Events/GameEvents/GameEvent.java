package Triggers.Events.GameEvents;

import Triggers.Conditionals.Conditional;
import Controller.LevelManager;
import Triggers.Events.Event;

import java.util.List;

public abstract class GameEvent extends Event {

    public GameEvent(){
        super();
    }

    public GameEvent(List<Conditional> conditionals){
        super(conditionals);
    }

    public abstract void activate(LevelManager gameManager);

    @Override
    public abstract Event copy();
}
