package Events.GameEvents;

import Conditionals.Conditional;
import EngineMain.LevelManager;
import Events.Event;

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
