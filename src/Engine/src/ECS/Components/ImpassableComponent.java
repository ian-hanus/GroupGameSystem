package ECS.Components;

public class ImpassableComponent extends Component {
    private boolean myImpassable;

    private ImpassableComponent(boolean impassable) {
        myImpassable = impassable;
    }

    public boolean getImpassable() {
        return myImpassable;
    }

    public void setImpassable(boolean impassible) {
        myImpassable = impassible;
    }
}
