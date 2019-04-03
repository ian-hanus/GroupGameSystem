package Physics;

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
        if (obj1 != obj2 && collides(obj1, obj2) && myCollisionResponses.containsKey(collisionClassPair)){
            Set<Response>[] responseSetPair = myCollisionResponses.get(collisionClassPair);
            for (int k = 0; k < responseSetPair.length; k++){
                Set<Response> responseSet = responseSetPair[k];
                for (Response response: responseSet){
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

}
