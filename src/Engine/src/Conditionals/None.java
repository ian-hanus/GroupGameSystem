package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public class None implements Conditional{
    @Override
    public boolean satisfied(double obj1, ObjectManager objectManager) {
        return true;
    }

    @Override
    public boolean satisfied(ObjectManager objectManager) {
        return true;
    }
}
