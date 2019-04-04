package EngineMain;

import Events.Event;
import Events.GameEvents.GameEvent;
import Events.ObjectEvents.ObjectEvent;
import GameObjects.ObjectManager;

import java.util.Map;
import java.util.Set;

public class LevelManager {
    private boolean levelPassed;
    private Map<Double, Set<Event>> myTimerMap;

    public LevelManager(Map<Double[], Set<Event>> myTimerMap){
        levelPassed = false;
    }

    public void addTimer(double timer, Set<Event> events) {
        myTimerMap.put(timer, events);
    }

    public void checkTimer(Double timer){
        if (System.currentTimeMillis()>= timer){
            Set<Event> events = myTimerMap.get(timer);
            for(Event event : events){
                if(event.conditionsSatisfied()) {
                    if (event instanceof ObjectEvent) ((ObjectEvent) event).activate(myObjectManager);
                    else if (event instanceof GameEvent) ((GameEvent) event).activate(this);
                }
            }
            myTimerMap.remove(timer);
        }
    }


}
