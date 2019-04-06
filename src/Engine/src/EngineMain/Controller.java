package EngineMain;

import GameObjects.GameObject;
import GameObjects.ObjectManager;
import GameObjects.User;
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
    private GameObject myUser;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;
    private ObjectManager myObjectManager;

    public Controller(){
        myEngineParser = new EngineParser();
        initializeDataVariables();
        myObjectManager = new ObjectManager(myActiveObjects);
        myCollisionHandler = new CollisionHandler(myCollisionResponses, myObjectManager);
    }

    public void initializeDataVariables(){
        myObjectBank = myEngineParser.parseDefaultObjects();
        myHotKeys = myEngineParser.makeHotKeyMap();
        myCollisionResponses = myEngineParser.makeCollisionResponseMap();
        myActiveObjects = myEngineParser.initializeActiveObjects();
        for(GameObject obj : myActiveObjects){
            if (obj instanceof User){
                myUser = obj;
            }
        }
        if (myUser == null); //TODO: throw error
    }

    public void processKey(String key){
        if (myHotKeys.containsKey(key)){
            Response response = myHotKeys.get(key);
            response.respond(myUser, myObjectManager);
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
            myObjectManager.move(obj);
            myObjectManager.updateStats(obj);
        }
    }

    private Map<GameObject[], Set<Response>[]> makeCollisionResponseMap() {
        return null;
    }

}
