package GameObjects;
import GameObjects.GameObject;

public class Block extends GameObject {

    private int numTicksToChangeDir;
    private int numTicksSinceChangeDir = 0;

    public Block(double xPos, double yPos, double health, double height, double width,
                     double angle, double velocity, String objectName, int zIndex, double[] direction, String filename,
                 int numTicksToChangeDir) {
        super(xPos, yPos, health, height, width, angle, velocity, objectName, zIndex, direction, filename);
        this.numTicksToChangeDir = numTicksToChangeDir;
    }

    // TODO Refactor the next two methods to avoid duplicate code with NPC

    public void updatePosition() {
        super.updatePosition();

        numTicksSinceChangeDir++;

        if (numTicksSinceChangeDir == numTicksToChangeDir) {
            changeDirection();
            numTicksSinceChangeDir = 0;
        }
    }

    private void changeDirection() {
        double[] nextDirection = getDirection();
        double nextAngle = getAngle();

        nextDirection[0] *= -1;
        setDirection(nextDirection);

        setAngle(nextAngle + Math.PI);
    }

}
