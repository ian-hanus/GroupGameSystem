package ECS.Components;

public class MotionComponent extends Component {
    private int myVel;
    private int myAcc;
    private int myAngle;

    public MotionComponent(int velocity, int acceleration, int angle) {
        myVel = velocity;
        myAcc = acceleration;
        myAngle = angle;
    }
}
