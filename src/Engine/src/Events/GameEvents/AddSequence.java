package Events.GameEvents;

import Conditionals.Conditional;
import EngineMain.LevelManager;
import Events.Event;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddSequence extends GameEvent {
    Map<Integer, Double> myDurations;
    Map<Integer, List<Event>> myEventsWhileOn;
    Map<Integer, List<Event>> myEventsAfter;
    boolean IsLoop;


    public AddSequence(List<Conditional> conditionals, Map<Integer, List<Event>> eventsWhileOn, Map<Integer, List<Event>> eventsAfter,
                       Map<Integer, Double> durations, boolean isLoop){
        super(conditionals);
        myDurations = durations;
        myEventsWhileOn = eventsWhileOn;
        myEventsAfter = eventsAfter;
        IsLoop = isLoop;
    }

    @Override
    public void activate(LevelManager levelManager) {
        levelManager.addSequence(myEventsWhileOn, myEventsAfter, myDurations, IsLoop);
    }
}
