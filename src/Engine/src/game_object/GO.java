package game_object;

import game_object.environment_components.EnvironmentComponent;
import game_object.health_components.HealthComponent;
import game_object.physics_components.PhysicsComponent;
import game_object.timer_components.TimerComponenent;

import java.awt.geom.Point2D;

/**
 * TODO call this GameObject later
 */
public class GO {
    protected String myDefaultImageName;
    private String myCurrentImageName;
    private Point2D myPosition;
    private String myType;
    private int myZIndex;
    private boolean myImpassible; //in all GO???
    private boolean isUser; //how to better mark a GO as the user...?

    private PhysicsComponent myPhysicsComponent;
    private HealthComponent myHealthComponent;
    private TimerComponenent myTimerComponent;
    private EnvironmentComponent myEnvironmentComponent;

    public GO(PhysicsComponent pc, HealthComponent hc, TimerComponenent tc, EnvironmentComponent ec) {
        myPhysicsComponent = pc;
        myHealthComponent = hc;
        myTimerComponent = tc;
        myEnvironmentComponent = ec;
    }

    public PhysicsComponent getPhysicsComponent() {
        return myPhysicsComponent;
    }

    public HealthComponent getHealthComponent() {
        return myHealthComponent;
    }

    public TimerComponenent getTimerComponent() {
        return myTimerComponent;
    }

    public EnvironmentComponent getEnvironmentComponent() {
        return myEnvironmentComponent;
    }

    public boolean isType(String type) {
        return myType.equals(type);
    }

    /**
     * Method to be used by Player
     * @return x, y coordinates
     */
    public Point2D getPosition() {
        return myPosition;
    }

    /**
     * Method to be used by Player
     * @return String of image file path
     */
    public String getImageName() {
        return myCurrentImageName;
    }
}
