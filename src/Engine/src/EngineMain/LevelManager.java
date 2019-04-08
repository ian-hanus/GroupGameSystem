package EngineMain;

import ECS.EntityManager;
import Events.Event;
import Events.GameEvents.GameEvent;
import Events.ObjectEvents.ObjectEvent;
import GameObjects.ObjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LevelManager {
    private boolean levelPassed;
    private List<TimerSequence> myTimers;
    private EntityManager myEntityManager;
    double myCount;

    public LevelManager(List<TimerSequence> timers, EntityManager entityManager, double count){
        levelPassed = false;
        myEntityManager = entityManager;
        myTimers = timers;
        myCount = count;
    }

    public void addSequence(Map<Integer, List<Event>> eventsWhileOn, Map<Integer, List<Event>> eventsAfter,
                            Map<Integer, Double> durations, boolean isLoop) {
        List<Timer> timerList = new ArrayList<>();
        for(Integer key: eventsWhileOn.keySet()){
            timerList.add(new Timer(eventsWhileOn.get(key), eventsAfter.get(key), durations.get(key)));
        }
        myTimers.add(new TimerSequence(timerList, isLoop));
    }

    public void updateTimer() {
        for (TimerSequence sequence : myTimers) {
            Timer currentTimer = sequence.getCurrentTimer();
            if (currentTimer.getCount() >= currentTimer.getEndTime()){
                currentTimer.activateEvents(currentTimer.getMyEventsAfterTimer(), myEntityManager, this);
                sequence.setNextTimer(myCount);
            }
            else {
                currentTimer.activateEvents(currentTimer.getEventsWhileOn(), myEntityManager, this);
                currentTimer.increment();
            }
            if (sequence.completed() && sequence.isLoop()) sequence.reset(myCount);
            else myTimers.remove(sequence);
        }
    }

    public void setLevelPass() {
        levelPassed = true;
    }

}
