package EngineMain;

import ECS.Components.Component;
import ECS.Components.MotionComponent;
import ECS.Components.TagsComponent;
import ECS.Pair;
import Events.ObjectEvents.ObjectEvent;
import GameObjects.GameObject;
import GameObjects.ObjectManager;
import Physics.CollisionHandler;
import Events.Event;

import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {

    private Map<String, Event> myHotKeys;
    private List<TimerSequence> myTimers;
    private Map<Pair<String>, Pair<List<Event>>> myCollisionResponses;
    private Map<Integer, Map<Class<? extends Component>, Component>> myActiveObjects;
    private int myUserID;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;
    private ObjectManager myObjectManager;
    private LevelManager myLevelManager;
    private double myStepTime;
    private double myIterationCounter;

    public Controller(double stepTime){
        myDataManager = new DataManager();
        initializeDataVariables();
        myObjectManager = new ObjectManager(myActiveObjects);
        myLevelManager = new LevelManager(myTimers, myObjectManager, myIterationCounter);
        myCollisionHandler = new CollisionHandler(myObjectManager);
        myStepTime = stepTime;
        myIterationCounter = 0;
    }

    public void initializeDataVariables(){
        myActiveObjects = myDataManager.loadDefaultObjects();
        myHotKeys = myDataManager.loadHotKeyMap();
        myCollisionResponses = myDataManager.loadCollisionResponseMap();
        myTimers = myDataManager.loadTimerMap();
        for(int id : myActiveObjects.keySet()){
            Component type =  myActiveObjects.get(id, new Class<T> TagsComponent);
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
        myLevelManager.updateTimer();
        myCollisionHandler.dealWithCollisions(myActiveObjects.keySet(), myCollisionResponses);
        for (int obj : myActiveObjects.keySet()){
            myObjectManager.move(obj);
            Component motion = myActiveObjects.get(obj).get("STATE");
            if(((MotionComponent) motion).colliding()) myObjectManager.restoreMovementDefaults(obj);
            myObjectManager.setCollide(obj, false);
            myObjectManager.updateStats(obj);
        }
    }

    private Map<GameObject[], Set<Event>[]> makeCollisionResponseMap() {
        return null;
    }

}
