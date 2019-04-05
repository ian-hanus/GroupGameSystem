package Events.GameEvents;

import Conditionals.Conditional;
import EngineMain.LevelManager;
import Events.Event;

import java.util.List;
import java.util.Set;

public class AddTimer extends GameEvent {
    double myDuration;
    Set<Event> myEvents;

    public AddTimer(List<Conditional> conditionals, double duration, Set<Event> events){
        super(conditionals);
        myDuration = duration;
        myEvents = events;
    }

    @Override
    public void activate(LevelManager gameManager) {
        gameManager.addTimer(myDuration + System.currentTimeMillis(), myEvents);
    }
}
