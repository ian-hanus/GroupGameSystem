package Controller;

import Components.Component;
import ECS.Pair;
import Triggers.Events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultGame {
    private Map<Integer, Map<Class<? extends Component>, Component>> myActiveObjects;
    private Map<Pair<String>, Pair<List<Event>>> myCollisionMap;

    public DefaultGame() {
        myActiveObjects = new HashMap<>();
        myCollisionMap = new HashMap();
        addActiveObjects();
        addCollisions();
    }

    private void addActiveObjects() {
        //TODO
    }

    private void addCollisions() {
        //TODO
    }

    public Map<Integer, Map<Class<? extends Component>, Component>> getActiveObjects() {
        return myActiveObjects;
    }

    public Map<Pair<String>, Pair<List<Event>>> getCollisionMap() {
        return myCollisionMap;
    }
}
