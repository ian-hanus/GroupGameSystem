package EngineMain;

import Events.Event;

import java.util.List;
import java.util.Set;

public class Timer {
    double myCount;
    double myDuration;
    double myStartTime;
    List<Event> myEventsWhileOn;
    List<Event> myEventsAfterTimer;

    public Timer(List<Event> eventsWhileOn, List<Event> eventsAfterTimer, double duration){
        myCount = 0;
        myStartTime = 0;
        myDuration = duration;
        myEventsWhileOn = eventsWhileOn;
        myEventsAfterTimer = eventsAfterTimer;
    }

    public Timer(List<Event> eventsWhileOn, List<Event> eventsAfterTimer, double duration, double currentCount){
        myCount = currentCount;
        myStartTime = currentCount;
        myDuration = duration;
        myEventsWhileOn = eventsWhileOn;
        myEventsAfterTimer = eventsAfterTimer;
    }

    protected void setCount(double currentCount){myCount = currentCount;}

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

    protected List<Event> getEventsWhileOn(){
        return myEventsWhileOn;
    }

    protected List<Event> getMyEventsAfterTimer(){
        return myEventsAfterTimer;
    }

}
