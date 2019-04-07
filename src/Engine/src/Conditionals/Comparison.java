package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

import java.util.List;

public abstract class Comparison extends ObjectConditional{

    private List<String> myComparisonTypes;
    private double myObject;
    private List<Component> myCompareTo;
    private List<Component> myComponents;

    public Comparison(List<String> comparisonTypes, double obj, List<Component> compareTo){
        super(obj);
        myComparisonTypes = comparisonTypes;
        myCompareTo = compareTo;
    }

    public Comparison(List<String> comparisonTypes, List<Component> compareTo){
        super();
        myComparisonTypes = comparisonTypes;
        myCompareTo = compareTo;
    }

    public Comparison(List<String> comparisonTypes){
        super();
        myComparisonTypes = comparisonTypes;
    }

    public Comparison(List<String> comparisonTypes, double obj){
        super(obj);
        myComparisonTypes = comparisonTypes;
    }

    private boolean compare() {
        boolean equals = false;
        if (myComparisonType.contains("=") && stat1 == stat2) equals = true;
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
