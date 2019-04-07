package ECS;

import ECS.Components.BasicComponent;
import ECS.Components.Component;
import ECS.Components.MotionComponent;

import java.util.HashMap;
import java.util.List;

public class EntityManager {
    private HashMap<Integer, List<Component>> myEntityMap;
    private int myFramesPerSecond;

    public EntityManager(int framesPerSecond) {
        myFramesPerSecond = framesPerSecond;
        myEntityMap = new HashMap<>();
    }

    public void kill(int entityID) {
        myEntityMap.remove(entityID);
    }

    public void create(int entityID, List<Component> components) {
        //TODO error checking
        myEntityMap.put(entityID, components);
    }

    public void addComponent(int entityID, Component component) {
        try {
            var components = getAllComponents(entityID);
            components.add(component);
            myEntityMap.put(entityID, components);
        }
        catch (NoEntityException e) {

        }
    }

    public void move(int entityID) {
        try {
            var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
            var basicComponent = (BasicComponent) getComponent(entityID, BasicComponent.class);
            double currXPos = basicComponent.getX();
            double currYPos = basicComponent.getY();
            double[] direction = motionComponent.getDirectionVec();
            basicComponent.setX(currXPos + direction[0]);
            basicComponent.setY(currYPos + direction[1]);

            //TODO update velocity based on accel
        }
        catch (NoEntityException | NoComponentException e) {

        }
    }

    public void adjustDirection(int entityID, double delta) {
        try {
            var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
            double angle = motionComponent.getAngle();
            angle += delta;
            double[] directionVec = calculateDirection(angle);
            motionComponent.setAngle(angle);
            motionComponent.setDirectionVec(directionVec);
        }
        catch (NoEntityException | NoComponentException e) {

        }
    }

    public void setDirection(int entityID, double angle){
        try {
            var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
            double[] directionVec = calculateDirection(angle);
            motionComponent.setAngle(angle);
            motionComponent.setDirectionVec(directionVec);
        }
        catch (NoEntityException | NoComponentException e) {

        }
    }

    public void stop(int entityID) {
        try {
            var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
            motionComponent.setVel(0);
        }
        catch (NoEntityException | NoComponentException e) {

        }
    }

    //public void addPowerup - later

    private double[] calculateDirection(double angle){
        double[] directionVec = new double[2];
        directionVec[0] = Math.cos(Math.toRadians(angle));
        directionVec[1] = Math.sin(Math.toRadians(angle));
        return directionVec;
    }

    //can return null
    private Component getComponent(int entityID, Class<? extends Component> componentClass) throws NoEntityException, NoComponentException {
        var components = getAllComponents(entityID);
        for (Component component : components) {
            if (componentClass.isInstance(component))
                return component;
        }
        return null;
    }

    private List<Component> getAllComponents(int entityID) throws NoEntityException {
        List<Component> components = myEntityMap.get(entityID);
        if (components == null)
            throw new NoEntityException("Entity " + entityID + " does not exist");
        return components;
    }
}
