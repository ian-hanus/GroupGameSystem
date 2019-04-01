package Responses;

public class Deflect extends DirectionChange{

    private double myAdjustmentAngle;

    public Deflect(double angle){
        super(angle);
        myAdjustmentAngle=180;
    }

    public void respond(GameObject obj){
        obj.adjustDirection(myAdjustmentAngle);
    }
}
