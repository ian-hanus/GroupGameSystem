package Responses;

public class DirectionChange extends Response{
    private double myAdjustmentAngle;

    public DirectionChange(double angle){
        myAdjustmentAngle=angle;
    }

    public void respond(GameObject obj){
        obj.adjustDirection(myAdjustmentAngle);
    }
}
