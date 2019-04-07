package Conditionals;

import GameObjects.GameObject;
import GameObjects.ObjectManager;

public abstract class Comparison implements Conditional{

    private String myComparisonType;
    private double myStat;
    private double myCompareTo;

    public Comparison(String comparisonType, double compareTo){
        myComparisonType = comparisonType;
        myCompareTo = compareTo;
    }

    abstract double setStat(double obj);

    protected boolean compare(double stat1, double stat2) {
        boolean equals = false;
        if (myComparisonType.contains("=") && stat1 == stat2) equals = true;
        if (myComparisonType.contains(">")) return (stat1 < stat2 || equals);
        if (myComparisonType.contains("<")) return (stat1 < stat2 || equals);
        return false;
    }

    @Override
    public boolean satisfied(double obj, ObjectManager objectManager) {
        return false;
    }

    @Override
    public boolean satisfied(double obj1, double obj2) {
        myStat = setStat(obj1);
        return compare(myStat, myCompareTo);
    }

}
