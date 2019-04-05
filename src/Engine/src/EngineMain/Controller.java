package EngineMain;

import Events.ObjectEvents.ObjectEvent;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import GameObjects.User;
import Physics.CollisionHandler;
import Events.Event;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    private Map<String, GameObject> myObjectBank;
    private Map<String, Event> myHotKeys;
    private Map<Class[], Set<Event>[]> myCollisionResponses;
    private Map<Double, Set<Event>> myTimerMap; // Double[] -> [start time, time limit ]
    private List<GameObject> myActiveObjects;
    private GameObject myUser;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;
    private ObjectManager myObjectManager;
    private LevelManager myLevelManager;

    public Controller(){
        myEngineParser = new EngineParser();
        initializeDataVariables();
        myObjectManager = new ObjectManager(myActiveObjects);
        myLevelManager = new LevelManager(myTimerMap, myObjectManager);
        myCollisionHandler = new CollisionHandler();
    }

    public void initializeDataVariables(){
        myObjectBank = myEngineParser.parseDefaultObjects();
        myHotKeys = myEngineParser.makeHotKeyMap();
        myCollisionResponses = myEngineParser.makeCollisionResponseMap();
        myActiveObjects = myEngineParser.initializeActiveObjects();
        myTimerMap = myEngineParser.makeTimerMap();
        for(GameObject obj : myActiveObjects){
            if (obj instanceof User){
                myUser = obj;
            }
        }
        if (myUser == null); //TODO: throw error
    }

    public void processKey(String key){
        if (myHotKeys.containsKey(key)){
            Event event = myHotKeys.get(key);
            if (event.conditionsSatisfied()){
                if(event instanceof ObjectEvent){
                    ((ObjectEvent) event).setMyObject(myUser);
                    ((ObjectEvent) event).activate(myUser, myObjectManager);
                }
            }
        }
        else; //TODO:error
    }

    public void updateScene(){
        for(Double timer : myTimerMap.keySet()) {
            myLevelManager.checkTimer(timer);
        }
        for(GameObject obj1: myActiveObjects){
            for(GameObject obj2: myActiveObjects){
                myCollisionHandler.checkCollision(obj1, obj2, myCollisionResponses, myObjectManager, myLevelManager);
            }
        }
        for (GameObject obj : myActiveObjects){
            myObjectManager.move(obj);
            if(!obj.colliding()) myObjectManager.restoreMovementDefaults(obj);
            myObjectManager.setCollide(obj, false);
            myObjectManager.updateStats(obj);
        }
    }

    private Map<GameObject[], Set<Event>[]> makeCollisionResponseMap() {
        return null;
    }

}
