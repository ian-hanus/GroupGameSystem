package EngineMain;

import Events.Event;

import java.sql.Time;
import java.util.List;
import java.util.Set;

public class TimerSequence {
    List<Timer> mySequence;
    int TimerIndex;

    TimerSequence(List<Timer> sequence){
        mySequence = sequence;
        TimerIndex = 0;
    }

    protected void increment(){
        myCount++;
    }

    protected double getEndTime() {
        return myStartTime + myDuration;
    }

    protected void reset(){
        myStartTime = myCount;
    }

    protected double getCount() {
        return myCount;
    }

    protected Set<Event> getEventsWhileOn(){
        return myEventsWhileOn;
    }

    protected Set<Event> getMyEventsAfterTimer(){
        return myEventsAfterTimer;
    }

    public boolean isLoop() {
        return IsLoop;
    }

    public void update(double myCount) {
        Timer currentTimer = mySequence.get(TimerIndex);
        if (currentTimer.getCount() >= currentTimer.getEndTime()){
            Set<Event> endEvents = currentTimer.getMyEventsAfterTimer();
            activateEvents(endEvents);
            if (timer.isLoop()) timer.reset();
            else myTimers.remove(timer);
        }
        else {
            Set<Event> currentEvents = timer.getEventsWhileOn();
            activateEvents(currentEvents);
            timer.increment();
        }
    }
}
