package controller;

import triggers.Conditionals.Conditional;
import triggers.Conditionals.HealthComparison;
import ecs.CollisionDetector;
import components.BasicComponent;
import components.Component;
import components.HealthComponent;
import components.TagsComponent;
import ecs.EntityManager;
import ecs.Pair;
import triggers.Events.ObjectEvents.Jump;
import triggers.Events.ObjectEvents.MoveLeft;
import triggers.Events.ObjectEvents.ObjectEvent;
import triggers.Events.GameEvents.GameEvent;
import triggers.Events.ObjectEvents.*;
import ecs.CollisionHandler;
import triggers.Events.Event;
import triggers.TimerSequence;

import java.util.ArrayList;
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
    private List<Event> myTriggers;
    private int myUserID;
    private CollisionHandler myCollisionHandler;
    private EngineParser myEngineParser;
    private EntityManager myEntityManager;
    private LevelManager myLevelManager;
    private double myStepTime;
    private double myIterationCounter;
    private double[] offset;

    public Controller(double stepTime, double screenWidth, double screenHeight, double levelWidth, double levelHeight){
        myStepTime = stepTime;
        myScreenWidth = screenWidth;
        myScreenHeight = screenHeight;
        //myDataManager = new DataManager();
        initializeDataVariables();
        myLevelManager = new LevelManager(myTimers, myEntityManager, myIterationCounter, levelWidth, levelHeight);
        myEntityManager = new EntityManager(myActiveObjects, myStepTime);
        myCollisionHandler = new CollisionHandler(myEntityManager, myLevelManager, new CollisionDetector(myEntityManager));
        myIterationCounter = 0;
    }

    //FIXME??
    public void initializeDataVariables(){
        //myActiveObjects = myDataManager.loadDefaultObjects();
        //myHotKeys = myDataManager.loadHotKeyMap();
        //myCollisionResponses = myDataManager.loadCollisionResponseMap();
        //myTimers = myDataManager.loadTimerMap();
        //myTriggers = myDataManager.loadTriggers();
        for(int id : myActiveObjects.keySet()){
            Component type =  myActiveObjects.get(id).get(TagsComponent.class);
            if(((TagsComponent) type).contains("USER")){
                myUserID = id;
                break;
            }
        }
        setDefaultKeys();
        setDefaultTriggers();
    }

    private void setDefaultKeys() {
        myHotKeys = new HashMap<>();
        myHotKeys.put("A", new MoveLeft(myUserID));
        myHotKeys.put("D", new MoveRight(myUserID));
        myHotKeys.put("SPACE", new Jump(myUserID));
    }

    private void setDefaultTriggers(){
        for(Integer id : myActiveObjects.keySet()) {
            Component health = myEntityManager.getComponent(id, HealthComponent.class);
            List<Conditional> conditionals = new ArrayList<>();
            conditionals.add(new HealthComparison(true, id, "<=", new HealthComponent(0, 0)));
            myTriggers.add(new Die(conditionals, id));
        }
    }

    public void processKey(String key){
        if (myHotKeys.containsKey(key)){
            Event event = myHotKeys.get(key);
            event = event.copy();
            event.setConditionalObject(myUserID);
            if (event.conditionsSatisfied(myEntityManager)){
                if(event instanceof ObjectEvent){
                    //((ObjectEvent) event).setEventObject(myUserID);
                    ((ObjectEvent) event).activate(myEntityManager);
                }
            }
        }
        else; //TODO:error
    }

    public void updateScene(){
        List<Event> newTriggers = new ArrayList<>();
        for( Event event : myTriggers){
            if (event.conditionsSatisfied(myEntityManager)){
                if (event instanceof ObjectEvent) ((ObjectEvent) event).activate(myEntityManager);
                else if (event instanceof GameEvent) ((GameEvent) event).activate(myLevelManager);
                }
            else newTriggers.add(event);
            }
        myTriggers = newTriggers;
        myLevelManager.updateTimer();
        myCollisionHandler.dealWithCollisions(myActiveObjects.keySet(), myCollisionResponses);
        for (int obj : myActiveObjects.keySet()){
            myEntityManager.move(obj);
        }
        offset = updateOffset();
    }

    public double[] updateOffset() {
        BasicComponent basic = (BasicComponent) myActiveObjects.get(myUserID).get(BasicComponent.class);
        double userX = basic.getX();
        double userY = basic.getY();
        double userWidth = basic.getWidth();
        double userHeight = basic.getHeight();
        return myLevelManager.determineOffset(userX, userY, userWidth, userHeight, myScreenWidth, myScreenHeight);
    }

    public double[] getOffset() {
        return offset;
    }
    public Map<Integer, Map<Class<? extends Component>, Component>> getEntities(){
        return myActiveObjects;
    }

}
