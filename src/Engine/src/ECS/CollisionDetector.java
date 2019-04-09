package ECS;

import ECS.Components.BasicComponent;

public class CollisionDetector {
    private EntityManager myEntityManager;

    public CollisionDetector(EntityManager entityManager) {
        myEntityManager = entityManager;
    }

    //FIXME do we need to detect whether the collision is right, top, left, or bottom based on solely the velocity angle?
    public boolean collides(Integer collider, Integer target) {
            return collideFromLeft(collider, target) ||
                    //collideFromRight(component1, component2) ||
                    //collideFromBottom(component1, component2) ||
                    collideFromTop(collider, target);
            //FIXME uncomment methods above
    }

    //FIXME no collide from right (CollideFromRight event uses this method)
    public boolean collideFromLeft(Integer collider, Integer target) {
        var colliderComponent = myEntityManager.getComponent(collider, BasicComponent.class); //FIXME these two lines duplicated in all collision methods
        var targetComponent = myEntityManager.getComponent(target, BasicComponent.class);
        double width1 = colliderComponent.getWidth();
        double x1 = colliderComponent.getX();
        double x2 = targetComponent.getX();
        return x1 + width1 >= x2;
    }

    //FIXME no collide from bottom (CollideFromBottom event uses this method)
    public boolean collideFromTop(Integer collider, Integer target) {
        var colliderComponent = myEntityManager.getComponent(collider, BasicComponent.class);
        var targetComponent = myEntityManager.getComponent(target, BasicComponent.class);
        double height1 = colliderComponent.getHeight();
        double y1 = colliderComponent.getY();
        double y2 = targetComponent.getY();
        return y1 + height1>= y2;
    }
}
