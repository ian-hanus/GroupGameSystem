package auth.ObjectControl;

import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectManager {
    private ObjectCreator myObjectCreator;
    private ObjectBank myObjectBank;
    private Map<Integer, Map<String, Integer>> myUniqueObjects;
    private Map<Integer, Map<String, Integer>> myObjects;
    private Map<String[], Set<String>> myCollisions;
    private Integer myObjectCount = 0;
    private Integer myUniqueObjectCount = 0;

    public ObjectManager(){
        myObjectCreator = new ObjectCreator(this);
        myObjectBank = new ObjectBank(this);
        myUniqueObjects = new HashMap<>();
        myObjects = new HashMap<>();
        myCollisions = new HashMap<>();
    }

    public VBox getCreatorView(){
        return myObjectCreator.getView();
    }

    public void writeUniqueObject(Map<String, Integer> objectComponents){
        myUniqueObjects.put(myUniqueObjectCount, objectComponents);
        myUniqueObjectCount++;
    }

    // TODO: add the x and y components to the map before passing to DataManager
    public void writeObject(Map<String, Integer> objectComponents, double xLoc, double yLoc){
        objectComponents.put("xLoc", (int) xLoc);
        objectComponents.put("yLoc", (int) yLoc);
        myObjects.put(myObjectCount, objectComponents);
        myObjectCount++;
    }
}
