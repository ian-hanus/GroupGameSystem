package EngineMain;

import Events.ObjectEvents.ObjectEvent;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import GameObjects.User;
import Physics.CollisionHandler;
import Events.Event;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    private Map<String, GameObject> myObjectBank;
    private Map<String, Event> myHotKeys;
    private Map<String[], Set<Event>[]> myCollisionResponses;
    private Set<Timer> myTimers;
    private Map<Double, Map<String, Components>> myActiveObjects;
    private double myUserID;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;
    private ObjectManager myObjectManager;
    private LevelManager myLevelManager;
    private double myStepTime;
    private double myIterationCounter;

    public Controller(double stepTime){
        myEngineParser = new EngineParser();
        initializeDataVariables();
        myObjectManager = new ObjectManager(myActiveObjects);
        myLevelManager = new LevelManager(myTimers, myObjectManager, myIterationCounter);
        myCollisionHandler = new CollisionHandler(myObjectManager);
        myStepTime = stepTime;
        myIterationCounter = 0;
    }

    public void initializeDataVariables(){
        myObjectBank = myEngineParser.parseDefaultObjects();
        myHotKeys = myEngineParser.makeHotKeyMap();
        myCollisionResponses = myEngineParser.makeCollisionResponseMap();
        myActiveObjects = myEngineParser.initializeActiveObjects();
        myTimers = myEngineParser.makeTimerMap();
        for(Double id : myActiveObjects.keySet()){
            Component type =  myActiveObjects.get(id).get("TYPE");
            if(((Type) type).getType.equals("USER")) {
                myUserID = id;
                break;
            }
        }
    }

    public void processKey(String key){
        if (myHotKeys.containsKey(key)){
            Event event = myHotKeys.get(key);
            event = event.copy();
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
                myCollisionHandler.checkCollision(obj1, obj2, myCollisionResponses, myLevelManager);
            }
        }
        for (double obj : myActiveObjects.keySet()){
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
