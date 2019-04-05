package Engine.src.Physics;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Responses.Response;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollisionHandler {

    private Map<Class[], Set<Response>[]> myCollisionResponses;
    private ObjectManager myObjectManager;

    public CollisionHandler(Map collisionResponses, ObjectManager objectManager){
        myCollisionResponses = collisionResponses;
        myObjectManager = objectManager;
    }

    public void checkCollision(GameObject obj1, GameObject obj2){
        Class[] collisionClassPair = {obj1.getClass(), obj2.getClass()};
        GameObject[] collisionPair = {obj1, obj2};
        if (obj1 != obj2 && collides(obj1, obj2) && collisionResponses.containsKey(collisionClassPair)) {
            objectManager.setCollide(obj1, true);
            objectManager.setCollide(obj2, true);
            modifyMovement(obj1, obj2, objectManager);
            Set<Event>[] responseSetPair = collisionResponses.get(collisionClassPair);
            for (int k = 0; k < responseSetPair.length; k++) {
                Set<Event> responseSet = responseSetPair[k];
                for (Event event : responseSet) {
                    int other = 1;
                    if (k == 1) other = 0;
                    response.respond(collisionPair[k], collisionPair[other], myObjectManager);
                }
            }
        }

    }

    private boolean collides(GameObject obj1, GameObject obj2){
        double height1 = obj1.getHeight();
        double height2 = obj2.getHeight();
        double width1 = obj1.getWidth();
        double width2 = obj2.getWidth();
        double x1 = obj1.getX();
        double x2 = obj2.getX();
        double y1 = obj1.getY();
        double y2 = obj1.getY();

        if(x1 + width1 >= x2 || y1 + height1>= y2 || x2 + width2 >= x1 || y2 + height2>= y1){
            return true;
        }
        return false;
    }

    public boolean collideFromLeft(GameObject collider, GameObject target){
        double width1 = collider.getWidth();
        double x1 = collider.getX();
        double x2 = target.getX();
        if(x1 + width1 >= x2) return true;
        return false;
    }

    public boolean collideFromTop(GameObject collider, GameObject target){
        double height1 = collider.getHeight();
        double y1 = collider.getY();
        double y2 = target.getY();
        if(y1 + height1>= y2) return true;
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
