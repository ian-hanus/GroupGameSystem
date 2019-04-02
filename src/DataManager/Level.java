package DataManager;

public class Level {
    List<GameObject> myGameObjects;
    Integer myLevelNumber;
    List<GameObject> myObjectBank;

    public Level(Integer levelNumber, List<GameObject> gameObjects, List<GameObject> objectBank){
        myLevelNumber = levelNumber;
        myGameObjects = gameObjects;
        myObjectBank = objectBank;
    }
}
