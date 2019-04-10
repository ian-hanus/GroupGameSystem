package Engine.src.Triggers.Events.GameEvents;

import Engine.src.Triggers.Conditionals.Conditional;
import Engine.src.Controller.LevelManager;
import Engine.src.Triggers.Events.Event;

import java.util.List;

public abstract class GameEvent extends Event {

    public GameEvent(){
        super();
    }

    public GameEvent(List<Conditional> conditionals){
        super(conditionals);
    }

    public abstract void activate(LevelManager gameManager);
}
