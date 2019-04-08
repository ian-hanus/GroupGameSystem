package ECS.Components;

/**
 * Assumes time step is 1 (multiplies velocity by 1 when returning new position or acceleration by 1 when updating velocity)
 */
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

    public void updateVelocity() {
        myXVelocity += myXAcceleration;
        myYVelocity += myYAcceleration;
    }

    public double getNewX(double x) {
        return x + myXVelocity;
    }

    public double getNewY(double y) {
        return y + myYVelocity;
    }

    //TODO stuff below needs to be converted to x, y components
    public void adjustDirection(double delta) {
        myAngle += delta;
        double[] directionVec = calculateDirection(myAngle);
        motionComponent.setDirectionVec(directionVec);//FIXME
    }

    public void setDirection(double angle) {
        myAngle = angle;
        double[] directionVec = calculateDirection(angle);
        motionComponent.setDirectionVec(directionVec);//FIXME
    }


    private double[] calculateDirection(double angle){
        double[] directionVec = new double[2];
        directionVec[0] = Math.cos(Math.toRadians(angle));
        directionVec[1] = Math.sin(Math.toRadians(angle));
        return directionVec;
    }
}
