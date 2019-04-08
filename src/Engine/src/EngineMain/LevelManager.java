package EngineMain;

import Events.Event;
import Events.GameEvents.GameEvent;
import Events.ObjectEvents.ObjectEvent;
import GameObjects.ObjectManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LevelManager {
    private boolean levelPassed;
    private List<TimerSequence> myTimers;
    private ObjectManager myObjectManager;
    double myCount;

    public LevelManager(List<TimerSequence> timers, ObjectManager objectManager, double count){
        levelPassed = false;
        myObjectManager = objectManager;
        myTimers = timers;
        myCount = count;
    }

    public void addTimer(Set<Event> eventsDuringTimer, Set<Event> eventsAfter, double duration, boolean isLoop) {
        myTimers.add(new Timer(eventsDuringTimer, eventsAfter, duration, myCount, isLoop));
    }

    public void updateTimer() {
        for (TimerSequence sequence : myTimers) {
            sequence.update(myCount);
            if (sequence.completed() && sequence.isLoop()) sequence.remove();
            else myTimers.remove(sequence);
        }
    }

    public void activateEvents(Set<Event> events) {
        for (Event event : events) {
            if (event.conditionsSatisfied(myObjectManager)) {
                if (event instanceof ObjectEvent) ((ObjectEvent) event).activate(myObjectManager);
                else if (event instanceof GameEvent) ((GameEvent) event).activate(this);
            }

        }
    }

    public void setLevelPass() {
        levelPassed = true;
    }

}
