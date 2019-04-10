package Triggers.Conditionals;

import ECS.EntityManager;

public abstract class Conditional {

    protected boolean Required;

    public Conditional(boolean required){
        Required = required;
    }

    public abstract boolean satisfied(EntityManager objectManager);
    public abstract boolean satisfied(int obj, EntityManager objectManager);

    public boolean required(){
        return Required;
    }

    public abstract Conditional copy();
}
