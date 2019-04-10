package ECS;

import Components.*;

import java.util.Map;

public class EntityManager {
    private Map<Integer, Map<Class<? extends Component>, Component>> myEntityMap;
    private double myStepTime;

    public EntityManager(Map<Integer, Map<Class<? extends Component>, Component>> entityMap, double stepTime) {
        myEntityMap = entityMap;
        myStepTime = stepTime;
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
        //TODO error checking, does removing a non-existent Entity work
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

    public void setYVelocity(int entityID, double vel) {
        var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
        if (motionComponent != null)
            motionComponent.setYVelocity(vel);
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
        var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
        if (motionComponent != null) {
            //might want to split this up into x and y stop methods
            motionComponent.setXVelocity(0);
            motionComponent.setYVelocity(0);
        }
    }

    public void jump(int entityID) {
        var jumpComponent = (JumpComponent) getComponent(entityID, JumpComponent.class);
        var motionComponent = (MotionComponent) getComponent(entityID, MotionComponent.class);
        if (jumpComponent != null && motionComponent != null) {
            double jumpVelocity = jumpComponent.getJumpVelocity();
            motionComponent.setYVelocity(jumpVelocity);
        }
    }

    //TODO remove duplication between horizontal and vertical
    public void moveVertical(Integer entity, boolean down) {
        var basic = getComponent(entity, BasicComponent.class);
        var motion = getComponent(entity, MotionComponent.class);
        if (basic == null || motion == null)
            return;

        double yPos = basic.getY();
        double yVel = motion.getMovementYVelocity();
        int direction = down ? 1 : -1;
        setY(entity, yPos + direction * yVel * myStepTime);
    }

    //TODO remove duplication between horizontal and vertical
    public void moveHorizontal(Integer entity, boolean right) {
        var basic = getComponent(entity, BasicComponent.class);
        var motion = getComponent(entity, MotionComponent.class);
        if (basic == null || motion == null)
            return;

        double xPos = basic.getX();
        double xVel = motion.getMovementXVelocity();
        int direction = right ? 1 : -1;
        setX(entity, xPos + direction * xVel * myStepTime);
    }

    //TODO remove duplication between setY and also in collision handler and detector
    private void setX(int obj, double newX){
        Component basic = getComponent(obj, BasicComponent.class);
        double currentX = ((BasicComponent) basic).getX();
        double finalX = newX;
        CollisionDetector collisionDetector = new CollisionDetector(this);
        Integer[] impassableColliders = collisionDetector.getImpassableColliders(obj, myEntityMap.keySet());
        for(Integer impassable : impassableColliders){
            if ((collisionDetector.collideFromLeft(impassable, obj) && newX > currentX) ||
                (collisionDetector.collideFromLeft(obj, impassable) && newX < currentX)) {
                finalX = currentX;
            }
        }
        ((BasicComponent) basic).setX(finalX);
    }

    //TODO remove duplication between setY and also in collision handler and detector
    private void setY(int obj, double newY){
        Component basic = getComponent(obj, BasicComponent.class);
        double currentY = ((BasicComponent) basic).getY();
        double finalY = newY;
        CollisionDetector collisionDetector = new CollisionDetector(this);
        Integer[] impassableColliders = collisionDetector.getImpassableColliders(obj, myEntityMap.keySet());
        for(Integer impassable : impassableColliders){
            if ((collisionDetector.collideFromTop(impassable, obj) && newY > currentY) ||
                    (collisionDetector.collideFromTop(impassable, obj) && newY < currentY)) {
                finalY = currentY;
            }
        }
        ((BasicComponent) basic).setY(finalY);
    }
}
