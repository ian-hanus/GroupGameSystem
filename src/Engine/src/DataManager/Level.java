package DataManager;

import ECS.Components.Component;
import Events.Event;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Level {
    // TODO: Establish what must be passed with the new entity based backend
    private Map<Integer, Map<String, Component>> myActiveObjects;
    private Map<String[], Set<Event>[]> myCollisionResponses;
    private List<String> myTypes;
    private Integer myLevelNumber;
    private List<Map<String, Component>> myObjectBank;
    private String myBackground;

    public Level(Integer levelNumber, Map<Integer, Map<String, Component>> activeObjects, List<Map<String, Component>> objectBank,
                 Map<String[], Set<Event>[]> collisionResponses, String background){
        myLevelNumber = levelNumber;
        myActiveObjects = activeObjects;
        myObjectBank = objectBank;
        myCollisionResponses = collisionResponses;
        myBackground = background;
    }
}
