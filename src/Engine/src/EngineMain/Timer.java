package EngineMain;

import Events.Event;

import java.util.Set;

public class Timer {
    double myCount;
    double myDuration;
    double myStartTime;
    Set<Event> myEventsWhileOn;
    Set<Event> myEventsAfterTimer;

    public Timer(Set<Event> eventsWhileOn, Set<Event> eventsAfterTimer, double duration){
        myCount = 0;
        myStartTime = 0;
        myDuration = duration;
    }

    public Timer(Set<Event> eventsWhileOn, Set<Event> eventsAfterTimer, double duration, double currentCount){
        myCount = currentCount;
        myStartTime = currentCount;
        myDuration = duration;
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

}
