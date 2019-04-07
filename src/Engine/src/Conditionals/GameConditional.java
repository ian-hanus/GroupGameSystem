package Conditionals;

import GameObjects.ObjectManager;

public abstract class GameConditional extends Conditional{
    //must implement BOTH satisfieds in SAME WAY

    public GameConditional(boolean required){
        super(required);
    }

    @Override
    public abstract boolean satisfied(double obj, ObjectManager objectManager);

    @Override
    public abstract boolean satisfied(ObjectManager objectManager);
}
