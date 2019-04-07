package EngineMain;

import Events.ObjectEvents.ObjectEvent;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import GameObjects.User;
import Physics.CollisionHandler;
import Events.Event;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    private Map<String, GameObject> myObjectBank;
    private Map<String, Event> myHotKeys;
    private Map<String[], Set<Event>[]> myCollisionResponses;
    private Map<Timer, Set<Event>> myTimerMap;
    private Map<Double, Map<String, Components>> myActiveObjects;
    private double myUserID;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;
    private ObjectManager myObjectManager;
    private LevelManager myLevelManager;
    private double myStepTime;
    private int myIterationCounter;

    public Controller(double stepTime){
        myEngineParser = new EngineParser();
        initializeDataVariables();
        myObjectManager = new ObjectManager(myActiveObjects);
        myLevelManager = new LevelManager(myTimerMap, myObjectManager);
        myCollisionHandler = new CollisionHandler();
        myStepTime = stepTime;
        myIterationCounter = 0;
    }

    public void initializeDataVariables(){
        myObjectBank = myEngineParser.parseDefaultObjects();
        myHotKeys = myEngineParser.makeHotKeyMap();
        myCollisionResponses = myEngineParser.makeCollisionResponseMap();
        myActiveObjects = myEngineParser.initializeActiveObjects();
        myTimerMap = myEngineParser.makeTimerMap();
        for(Double id : myActiveObjects.keySet()){
            Component type =  myActiveObjects.get(id).get("TYPE");
            if(((Type) component).getType.equals("USER")) {
                myUserID = id;
                break;
            }
        }
    }

    public void processKey(String key){
        if (myHotKeys.containsKey(key)){
            Event event = myHotKeys.get(key);
            event.setConditionalObject(myUserID);
            if (event.conditionsSatisfied(myObjectManager)){
                if(event instanceof ObjectEvent){
                    ((ObjectEvent) event).setEventObject(myUserID);
                    ((ObjectEvent) event).activate(myObjectManager);
                }
            }
        }
        else; //TODO:error
    }

    public void updateScene(){
        for(Double timer : myTimerMap.keySet()) {
            myLevelManager.checkTimer(timer);
        }
        for(double obj1: myActiveObjects.keySet()){
            for(double obj2: myActiveObjects.keySet()){
                myCollisionHandler.checkCollision(obj1, obj2, myCollisionResponses, myObjectManager, myLevelManager);
            }
        }
        for (double obj : myActiveObjects){
            myObjectManager.move(obj);
            Component state = myActiveObjects.get(obj).get("STATE");
            if(((State) state).colliding()) myObjectManager.restoreMovementDefaults(obj);
            myObjectManager.setCollide(obj, false);
            myObjectManager.updateStats(obj);
        }
    }

    private Map<GameObject[], Set<Event>[]> makeCollisionResponseMap() {
        return null;
    }

}
