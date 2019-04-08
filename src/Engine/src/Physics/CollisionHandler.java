package Physics;

import ECS.Components.BasicComponent;
import ECS.Components.TagsComponent;
import ECS.EntityManager;
import ECS.NoEntityException;
import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import ECS.TagPair;
import EngineMain.LevelManager;
import Events.GameEvents.GameEvent;
import Events.ObjectEvents.ObjectEvent;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollisionHandler {
    private EntityManager myEntityManager;
    private LevelManager myLevelManager;

    public CollisionHandler(EntityManager objectManager, LevelManager levelManager) {
        myEntityManager = objectManager;
        myLevelManager = levelManager;
    }

    public void checkCollision(Integer entity1, Integer entity2, Map<TagPair, List<Event>[]> collisionResponses) {
        try {
            if (entity1 == entity2 || !collides(entity1, entity2))
                return;

            TagPair[] collisionTagPairs = findRelevantTagPairs(entity1, entity2, collisionResponses);
            if (collisionTagPairs.length == 0)
                return;

            myEntityManager.setCollide(entity1, true);
            myEntityManager.setCollide(entity2, true);
            //modifyMovement(obj1, obj2, objectManager); //TODO fix

            for (TagPair tagPair : collisionTagPairs) {
                List<Event>[] responseListPair = collisionResponses.get(tagPair);
                handleEvents(responseListPair);
            }
        } catch (NoEntityException e) {
            System.out.println("One or more entities do not exist.");
        }
    }

    //TODO fix if Events are changed
    private void handleEvents(Integer entity1, Integer entity2, List<Event>[] responseListPair) {
        Integer[] entityPair = {entity1, entity2};
        for (int k = 0; k < responseListPair.length; k++) {
            var responseList = responseListPair[k];
            for (Event event : responseList) {
                int other = 1;
                if (k == 1)
                    other = 0;

                /*
                if (event instanceof ObjectEvent) {              //only include other if necessary for conditionals
                    ((ObjectEvent) event).activate(entityPair[k], entityPair[other], myEntityManager);
                }
                if (event instanceof GameEvent) {
                    ((GameEvent) event).activate(entityPair[k], entityPair[other], myLevelManager);
                }
                */
                //FIXME delegate rest of method to ObjectEvent/GameEvent and uncomment code above

                if (event instanceof ObjectEvent)
                    ((ObjectEvent) event).setEventObject(entityPair[k]);
                List<Conditional> conditionals = event.getConditionals();
                for (Conditional conditional : conditionals) {
                    if (conditional instanceof ObjectConditional) {
                        event.setConditionalObject(entityPair[k]);
                    }
                }
                if (event.conditionsSatisfied(entityPair[other], myEntityManager)) {
                    if (event instanceof ObjectEvent) {
                        ((ObjectEvent) event).activate(entityPair[other], myEntityManager);
                    } else ((GameEvent) event).activate(myLevelManager);
                }
            }
        }
    }

    private TagPair[] findRelevantTagPairs(Integer entity1, Integer entity2, Map<TagPair, List<Event>[]> collisionResponses) throws NoEntityException{
        var tags1 = myEntityManager.getComponent(entity1, TagsComponent.class);
        var tags2 = myEntityManager.getComponent(entity2, TagsComponent.class);
        ArrayList<TagPair> tagPairs = new ArrayList<>();
        for (String tag1 : tags1.getTags()) {
            for (String tag2 : tags2.getTags()) {
                var tagPair = new TagPair(tag1, tag2);
                if (collisionResponses.containsKey(tagPair))
                    tagPairs.add(tagPair);
            }
        }
        return tagPairs.toArray(new TagPair[0]);
    }

    //FIXME do we need to detect whether the collision is right, top, left, or bottom based on solely the velocity angle?
    private boolean collides(Integer entity1, Integer entity2) throws NoEntityException {
        var component1 = myEntityManager.getComponent(entity1, BasicComponent.class);
        var component2 = myEntityManager.getComponent(entity2, BasicComponent.class);
        return collideFromLeft(component1, component2) ||
                collideFromLeft(component1, component2) ||
                collideFromTop(component1, component2) ||
                collideFromTop(component1, component2);
        //FIXME no collide from right or bottom
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
