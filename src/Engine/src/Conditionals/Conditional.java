package Conditionals;

import GameObjects.ObjectManager;

public abstract class Conditional {

    protected boolean Required;

    public Conditional(boolean required){
        Required = required;
    }

    abstract boolean satisfied(ObjectManager objectManager);
    abstract boolean satisfied(double obj, ObjectManager objectManager);

    public boolean required(){
        return Required;
    }

    abstract Conditional copy();
}
