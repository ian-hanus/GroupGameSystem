package components.immutable_basic_component;

import components.BasicComponent;

import java.io.File;

public class ImmutableBasicComponent {
    private BasicComponent myBasicComponent;

    public ImmutableBasicComponent(BasicComponent basicComponent) {
        myBasicComponent = basicComponent;
    }

    public double getWidth() {
        return myBasicComponent.getWidth();
    }

    public double getHeight() {
        return myBasicComponent.getHeight();
    }

    public double getX() { return myBasicComponent.getX(); }

    public double getY() { return myBasicComponent.getY(); }

    public File getMyFile() {
        return myBasicComponent.getMyFile();
    }

    public int getMyZIndex() {
        return myBasicComponent.getMyZIndex();
    }
}
