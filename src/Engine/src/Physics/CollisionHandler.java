package Physics;

import ECS.*;
import ECS.Components.BasicComponent;
import ECS.Components.EnvironmentComponent;
import ECS.Components.MotionComponent;
import ECS.Components.TagsComponent;
import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import EngineMain.LevelManager;
import Events.GameEvents.GameEvent;
import Events.ObjectEvents.ObjectEvent;
import Events.Event;

import java.util.*;

public class CollisionHandler {
    private EntityManager myEntityManager;
    private LevelManager myLevelManager;
    private CollisionDetector myCollisionDetector;
    private Map<Pair<String>, Pair<List<Event>>> myCollisionResponses;
    private Map<Integer, Set<Integer>> myPreviousCollisions;
    private Map<Integer, Set<Integer>> myCurrentCollisions;

    public CollisionHandler(EntityManager objectManager, LevelManager levelManager, CollisionDetector collisionDetector) {
        myEntityManager = objectManager;
        myLevelManager = levelManager;
        myCollisionDetector = collisionDetector;
        myCollisionResponses = new HashMap<>();
        myPreviousCollisions = new HashMap<>();
        myCurrentCollisions = new HashMap<>();
    }

    //assumes collisionResponses and entities are nonnull
    public void dealWithCollisions(Set<Integer> entities, Map<Pair<String>, Pair<List<Event>>> collisionResponses) {
        myCollisionResponses = collisionResponses;
        myCurrentCollisions = new HashMap<>();
        for (Integer entity1 : entities) {
            for (Integer entity2 : entities) {
                if (entity1 == entity2)
                    continue;
                checkCollision(entity1, entity2);
            }
        }

        for (Integer entity : entities) {
            if (notInteractingWithEnvironment(entity))
                setInDefaultEnvironment();
        }
        myPreviousCollisions = myCurrentCollisions;
    }

    private boolean notInteractingWithEnvironment(Integer entity) {
        if (myCurrentCollisions.containsKey(entity)) {
            Set<Integer> possibleEnvironments = myCurrentCollisions.get(entity);
            for (Integer possibleEnvironment : possibleEnvironments) {
                if (possibleEnvironment.containsComponent(EnvironmentComponent.class))
                    return true;
            }
        }
        return false;
    }

    private void setInDefaultEnvironment(Integer entity) {
        //TODO update acceleration, and more?
    }

    private void checkCollision(Integer entity1, Integer entity2) {
        try {
            Pair<String>[] collisionTagPairs = findRelevantTagPairs(entity1, entity2);
            if (collisionTagPairs.length == 0 || !myCollisionDetector.collides(entity1, entity2))
                return;

            handleEnvironments(entity1, entity2);
            handleEnvironments(entity2, entity1);

            for (Pair<String> tagPair : collisionTagPairs) {
                Pair<List<Event>> responseListPair = myCollisionResponses.get(tagPair);
                activateEvents(entity1, entity2, responseListPair.getItem1());
                activateEvents(entity2, entity1, responseListPair.getItem2());
            }
        } catch (NoEntityException e) {
            System.out.println("One or more entities do not exist.");
        }
    }

    private Pair<String>[] findRelevantTagPairs(Integer entity1, Integer entity2) throws NoEntityException{
        var tags1 = myEntityManager.getComponent(entity1, TagsComponent.class);
        var tags2 = myEntityManager.getComponent(entity2, TagsComponent.class);
        ArrayList<Pair<String>> tagPairs = new ArrayList<>();
        for (String tag1 : tags1.getTags()) {
            for (String tag2 : tags2.getTags()) {
                var tagPair = new Pair(tag1, tag2);
                if (myCollisionResponses.containsKey(tagPair))
                    tagPairs.add(tagPair);
            }
        }
        return tagPairs.toArray(new Pair[0]);
    }

    private void handleEnvironments(Integer current, Integer other) throws NoEntityException {
        myCurrentCollisions.putIfAbsent(current, new HashSet<>());
        myCurrentCollisions.get(current).add(other);
        try {
            var currentMotionComponent = myEntityManager.getComponent(current, MotionComponent.class);
            var otherEnvironmentComponent = myEntityManager.getComponent(current, EnvironmentComponent.class);

            if (myPreviousCollisions.containsKey(current) && !myPreviousCollisions.get(current).contains(other))
                setInEnvironment(currentMotionComponent, otherEnvironmentComponent);
        }
        catch (NoComponentException e) {
            return; //can't update motion component if entity doesn't have one or if other entity doesn't have an environment component
        }
    }

    private void setInEnvironment(MotionComponent motion, EnvironmentComponent environment) {
        //TODO dampen velocities, update accelerations, handle friction?
    }

    //TODO fix if Events are changed
    private void activateEvents(Integer current, Integer other, List<Event> responseList) {
        for (Event event : responseList) {
            /*if (event instanceof ObjectEvent) {                 //only include other if necessary for conditionals
                ((ObjectEvent) event).activate(current, other, myEntityManager);
            }
            if (event instanceof GameEvent) {
                ((GameEvent) event).activate(current, other, myLevelManager);
            }*/
            //FIXME delegate rest of method to ObjectEvent/GameEvent and uncomment code above

            if (event instanceof ObjectEvent)
                ((ObjectEvent) event).setEventObject(current);
            List<Conditional> conditionals = event.getConditionals();
            for (Conditional conditional : conditionals) {
                if (conditional instanceof ObjectConditional) {
                    event.setConditionalObject(current);
                }
            }
            if (event.conditionsSatisfied(other, myEntityManager)) {
                if (event instanceof ObjectEvent)
                    ((ObjectEvent) event).activate(other, myEntityManager);
                else
                    ((GameEvent) event).activate(myLevelManager);
            }
        }
    }
}
