package ECS.Components;

public class HealthComponent extends Component {
    private int myHealth;
    //private int maxHealth (do we want to cap the health of something)

    public HealthComponent(int health) {
        myHealth = health;
    }
}
