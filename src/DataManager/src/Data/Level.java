package Data;

import javafx.scene.image.Image;

import java.util.List;

public class Level {
    private List<Object> myGameObjects;
    private Integer myLevelNumber;
    private List<Object> myObjectBank;
    private Image myBackground;

    public Level(Integer levelNumber, List<Object> gameObjects, List<Object> objectBank, Image background){
        myLevelNumber = levelNumber;
        myGameObjects = gameObjects;
        myObjectBank = objectBank;
        myBackground = background;
    }
}
