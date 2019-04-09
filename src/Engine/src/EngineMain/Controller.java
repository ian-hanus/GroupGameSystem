package EngineMain;

import ECS.CollisionDetector;
import ECS.Components.Component;
import ECS.Components.TagsComponent;
import ECS.EntityManager;
import ECS.Pair;
import Events.ObjectEvents.Jump;
import Events.ObjectEvents.MoveLeft;
import Events.ObjectEvents.MoveRight;
import Events.ObjectEvents.ObjectEvent;
import Physics.CollisionHandler;
import Events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    //FIXME remove eventually
    private static final boolean SCROLLS_HORIZONTALLY = true;
    private static final boolean SCROLLS_VERTICALLY = false;
    private static final boolean IS_AUTO_SCROLLER = false;
    private static final double SCROLL_SPEED = 10;                        //if auto scroller
    private static final double CHARACTER_DISTANCE_FROM_SCROLL_WALL = 20; //if auto scroller
    private static final double START_X = 0;
    private static final double START_Y = 0;
    //FIXME remove eventually

    private final double myScreenWidth;
    private final double myScreenHeight;

    private Map<String, Event> myHotKeys;
    private List<TimerSequence> myTimers;
    private Map<Pair<String>, Pair<List<Event>>> myCollisionResponses;
    private Map<Integer, Map<Class<? extends Component>, Component>> myActiveObjects;
    private int myUserID;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;
    private EntityManager myEntityManager;
    private LevelManager myLevelManager;
    private double myStepTime;
    private double myIterationCounter;
    private double myWidth;
    private double myHeight;

    public Controller(double stepTime, double screenWidth, double screenHeight){
        myStepTime = stepTime;
        myScreenWidth = screenWidth;
        myScreenHeight = screenHeight;
        myDataManager = new DataManager();
        initializeDataVariables();
        myEntityManager = new EntityManager(myActiveObjects, myStepTime);
        myLevelManager = new LevelManager(myTimers, myEntityManager, myIterationCounter);
        myCollisionHandler = new CollisionHandler(myEntityManager, myLevelManager, new CollisionDetector(myEntityManager));
        myIterationCounter = 0;
        myWidth = width;
        myHeight = height;
    }

    //FIXME??
    public void initializeDataVariables(){
        myActiveObjects = myDataManager.loadDefaultObjects();
        //myHotKeys = myDataManager.loadHotKeyMap();
        setDefaultKeys();
        myCollisionResponses = myDataManager.loadCollisionResponseMap();
        //myTimers = myDataManager.loadTimerMap();
        for(int id : myActiveObjects.keySet()){
            Component type =  myActiveObjects.get(id).get(TagsComponent.class);
            if(((TagsComponent) type).contains("User")){
                myUserID = id;
                break;
            }
        }
    }

    private void setDefaultKeys() {
        myHotKeys = new HashMap<>();
        myHotKeys.put("A", new MoveLeft());
        myHotKeys.put("D", new MoveRight());
        myHotKeys.put("SPACE", new Jump());
    }

    public void processKey(String key){
        if (myHotKeys.containsKey(key)){
            Event event = myHotKeys.get(key);
            event = event.copy();
            event.setConditionalObject(myUserID);
            if (event.conditionsSatisfied(myEntityManager)){
                if(event instanceof ObjectEvent){
                    ((ObjectEvent) event).setEventObject(myUserID);
                    ((ObjectEvent) event).activate(myEntityManager);
                }
            }
        }
        else; //TODO:error
    }

    public void updateScene(){
        myLevelManager.updateTimer();
        myCollisionHandler.dealWithCollisions(myActiveObjects.keySet(), myCollisionResponses);
        for (int obj : myActiveObjects.keySet()){
            myEntityManager.move(obj);
        }
        updateScroll();
    }

    public void updateScroll() {

    }

    public Map<Integer, Map<Class<? extends Component>, Component>> getEntities(){
        return myActiveObjects;
    }

}
