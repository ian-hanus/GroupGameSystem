package Conditionals;

import GameObjects.GameObject;

public class None implements Conditional{
    @Override
    public boolean satisfied(GameObject obj1, GameObject obj2) {
        return true;
    }

    @Override
    public boolean satisfied() {
        return true;
    }
}
