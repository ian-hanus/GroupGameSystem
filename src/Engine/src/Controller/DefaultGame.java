package Controller;

import Components.*;
import ECS.Pair;
import Triggers.Events.Event;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultGame {
    private Map<Integer, Map<Class<? extends Component>, Component>> myActiveObjects;
    private Map<Pair<String>, Pair<List<Event>>> myCollisionMap;

    public DefaultGame() {
        myActiveObjects = new HashMap<>();
        myCollisionMap = new HashMap<>();
        addActiveObjects();
        addCollisions();
    }

    private void addActiveObjects() {
        Map<Class<? extends Component>, Component> user = new HashMap<>();
        user.put(BasicComponent.class, new BasicComponent(new File("mario.png"), 300, 200, 50, 50));
        user.put(MotionComponent.class, new MotionComponent(0, 0, 0, 0, 0, 10, 0));
        user.put(JumpComponent.class, new JumpComponent(30));
        List<String> userTag = new ArrayList<>();
        userTag.add("USER");
        user.put(TagsComponent.class, new TagsComponent(userTag));
        myActiveObjects.put(0, user);

        Map<Class<? extends Component>, Component> block = new HashMap<>();
        block.put(BasicComponent.class, new BasicComponent(new File("block.png"), 0, 250, 1000, 75));
        block.put(ImpassableComponent.class, new ImpassableComponent(true));
        List<String> blockTag = new ArrayList<>();
        blockTag.add("BLOCK");
        block.put(TagsComponent.class, new TagsComponent(blockTag));
        myActiveObjects.put(1, block);
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
