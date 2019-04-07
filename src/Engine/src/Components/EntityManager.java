package Components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityManager {
    private HashMap<Integer, List<Component>> myEntityMap;
    private int myFramesPerSecond;

    public EntityManager(int framesPerSecond) {
        myFramesPerSecond = framesPerSecond;
        myEntityMap = new HashMap<>();
    }

    public void kill(int entityID) {
        myEntityMap.remove(entityID);
    }

    public void create(int entityID, List<Component> components) {
        myEntityMap.put(entityID, components);
    }
}
