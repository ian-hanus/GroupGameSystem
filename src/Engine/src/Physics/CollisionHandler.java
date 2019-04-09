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
    private Map<Integer, EnvironmentComponent> myEntityCurrentEnvironments;

    public CollisionHandler(EntityManager objectManager, LevelManager levelManager, CollisionDetector collisionDetector) {
        myEntityManager = objectManager;
        myLevelManager = levelManager;
        myCollisionDetector = collisionDetector;
        myCollisionResponses = new HashMap<>();
        myPreviousCollisions = new HashMap<>();
        myCurrentCollisions = new HashMap<>();
        myEntityCurrentEnvironments = new HashMap<>();
    }

    public void moveRight() {

    }

    public void moveLeft() {

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
                setInDefaultEnvironment(entity);
        }
        myPreviousCollisions = myCurrentCollisions;
    }

    private boolean notInteractingWithEnvironment(Integer entity) {
        if (myCurrentCollisions.containsKey(entity)) {
            Set<Integer> possibleEnvironments = myCurrentCollisions.get(entity);
            for (Integer possibleEnvironment : possibleEnvironments) {
                if (myEntityManager.getComponent(possibleEnvironment, EnvironmentComponent.class) != null)
                    return true;
            }
        }
        return false;
    }

    private void setInDefaultEnvironment(Integer entity) {
        myEntityCurrentEnvironments.put(entity, null);
    }

    private void checkCollision(Integer entity1, Integer entity2) {
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
    }

    private Pair<String>[] findRelevantTagPairs(Integer entity1, Integer entity2) {
        var tags1 = myEntityManager.getComponent(entity1, TagsComponent.class);
        var tags2 = myEntityManager.getComponent(entity2, TagsComponent.class);

        if (tags1 == null || tags2 == null)
            return new Pair[]{new Pair("",""), new Pair("", "")};

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

    private void handleEnvironments(Integer current, Integer other) {
        myCurrentCollisions.putIfAbsent(current, new HashSet<>());
        myCurrentCollisions.get(current).add(other);
        var currentMotionComponent = myEntityManager.getComponent(current, MotionComponent.class);
        var otherEnvironmentComponent = myEntityManager.getComponent(current, EnvironmentComponent.class);

        if (currentMotionComponent == null)
            return;

        if (myPreviousCollisions.containsKey(current) && !myPreviousCollisions.get(current).contains(other))
            setInEnvironment(current, currentMotionComponent, otherEnvironmentComponent);
    }

    private void setInEnvironment(Integer entity, MotionComponent motion, EnvironmentComponent environment) {
        myEntityCurrentEnvironments.put(entity, environment);

        double scaleFactor = environment.getVelDamper();
        motion.setXVelocity(scaleFactor * motion.getXVelocity());
        motion.setYVelocity(scaleFactor * motion.getYVelocity());
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
                if (event instanceof ObjectEvent) {
                    ((ObjectEvent) event).setOther(other);
                    ((ObjectEvent) event).activate(myEntityManager);
                }
                    else
                    ((GameEvent) event).activate(myLevelManager);
            }
        }
    }
}
