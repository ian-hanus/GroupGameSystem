package Conditionals;

import GameObjects.GameObject;

public interface Conditional {
    boolean satisfied();
    boolean satisfied(GameObject obj1, GameObject obj2);
}
