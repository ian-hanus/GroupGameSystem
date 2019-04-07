package Components;

public class DimensionsComponent {
    private double myWidth;
    private double myHeight;

    public DimensionsComponent(double width, double height) {
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
