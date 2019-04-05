package GameObjects;

import java.awt.geom.Point2D;

abstract class MovableObject {
    private final boolean IS_IMMOBILE;
    private Point2D myAcceleration;
    private Point2D myVelocity;
    private Point2D myPosition;

    MovableObject(double posX, double posY, boolean isImmobile) {
        myPosition = new Point2D.Double();
        myPosition.setLocation(posX, posY);
        IS_IMMOBILE = isImmobile;
        myVelocity = new Point2D.Double();
        myAcceleration = new Point2D.Double();
    }

    public double getX() {
        return myPosition.getX();
    }

    public double getY() {
        return myPosition.getY();
    }

    void move(double timeStep) {
        if (IS_IMMOBILE)
            return;

        double newVelX = myVelocity.getX() + timeStep * myAcceleration.getX();
        double newVelY = myVelocity.getY() + timeStep * myAcceleration.getY();
        myVelocity.setLocation(newVelX, newVelY);

        double newPosX = myPosition.getX() + timeStep * myVelocity.getX();
        double newPosY = myPosition.getY() + timeStep * myVelocity.getY();
        myPosition.setLocation(newPosX, newPosY);
    }

    void setAccelX(double accel) {
        myAccelX = accel;
    }

    void setAccelY(double accel) {
        myAccelY = accel;
    }

    boolean getIsImmobile() {
        return IS_IMMOBILE;
    }
}
