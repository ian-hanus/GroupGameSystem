package Physics;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Responses.Response;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollisionHandler {

    public void checkCollision(GameObject obj1, GameObject obj2, Map<Class[], Set<Response>[]> collisionResponses, ObjectManager objectManager){
        Class[] collisionClassPair = {obj1.getClass(), obj2.getClass()};
        GameObject[] collisionPair = {obj1, obj2};
        if (obj1 != obj2 && collides(obj1, obj2) && collisionResponses.containsKey(collisionClassPair)){
            Set<Response>[] responseSetPair = collisionResponses.get(collisionClassPair);
            for (int k = 0; k < responseSetPair.length; k++){
                Set<Response> responseSet = responseSetPair[k];
                for (Response response: responseSet){
                    int other = 1;
                    if (k == 1) other = 0;
                    if (response.conditionsSatisfied(obj1, obj2)) response.respond(collisionPair[k], collisionPair[other], objectManager);
                }
            }
        }

    }

    private boolean collides(GameObject obj1, GameObject obj2){
        if(collideFromLeft(obj1, obj2) || collideFromLeft(obj2, obj1) || collideFromTop(obj1, obj2) || collideFromTop(obj2, obj1)){
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

}
