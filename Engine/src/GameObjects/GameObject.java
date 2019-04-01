package GameObjects;

import java.util.List;

public class GameObject {
    List myActiveObjects;

    public GameObject(List activeObjects){
        myActiveObjects = activeObjects;
    }

    public void die(){
        myActiveObjects.remove(this);
    }
}
