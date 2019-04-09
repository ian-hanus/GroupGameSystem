package EngineMain;

import Events.Event;

import java.util.List;
import java.util.Set;

public class TimerSequence {
    List<Timer> mySequence;
    int TimerIndex;
    boolean IsLoop;

    TimerSequence(List<Timer> sequence, boolean isLoop){
        mySequence = sequence;
        TimerIndex = 0;
        IsLoop = isLoop;
    }

    protected void reset(double currentCount){
        TimerIndex = 0;
        mySequence.get(TimerIndex).setCount(currentCount);
    }

    protected boolean isLoop() {
        return IsLoop;
    }


    protected Timer getCurrentTimer() {
        return mySequence.get(TimerIndex);
    }

    protected void setNextTimer(double currentCount) {
        TimerIndex++;
        mySequence.get(TimerIndex).setCount(currentCount);
    }

    public boolean completed() {
        return (TimerIndex == mySequence.size());
    }
}
