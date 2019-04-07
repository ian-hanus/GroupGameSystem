package ECS.Components;

public class MotionComponent extends Component {
    private double myVel;
    private double myAcc;
    private double myAngle;
    private double[] myDirectionVec;

    public MotionComponent(int velocity, int acceleration, int angle) {
        myVel = velocity;
        myAcc = acceleration;
        myAngle = angle;
        myDirectionVec = new double[2];
        myDirectionVec[0] = Math.cos(Math.toRadians(angle));
        myDirectionVec[1] = Math.sin(Math.toRadians(angle));
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

    public void setDirectionVec(double[] directionVec) {
        this.myDirectionVec = directionVec;
    }

    public double[] getDirectionVec() { return myDirectionVec; }
}
