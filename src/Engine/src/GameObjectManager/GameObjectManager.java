package GameObjectManager;

import GameObjects.GameObject;

import java.util.ArrayList;

class GameObjectManager {
    ArrayList<GameObject> myActiveObjects;

    GameObjectManager() {
        myActiveObjects = new ArrayList<>();
    }

    void kill(GameObject gameObject) {
        myActiveObjects.remove(gameObject);
    }

    void create(GameObject gameObject) {
        myActiveObjects.add(gameObject);
    }

    int getNumObjects() {
        return myActiveObjects.size();
    }
}
