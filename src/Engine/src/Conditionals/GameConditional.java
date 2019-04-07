package Conditionals;

import GameObjects.ObjectManager;

public abstract class GameConditional implements Conditional{
    //must implement BOTH satisfieds in SAME WAY
    @Override
    public abstract boolean satisfied(double obj, ObjectManager objectManager);

    @Override
    public abstract boolean satisfied(ObjectManager objectManager);
}
