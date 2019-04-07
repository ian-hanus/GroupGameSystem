package Physics;

import ECS.Components.BasicComponent;
import ECS.Components.TagsComponent;
import ECS.EntityManager;
import ECS.NoEntityException;
import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import EngineMain.LevelManager;
import Events.GameEvents.GameEvent;
import Events.ObjectEvents.ObjectEvent;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Events.Event;

import java.util.List;
import java.util.Map;

public class CollisionHandler {
    private EntityManager myEntityManager;
    private LevelManager myLevelManager;

    public CollisionHandler(EntityManager objectManager, LevelManager levelManager) {
        myEntityManager = objectManager;
        myLevelManager = levelManager;
    }

    public void checkCollision(Integer entity1, Integer entity2, Map<TagsComponent[], List<Event>[]> collisionResponses) {
        try {
            if (entity1 == entity2 || collides(entity1, entity2) || !myCollisionResponses.containsKey(tagPair))
                return;

            myEntityManager.setCollide(entity1, true);
            myEntityManager.setCollide(entity2, true);
            //modifyMovement(obj1, obj2, objectManager); //TODO fix
            List<Event>[] responseListPair = myCollisionResponses.get(tagPair);
            for (int k = 0; k < responseListPair.length; k++) {
                var responseList = responseListPair[k];
                for (Event event : responseList) {
                    int other = 1;
                    if (k == 1)
                        other = 0;

                    ///////TODO break
                    if (event instanceof ObjectEvent)
                        ((ObjectEvent) event).setEventObject(collisionPair[k]);
                    List<Conditional> conditionals = event.getConditionals();
                    for (Conditional conditional : conditionals) {
                        if (conditional instanceof ObjectConditional) {
                            event.setConditionalObject(collisionPair[k]);
                        }
                    }
                    if (event.conditionsSatisfied(collisionPair[other], myEntityManager)) {
                        if (event instanceof ObjectEvent) {
                            ((ObjectEvent) event).activate(collisionPair[other], myEntityManager);
                        } else ((GameEvent) event).activate(myLevelManager);
                    }
                }
            }
        } catch (NoEntityException e) {
            System.out.println("One or more entities do not exist.");
        }
    }

    private boolean collides(Integer entity1, Integer entity2) throws NoEntityException {
        var basicComponent1 = myEntityManager.getComponent(entity1, BasicComponent.class);
        var basicComponent2 = myEntityManager.getComponent(entity2, BasicComponent.class);
        return collideFromLeft(component1, component2) ||
                collideFromLeft(component1, component2) ||
                collideFromTop(component1, component2) ||
                collideFromTop(component1, component2);
        //FIXME no collide from right or bottom
    }

    private TagsComponent[] getTagPair(int entity1, int entity2) throws NoEntityException {
        var tags1 = myEntityManager.getComponent(entity1, TagsComponent.class);
        var tags2 = myEntityManager.getComponent(entity2, TagsComponent.class);
        return new TagsComponent[]{tags1, tags2};
    }

    public boolean collideFromLeft(BasicComponent collider, BasicComponent target){
        double width1 = collider.getWidth();
        double x1 = collider.getX();
        double x2 = target.getX();
        return x1 + width1 >= x2;
    }

    public boolean collideFromTop(BasicComponent collider, BasicComponent target){
        double height1 = collider.getHeight();
        double y1 = collider.getY();
        double y2 = target.getY();
        return y1 + height1>= y2;
    }

    public void modifyMovement(GameObject obj1, GameObject obj2, ObjectManager objectManager) {
/*        GameObject block;
        GameObject other;
        if (obj1 instanceof Block) {
            block = obj1;
            other = obj2;
        }
        else if (obj2 instanceof Block) {
            block = obj2;
            other = obj1;
        }
        else return;

        if (((Block) block).impassible()){
            if(collideFromLeft(block, other) || collideFromLeft(other, block)) objectManager.setXVel(other, 0);
            if(collideFromTop(block, other) || collideFromTop(other, block))objectManager.setYVel(other, 0);
        }
        else{
            objectManager.downscaleVelocity(other, ((Block) block).getVelocityScalar());
        }*/
    }

}
