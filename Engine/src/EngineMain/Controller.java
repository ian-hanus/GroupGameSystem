package EngineMain;

import GameObjects.GameObject;
import Physics.CollisionHandler;
import Responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    private Map<String, Response> myHotKeys;
    private Map<GameObject[], Set<Response>[]> myCollisionResponses;
    private List<GameObject> myActiveObjects;
    private GameObject myHero;
    private CollisionHandler myCollisionHandler;

    public Controller(List activeObjects, GameObject hero){
        makeHotKeyMap();
        myCollisionResponses = makeCollisionResponseMap();
        myActiveObjects = activeObjects;
        myCollisionHandler = new CollisionHandler(myCollisionResponses);
        myHero = hero;
    }

    public void processKey(String key){
        if (myHotKeys.containsKey(key)){
            Response response = myHotKeys.get(key);
            response.respond(myHero);
        }
        else; //TODO:error
    }

    public void updateScene(){
        for(GameObject obj1: myActiveObjects){
            for(GameObject obj2: myActiveObjects){
                myCollisionHandler.checkCollision(obj1, obj2);
            }
        }
        for (GameObject obj : myActiveObjects){
            obj.updatePosition();
            obj.updateStats();
        }
    }

    private void makeHotKeyMap() {
    }

    private Map<GameObject[], Set<Response>[]> makeCollisionResponseMap() {
        return null;
    }

}
