package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;
import java.util.Map;

public abstract class Comparison extends ObjectConditional{

    private String myComparisonOperator;
    private double myObject;
    private Component myCompareTo;
    private Component myComponent;
    private String myComponentName;

    public Comparison(boolean required, String componentName, double obj, String comparisonOperator, Component compareTo){
        super(required, obj);
        myComparisonOperator = comparisonOperator;
        myCompareTo = compareTo;
        myComponentName = componentName;
    }

    public Comparison(boolean required, String componentName, double obj, String comparisonOperator){
        super(required, obj);
        myComparisonOperator = comparisonOperator;
        myComponentName = componentName;
    }

    public Comparison(boolean required, String componentName, String comparisonOperator, Component compareTo){
        super(required);
        myComparisonOperator = comparisonOperator;
        myCompareTo = compareTo;
        myComponentName = componentName;
    }

    public Comparison(boolean required, String componentName, String comparisonOperator){
        super(required);
        myComparisonOperator = comparisonOperator;
        myComponentName = componentName;
    }

    protected boolean checkOperator(String value1, String value2) {
        if (value1.equals(value2) && myComponentName.contains("=")) return true;
        return false;
    }

    protected boolean checkOperator(double value1, double value2) {
        boolean equals = false;
        if (value1 == value2 && myComponentName.contains("=")) equals = true;
        if (myComponentName.contains(">")) return value1 > value2 || equals;
        if (myComponentName.contains(">")) return value1 > value2 || equals;
        return equals;
    }

    abstract boolean compare();

    @Override
    public boolean satisfied(double obj, ObjectManager objectManager) {
        if (myCompareTo == null){
            myCompareTo = objectManager.getComponent(myComponentName);
        }
        myComponent = objectManager.getComponent(myComponentName);
        return compare();
    }

    @Override
    public boolean satisfied(ObjectManager objectManager) {
        myComponent = objectManager.getComponent(myComponentName);
        return compare();
    }

    protected Component getCompareTo(){
        return myCompareTo;
    }

    protected Component getComponent(){
        return myComponent;
    }
}
