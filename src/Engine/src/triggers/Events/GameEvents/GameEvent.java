package triggers.Events.GameEvents;

import triggers.Conditionals.Conditional;
import controller.LevelManager;
import triggers.Events.Event;

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
