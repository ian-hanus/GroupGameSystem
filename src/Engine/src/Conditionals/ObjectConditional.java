package Conditionals;

import GameObjects.ObjectManager;

public abstract class ObjectConditional implements Conditional{

    double myObject;

    public ObjectConditional(){myObject = -1;}

    public ObjectConditional(double obj){
        myObject = obj;
    }

    public void setMyObject(double obj){
        myObject = obj;
    }

    @Override
    public abstract boolean satisfied(double obj, ObjectManager objectManager);

    @Override
    public abstract boolean satisfied(ObjectManager objectManager); // TODO: ideally throw error here
}
