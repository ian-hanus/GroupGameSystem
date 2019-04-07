package ECS.Components;

import java.io.File;

public class BasicComponent extends Component {
    private double myWidth;
    private double myHeight;
    private double myXPos;
    private double myYPos;
    private File myFile;

    public BasicComponent(File file, double xPos, double yPos, double width, double height) {
        myFile = file;
        myXPos = xPos;
        myYPos = yPos;
        myWidth = width;
        myHeight = height;
    }

    public double getWidth() {
        return myWidth;
    }

    public double getHeight() {
        return myHeight;
    }

    public void setWidth(double myWidth) {
        this.myWidth = myWidth;
    }

    public void setHeight(double myHeight) {
        this.myHeight = myHeight;
    }
}
