package Responses;

import GameObjects.GameObject;

public class DirectionChange implements Response{
    private double myAdjustmentAngle;

    public DirectionChange(double angle){
        myAdjustmentAngle = angle;
    }

    @Override
    public void respond(GameObject obj){
        //obj.adjustDirection(myAdjustmentAngle);
    }
}
