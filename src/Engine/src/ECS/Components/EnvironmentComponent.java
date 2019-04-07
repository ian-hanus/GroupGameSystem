package ECS.Components;

public class EnvironmentComponent extends Component{
    private double myGravity;
    private double myVelDampener;
    private double myFriction;

    public EnvironmentComponent(double gravity, double velDampener, double friction) {
        myGravity = gravity;
        myVelDampener = velDampener;
        myFriction = friction;
    }
}
