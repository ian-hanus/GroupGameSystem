package Physics;

import GameObjects.GameObject;
import Responses.Response;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollisionHandler {

    private Map<GameObject[], Set<Response>[]> myCollisionResponses;

    public CollisionHandler(Map collisionResponses){
        myCollisionResponses = collisionResponses;
    }

    public void checkCollision(GameObject obj1, GameObject obj2){
        GameObject[] collisionPair={obj1, obj2};
        if (obj1 != obj2 && collides(obj1, obj2) && myCollisionResponses.containsKey(collisionPair)){
            Set<Response>[] responseSetPair = myCollisionResponses.get(collisionPair);
            for (int k = 0; k < responseSetPair.length; k++){
                Set<Response> responseSet = responseSetPair[k];
                for (Response response: responseSet){
                    response.respond(collisionPair[k]);
                }
            }
        }

    }

    private boolean collides(GameObject obj1, GameObject obj2){
        int height1 = obj1.getHeight();
        int height2 = obj2.getHeight();
        int width1 = obj1.getWidth();
        int width2 = obj2.getWidth();
        int x1 = obj1.getX();
        int x2 = obj2.getX();
        int y1 = obj1.getY();
        int y2 = obj1.getY();

        if(x1 + width1 >= x2 || y1 + height1>= y2 || x2 + width2 >= x1 || y2 + height2>= y1){
            return true;
        }
        return false;
    }

}
