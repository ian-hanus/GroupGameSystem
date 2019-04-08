package ECS.Components;

public class MotionComponent extends Component {
    private double myXVelocity;
    private double myYVelocity;
    private double myXAcceleration;
    private double myYAcceleration;
    private double myAngle;

    public MotionComponent(double xVelocity, double yVelocity, double xAcceleration, double yAcceleration, double angle) {
        this.myXVelocity = xVelocity;
        this.myYVelocity = yVelocity;
        this.myXAcceleration = xAcceleration;
        this.myYAcceleration = yAcceleration;
        this.myAngle = angle;
    }

    public double getXVelocity() {
        return myXVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.myXVelocity = xVelocity;
    }

    public double getYVelocity() {
        return myYVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.myYVelocity = yVelocity;
    }

    public double getXAcceleration() {
        return myXAcceleration;
    }

    public void setXAcceleration(double xAcceleration) {
        this.myXAcceleration = xAcceleration;
    }

    public double getYAcceleration() {
        return myYAcceleration;
    }

    public void setYAcceleration(double yAcceleration) {
        this.myYAcceleration = yAcceleration;
    }

    public double getAngle() {
        return myAngle;
    }

    public void setAngle(double angle) {
        this.myAngle = angle;
    }
}
