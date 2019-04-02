package EngineMain;

import GameObjects.GameObject;
import Physics.CollisionHandler;
import Responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    private Map<String, GameObject> myObjectBank;
    private Map<String, Response> myHotKeys;
    private Map<GameObject[], Set<Response>[]> myCollisionResponses;
    private List<GameObject> myActiveObjects;
    private GameObject myHero;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;

    public Controller(List activeObjects, GameObject hero){
        myEngineParser = new EngineParser();
        initializeEngineVariables();
        myCollisionHandler = new CollisionHandler(myCollisionResponses);
    }

    public void initializeEngineVariables(){
        myObjectBank = myEngineParser.parseDefaultObjects();
        myHotKeys = myEngineParser.makeHotKeyMap();
        myCollisionResponses = myEngineParser.makeCollisionResponseMap();
        myActiveObjects = myEngineParser.initializeActiveObjects();
        for(GameObject obj : myActiveObjects){
            if (obj.getType.equals("HERO")){
                myHero = obj;
            }
        }
        if (myHero == null); //TODO: throw error
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

    private Map makeHotKeyMap() {
        return null;
    }

    private Map<GameObject[], Set<Response>[]> makeCollisionResponseMap() {
        return null;
    }

}
