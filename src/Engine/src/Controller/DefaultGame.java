package Engine.src.Controller;

import Engine.src.Components.*;
import Engine.src.ECS.Pair;
import Engine.src.Triggers.Events.Event;
import Engine.src.Triggers.Events.ObjectEvents.Deflect;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultGame {
    private static final String BLOCK_IMAGE = "block.jpg";
    private static final String USER_IMAGE = "mario.png";
    private static final double USER_WIDTH = 30;
    private static final double USER_HEIGHT = 50;
    private static final double USER_MOVEMENT_VELOCITY = 10;
    private static final double USER_JUMP_VELOCITY = 10; //good to increase if you increase gravity and vice versa
    private static final double GRAVITY = 1;

    private Map<Integer, Map<Class<? extends Component>, Component>> myActiveObjects;
    private Map<Pair<String>, Pair<List<Event>>> myCollisionMap;
    private List<Map<Class<? extends Component>, Component>> myBlocks;

    public DefaultGame() {
        myActiveObjects = new HashMap<>();
        myCollisionMap = new HashMap<>();
        addActiveObjects();
        addCollisions();
    }

    private void addActiveObjects() {
        makeUser();

        myBlocks = new ArrayList<>();
        addBlock(0, 250, 100);
        addBlock(110, 250, 100);
        addBlock(220, 300, 150);
        addBlock(450, 200, 50);
        addImpassableAndTags();

        int blockNumber = 1;
        for (var block : myBlocks) {
            myActiveObjects.put(blockNumber, block);
            blockNumber++;
        }
    }

    private void addBlock(double x, double y, double width) {
        Map<Class<? extends Component>, Component> block = new HashMap<>();
        myBlocks.add(block);
        block.put(BasicComponent.class, new BasicComponent("/images/" + BLOCK_IMAGE, x, y,width, 75));
    }

    private List<String> addImpassableAndTags() {
        List<String> blockTag = new ArrayList<>();
        blockTag.add("BLOCK");
        for (var block : myBlocks) {
            block.put(ImpassableComponent.class, new ImpassableComponent(true));
            block.put(TagsComponent.class, new TagsComponent(blockTag));
        }
        return blockTag;
    }

    private void makeUser() {
        Map<Class<? extends Component>, Component> user = new HashMap<>();
        user.put(BasicComponent.class, new BasicComponent("/images/" + USER_IMAGE, 300, 100, USER_WIDTH, USER_HEIGHT));
        user.put(MotionComponent.class, new MotionComponent(0, 0, 0, GRAVITY, 0, USER_MOVEMENT_VELOCITY, 0));
        user.put(JumpComponent.class, new JumpComponent(USER_JUMP_VELOCITY));
        List<String> userTag = new ArrayList<>();
        userTag.add("USER");
        user.put(TagsComponent.class, new TagsComponent(userTag));
        myActiveObjects.put(0, user);
    }



    private void addCollisions() {
        var tagPair = new Pair<>("USER","BLOCK");
        var deflect = new Deflect(new ArrayList<>(), 0);
        var list1 = new ArrayList<Event>();
        var list2 = new ArrayList<Event>();
        list1.add(deflect);
        myCollisionMap.put(tagPair, new Pair<>(list1, list2));
    }

    public Map<Integer, Map<Class<? extends Component>, Component>> getActiveObjects() {
        return myActiveObjects;
    }

    public Map<Pair<String>, Pair<List<Event>>> getCollisionMap() {
        return myCollisionMap;
    }
}
