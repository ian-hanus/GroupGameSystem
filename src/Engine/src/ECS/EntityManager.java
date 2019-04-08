package ECS;

import ECS.Components.BasicComponent;
import ECS.Components.Component;
import ECS.Components.MotionComponent;

import java.util.HashMap;
import java.util.List;

public class EntityManager {
    private HashMap<Integer, List<Component>> myEntityMap;

    public EntityManager() {
        myEntityMap = new HashMap<>();
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

    //can return null
    //if we want this to be O(1) lookup instead of O(M) [M is number of components in entity], then must convert
    //the List of components for an entity to a map of component classes to components
    public <T extends Component> T getComponent(int entityID, Class<T> componentClass) throws NoEntityException {
        var components = getAllComponents(entityID);
        for (Component component : components) {
            if (componentClass.isInstance(component))
                return (T) component;
        }
        return null;
    }

    private List<Component> getAllComponents(int entityID) throws NoEntityException {
        List<Component> components = myEntityMap.get(entityID);
        if (components == null)
            throw new NoEntityException("Entity " + entityID + " does not exist");
        return components;
    }

    public void die(int entityID) {
        myEntityMap.remove(entityID);
    }

    public void create(int entityID, List<Component> components) {
        //TODO error checking
        myEntityMap.put(entityID, components);
    }

    public void move(int entityID) {
        try {
            var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
            var basicComponent = (BasicComponent) getComponent(entityID, BasicComponent.class);

            motionComponent.updateVelocity();
            double newX = motionComponent.getNewX(basicComponent.getX());
            double newY = motionComponent.getNewY(basicComponent.getY());
            basicComponent.setX(newX);
            basicComponent.setY(newY);
        }
        catch (NoEntityException | NoComponentException e) {
            System.out.println("Can't move an entity without a motion component.");
        }
    }

    public void adjustDirection(int entityID, double delta) {
        try {
            var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
            motionComponent.adjustDirection(delta);
        }
        catch (NoEntityException | NoComponentException e) {
            System.out.println("Can't adjust the direction of an entity without a motion component.");
        }
    }

    public void setDirection(int entityID, double angle){
        try {
            var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
            motionComponent.setDirection(angle);
        }
        catch (NoEntityException | NoComponentException e) {
            System.out.println("Can't set the direction of an entity without a motion component.");
        }
    }

    //public void addPowerup - later
}
