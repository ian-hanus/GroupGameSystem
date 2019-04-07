package Conditionals;

public class HealthComparison extends Comparison{
    public HealthComparison(boolean required, String componentName, double obj, String comparisonOperator, Component compareTo){
        super(required, componentName, obj, comparisonOperator, compareTo);
    }

    public HealthComparison(boolean required, String componentName, double obj, String comparisonOperator){
        super(required, componentName, obj, comparisonOperator);
    }

    public HealthComparison(boolean required, String componentName, String comparisonOperator, Component compareTo){
        super(required, componentName, comparisonOperator, compareTo);
    }

    public HealthComparison(boolean required, String componentName, String comparisonOperator){
        super(required, componentName, comparisonOperator);
    }

    public boolean compare(){
        return checkOperator(((HealthComponent) getComponent()).getHealth(), ((HealthComponent) getCompareTo()).getHealth());
    }
}
