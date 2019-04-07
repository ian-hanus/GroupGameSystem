package Conditionals;

import GameObjects.GameObject;

public class HealthComparison extends Comparison{

    public HealthComparison(String comparisonType, double compareTo){
        super(comparisonType, compareTo);
    }

    protected double setStat(double obj){
        return obj.getHealth();
    }
}
