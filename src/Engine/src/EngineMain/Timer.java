package EngineMain;

import Events.Event;

import java.util.Set;

public class Timer {
    double myCount;
    double myDuration;
    double myStartTime;
    Set<Event> myEventsWhileOn;
    Set<Event> myEventsAfterTimer;
    boolean IsLoop;

    public Timer(Set<Event> eventsWhileOn, Set<Event> eventsAfterTimer, double duration, boolean isLoop){
        myCount = 0;
        myStartTime = 0;
        myDuration = duration;
        IsLoop = isLoop;
    }

    public Timer(Set<Event> eventsWhileOn, Set<Event> eventsAfterTimer, double duration, double currentCount, boolean isLoop){
        myCount = currentCount;
        myStartTime = currentCount;
        myDuration = duration;
        IsLoop = isLoop;
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
}
