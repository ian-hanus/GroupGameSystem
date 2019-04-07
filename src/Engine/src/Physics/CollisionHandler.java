package Physics;

import Conditionals.Conditional;
import Conditionals.ObjectConditional;
import EngineMain.LevelManager;
import Events.GameEvents.GameEvent;
import Events.ObjectEvents.ObjectEvent;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Events.Event;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollisionHandler {

    ObjectManager myObjectManager;

    public CollisionHandler(ObjectManager objectManager){
        myObjectManager = objectManager;
    }

    public void checkCollision(double obj1, double obj2, Map<String[], Set<Event>[]> collisionResponses,
                               LevelManager levelManager){
        String[] collisionTypePair = {myObjectManager.getType(obj1), myObjectManager.getType(obj2)};
        double[] collisionPair = {obj1, obj2};
        if (obj1 != obj2 && collides(obj1, obj2) && collisionResponses.containsKey(collisionTypePair)) {
            myObjectManager.setCollide(obj1, true);
            myObjectManager.setCollide(obj2, true);
            modifyMovement(obj1, obj2);
            Set<Event>[] responseSetPair = collisionResponses.get(collisionTypePair);
            for (int k = 0; k < responseSetPair.length; k++) {
                Set<Event> responseSet = responseSetPair[k];
                for (Event event : responseSet) {
                    event = event.copy();
                    int other = 1;
                    if (k == 1) other = 0;
                    if (event instanceof ObjectEvent) ((ObjectEvent) event).setEventObject(collisionPair[k]);
                    List<Conditional> conditionals = event.getConditionals();
                    for (Conditional conditional : conditionals) {
                        if (conditional instanceof ObjectConditional) {
                            event.setConditionalObject(collisionPair[k]);
                        }
                    }
                    if (event.conditionsSatisfied(collisionPair[other], myObjectManager)) {
                        if (event instanceof ObjectEvent) {
                            ((ObjectEvent) event).activate(collisionPair[other], myObjectManager);
                        } else ((GameEvent) event).activate(levelManager);
                    }
                }
            }
        }
    }

    private boolean collides(double obj1, double obj2){
        if(collideFromLeft(obj1, obj2) || collideFromLeft(obj2, obj1) || collideFromTop(obj1, obj2) || collideFromTop(obj2, obj1)){
            return true;
        }
        return false;
    }

    public boolean collideFromLeft(double collider, double target){
        BasicComponent basicComponent = myObjectManager.getBasicComponent(collider);
        double width1 = basicComponent.getWidth();
        double x1 = basicComponent.getX();
        basicComponent = myObjectManager.getBasicComponent(target);
        double x2 = basicComponent.getX();
        if(x1 + width1 >= x2) return true;
        return false;
    }

    public boolean collideFromTop(double collider, double target){
        BasicComponent basicComponent = myObjectManager.getBasicComponent(collider);
        double height1 = basicComponent.getHeight();
        double y1 = basicComponent.getY();
        basicComponent = myObjectManager.getBasicComponent(target);
        double y2 = basicComponent.getY();
        if(y1 + height1 >= y2) return true;
        return false;
    }

    public void modifyMovement(GameObject obj1, GameObject obj2, ObjectManager objectManager) {
        GameObject block;
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
        }
    }

}
