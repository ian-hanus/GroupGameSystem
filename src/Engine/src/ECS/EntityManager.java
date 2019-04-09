package ECS;

import ECS.Components.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManager {
    private Map<Integer, Map<Class<? extends Component>, Component>> myEntityMap;

    public EntityManager(Map<Integer, Map<Class<? extends Component>, Component>> entityMap) {
        myEntityMap = entityMap;
    }

    public void addComponent(int entityID, Component component) {
        try {
            var components = getAllComponents(entityID);
            components.put(component.getClass(), component);
            myEntityMap.put(entityID, components);
        }
        catch (NoEntityException e) {
            System.out.println("Entity " + entityID + " does not exist");
        }
    }

    //can return null
    //if we want this to be O(1) lookup instead of O(M) [M is number of components in entity], then must convert
    //the List of components for an entity to a map of component classes to components
    public <T extends Component> T getComponent(int entityID, Class<T> componentClass){
        try {
            var components = getAllComponents(entityID);
            for (Class<?> componentName: components.keySet()) {
                if (componentName.equals(componentClass)) {
                    return (T) components.get(componentName);
                }
            }
            return null;
        }
        catch(NoEntityException e) {
            System.out.println("Entity " + entityID + " does not exist");
            return null;
        }
    }

    private Map<Class<? extends Component>, Component> getAllComponents(int entityID) throws NoEntityException {
        Map<Class<? extends Component>, Component> components = myEntityMap.get(entityID);
        if (components == null)
            throw new NoEntityException("Entity " + entityID + " does not exist");
        return components;
    }

    public void die(int entityID) {
        //TODO error checking, does removing a non-existent entity work
        myEntityMap.remove(entityID);
    }

    public void create(int entityID, Map<Class<? extends Component>, Component> components) {
        //TODO error checking
        myEntityMap.put(entityID, components);
    }

    public void move(int entityID) {
        var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
        var basicComponent = (BasicComponent) getComponent(entityID, BasicComponent.class);
        if (motionComponent != null && basicComponent != null) {
            motionComponent.updateVelocity();
            double newX = motionComponent.getNewX(basicComponent.getX());
            double newY = motionComponent.getNewY(basicComponent.getY());
            basicComponent.setX(newX);
            basicComponent.setY(newY);
        }
    }

    public void adjustDirection(int entityID, double delta) {
        var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
        if (motionComponent != null)
            motionComponent.adjustDirection(delta);
    }

    public void setDirection(int entityID, double angle){
        var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
        if (motionComponent != null)
            motionComponent.setDirection(angle);
    }

    public void adjustHealth(int entityID, int delta) {
        var healthComponent = (HealthComponent) getComponent(entityID, HealthComponent.class);
        if (healthComponent != null) {
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
    }

    public void setHealth(int entityID, int health) {
        var healthComponent = (HealthComponent) getComponent(entityID, HealthComponent.class);
        if (healthComponent != null) {
            int maxHealth = healthComponent.getMaxHealth();
            if (health < maxHealth) {
                healthComponent.setHealth(health);
            }
            //is it necessary to throw an error or just set it to the max health?
            else {
                healthComponent.setHealth(maxHealth);
            }
        }
    }

    //Not sure if done here and how states would be referenced non-specifically
    public void changeState(int entityID, String state) {

    }

    public void increaseScore(int entityID, double gain) {

    }


    public void addPowerup(int entityID, int otherEntityID) {

    }

    public void stop(int entityID) {

    }

    public void jump(int entityID) {
        var jumpComponent = (JumpComponent) getComponent(entityID, JumpComponent.class);
        var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
        if (jumpComponent != null && motionComponent != null) {
            double jumpVelocity = jumpComponent.getJumpVelocity();
            motionComponent.setYVelocity(jumpVelocity);
        }
    }
}
