package Conditionals;

import GameObjects.ObjectManager;

public interface Conditional {

    boolean satisfied(ObjectManager objectManager);
    boolean satisfied(double obj, ObjectManager objectManager);

}
