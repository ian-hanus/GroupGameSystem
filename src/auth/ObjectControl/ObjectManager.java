package auth.ObjectControl;

import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.Set;

public class ObjectManager {
    private ObjectCreator myObjectCreator;
    private Map<Integer, Map<String, Integer>> myUniqueObjects;
    private Map<Integer, Map<String, Integer>> myObjects;
    private Map<String[], Set<String>> myCollisions;
    private Integer myObjectCount = 0;
    private Integer myUniqueObjectCount = 0;

    public ObjectManager(){
        myObjectCreator = new ObjectCreator(this);
    }

    public VBox getCreatorView(){
        return myObjectCreator.getView();
    }

    public void writeUniqueObject(Map<String, Integer> objectComponents){
        myUniqueObjects.put(myUniqueObjectCount, objectComponents);
        myUniqueObjectCount++;
    }
}
