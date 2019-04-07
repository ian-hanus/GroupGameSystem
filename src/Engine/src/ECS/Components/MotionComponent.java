package ECS.Components;

public class MotionComponent extends Component {
    private double myVel;
    private double myAcc;
    private double myAngle;

    public MotionComponent(int velocity, int acceleration, int angle) {
        myVel = velocity;
        myAcc = acceleration;
        myAngle = angle;
    }

    public double getVel() {
        return myVel;
    }

    public double getAcc() {
        return myAcc;
    }

    public double getAngle() {
        return myAngle;
    }

    public void setVel(double vel) {
        this.myVel = vel;
    }

    public void setAcc(double acc) {
        this.myAcc = acc;
    }

    public void setAngle(double angle) {
        this.myAngle = angle;
    }

}
