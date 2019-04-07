package Conditionals;

import GameObjects.ObjectManager;

public abstract class ObjectConditional extends Conditional{

    double myObject;

    public ObjectConditional(boolean required){
        super(required);
        myObject = -1;
    }

    public ObjectConditional(boolean required, double obj){
        super(required);
        myObject = obj;
    }

    public void setMyObject(double obj){
        myObject = obj;
    }

    @Override
    public abstract boolean satisfied(double obj, ObjectManager objectManager);

    @Override
    public abstract boolean satisfied(ObjectManager objectManager); // TODO: ideally throw error here

    public double getMyObj(){
        return myObject;
    }

}
