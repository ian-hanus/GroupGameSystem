package Components;

public class EnvironmentComponent {
    private double myGravity;
    private double myVelDampener;
    private double myFriction;

    public EnvironmentComponent(double gravity, double velDampener, double friction) {
        myGravity = gravity;
        myVelDampener = velDampener;
        myFriction = friction;
    }
}
