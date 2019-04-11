package auth.ObjectControl;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectManager {
    private ObjectCreator myObjectCreator;
    private ObjectBank myObjectBank;
    private Map<Integer, Map<String, String>> myUniqueObjects;
    private Map<Integer, Map<String, String>> myObjects;
    private Map<String[], Set<String>> myCollisions;
    private Integer myObjectCount = 0;
    private Integer myUniqueObjectCount = 0;
    private Pane myPane;

    public ObjectManager(Pane pane){
        myObjectCreator = new ObjectCreator(this);
        myObjectBank = new ObjectBank(this);
        myUniqueObjects = new HashMap<>();
        myObjects = new HashMap<>();
        myCollisions = new HashMap<>();
        myPane = pane;
    }

    public VBox getCreatorView(){
        return myObjectCreator.getView();
    }

    public VBox getBankView(){
        return myObjectBank.getView();
    }

    public void writeUniqueObject(Map<String, String> objectComponents){
        myUniqueObjects.put(myUniqueObjectCount, objectComponents);
        myUniqueObjectCount++;
        myObjectBank.addObject(objectComponents);
        myPane.getChildren().removeAll();
        myPane.getChildren().add(myObjectBank.getView());
    }

    // TODO: add the x and y Components to the map before passing to DataManager
    public void writeObject(Map<String, String> objectComponents, double xLoc, double yLoc){
        objectComponents.put("xLoc", Double.toString(xLoc));
        objectComponents.put("yLoc", Double.toString(yLoc));
        myObjects.put(myObjectCount, objectComponents);
        myObjectCount++;
    }
}
