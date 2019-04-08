package Events.GameEvents;

import Conditionals.Conditional;
import EngineMain.LevelManager;
import Events.Event;

import java.util.List;
import java.util.Set;

public class AddTimer extends GameEvent {
    double myDuration;
    Set<Event> myEventsWhileOn;
    Set<Event> myEventsAfter;
    boolean IsLoop;


    public AddTimer(List<Conditional> conditionals, Set<Event> eventsWhileOn, Set<Event> eventsAfter, double duration, boolean isLoop){
        super(conditionals);
        myDuration = duration;
        myEventsWhileOn = eventsWhileOn;
        myEventsAfter = eventsAfter;
        IsLoop = isLoop;
    }

    @Override
    public void activate(LevelManager gameManager) {
        gameManager.addTimer(myEventsWhileOn, myEventsAfter, myDuration, IsLoop);
    }
}
