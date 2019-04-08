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

    public void addTimerSequence(Set<Event> eventsDuringTimer, Set<Event> eventsAfter, double duration, boolean isLoop) {
        myTimers.add(new TimerSequence(eventsDuringTimer, eventsAfter, duration, myCount, isLoop));
    }

    public void updateTimer() {
        for (TimerSequence sequence : myTimers) {
            Timer currentTimer = sequence.getCurrentTimer();
            if (currentTimer.getCount() >= currentTimer.getEndTime()){
                Set<Event> endEvents = currentTimer.getMyEventsAfterTimer();
                activateEvents(endEvents);
                sequence.setNextTimer(myCount);
            }
            else {
                Set<Event> currentEvents = currentTimer.getEventsWhileOn();
                activateEvents(currentEvents);
                currentTimer.increment();
            }
            if (sequence.completed() && sequence.isLoop()) sequence.reset(myCount);
            else myTimers.remove(sequence);
        }
    }

    public void activateEvents(Set<Event> events) {
        for (Event e : events) {
            Event event = e.copy();
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
