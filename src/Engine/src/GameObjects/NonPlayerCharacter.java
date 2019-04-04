package Engine.src.GameObjects;

public class NonPlayerCharacter extends Character {

    private boolean jumps;
    private boolean changesDirection;
    private int numTicksToJump;
    private int numTicksToChangeDir;
    private int numTicksSinceJump = 0;
    private int numTicksSinceChangeDir = 0;

    public NonPlayerCharacter(double xPos, double yPos, double health, double height, double width,
                              double angle, String objectName, int zIndex, double[] direction, String filename,
                              double jumpSpeed, int numTicksToJump, int numTicksToChangeDir) {
        super(xPos, yPos, health, height, width, angle, objectName, zIndex, direction, filename, jumpSpeed);
        this.numTicksToJump = numTicksToJump;
        this.numTicksToChangeDir = numTicksToChangeDir;
    }

    public void updatePosition() {
        super.updatePosition();

        numTicksSinceJump++;
        numTicksSinceChangeDir++;

        if (numTicksSinceJump == numTicksToJump) {
            jump();
            numTicksSinceJump = 0;
        }
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
