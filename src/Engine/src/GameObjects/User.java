package GameObjects;

public class User extends GameObject {
    public User(double xPos, double yPos, double health, double height, double width,
                      double angle, String objectName, int zIndex, double[] direction, String filename) {
        super(xPos, yPos, health, height, width, angle, objectName, zIndex, direction, filename);
    }
}
