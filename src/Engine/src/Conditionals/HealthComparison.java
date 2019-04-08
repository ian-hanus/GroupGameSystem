package Conditionals;

import ECS.Components.Component;
import ECS.Components.HealthComponent;
import ECS.EntityManager;

public class HealthComparison extends Comparison{
    public HealthComparison(boolean required, int obj, String comparisonOperator, Component compareTo){
        super(required, obj, comparisonOperator, compareTo);
    }

    public HealthComparison(boolean required, int obj, String comparisonOperator){
        super(required, obj, comparisonOperator);
    }

    public HealthComparison(boolean required, String comparisonOperator, Component compareTo){
        super(required, comparisonOperator, compareTo);
    }

    public HealthComparison(boolean required, String comparisonOperator){
        super(required, comparisonOperator);
    }

    public boolean compare(EntityManager entityManager){
        return checkOperator(((HealthComponent) getComponent()).getHealth(), ((HealthComponent) getCompareTo()).getHealth());
    }
}
