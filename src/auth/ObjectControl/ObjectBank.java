package auth.ObjectControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectBank {
    private List<Map<String, Integer>> myObjects;
    private ObjectManager myManager;

    public ObjectBank(ObjectManager manager){
        myObjects = new ArrayList<>();
        myManager = manager;
    }
}
