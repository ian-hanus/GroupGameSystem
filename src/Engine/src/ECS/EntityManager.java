package ECS;

import ECS.Components.BasicComponent;
import ECS.Components.Component;
import ECS.Components.HealthComponent;
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

    public void adjustHealth(int entityID, int delta) {
        try {
            var healthComponent = (HealthComponent) getComponent(entityID, HealthComponent.class);
            int currentHealth = healthComponent.getHealth();
            int maxHealth = healthComponent.getMaxHealth();
            if (healthComponent.getMaxHealth() == 0) {
                healthComponent.setHealth(currentHealth + delta);
            }
            else {
                int newHealth = Math.min(currentHealth + delta, maxHealth);
                healthComponent.setHealth(newHealth);
            }
        }
        catch (NoEntityException e) {
            System.out.println("Can't adjust the health of an entity without a health component.");
        }
    }

    public void setHealth(int entityID, int health) {
        try {
            var healthComponent = (HealthComponent) getComponent(entityID, HealthComponent.class);
            int maxHealth = healthComponent.getMaxHealth();
            if (health < maxHealth) {
                healthComponent.setHealth(health);
            }
            //is it necessary to throw an error or just set it to the max health?
            else {
                healthComponent.setHealth(maxHealth);
            }
        }
        catch (NoEntityException e) {

        }
    }

    //Not sure if done here and how states would be referenced non-specifically
    public void changeState(int entityID, String state) {

    }

    //public void addPowerup to other objects? - later
}
