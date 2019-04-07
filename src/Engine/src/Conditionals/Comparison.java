package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;
import java.util.Map;

public abstract class Comparison extends ObjectConditional{

    private Map<String, String> myComparisons;
    private double myObject;
    private Map<String, Component> myCompareTo;
    private Map<String, Component> myComponents;

    public Comparison(boolean required, double obj, Map<String, String> comparisons, Map<String, Component> compareTo){
        super(required, obj);
        myComparisons = comparisons;
        myCompareTo = compareTo;
    }

    public Comparison(boolean required, double obj, Map<String, String> comparisonTypes){
        super(required, obj);
        myComparisons = comparisonTypes;
    }

    public Comparison(boolean required, Map<String, String> comparisonTypes, Map<String, Component> compareTo){
        super(required);
        myComparisons = comparisonTypes;
        myCompareTo = compareTo;
    }

    public Comparison(boolean required, Map<String, String> comparisonTypes){
        super(required);
        myComparisons = comparisonTypes;
    }


    private boolean compare() {
        for (String statToBeCompared : myComparisons.keySet())
        if (myComparisons.contains("=") && stat1 == stat2) equals = true;
        if (myComparisonType.contains(">")) return (stat1 < stat2 || equals);
        if (myComparisonType.contains("<")) return (stat1 < stat2 || equals);
        return false;
    }

    @Override
    public boolean satisfied(double obj, ObjectManager objectManager) {
        if (myCompareTo == null){
            myCompareTo = objectManager.getComponents(obj);
        }
        myComponents = objectManager.getComponents(myObject);
        return compare();
    }

    @Override
    public boolean satisfied(ObjectManager objectManager) {
        myComponents = objectManager.getComponents(myObject);
        return compare();
    }

}
