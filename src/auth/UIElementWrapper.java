package auth;

import javafx.scene.Node;

public class UIElementWrapper implements UIElement {
    private Node view;
    private String ID;
    public UIElementWrapper(Node n, String ID) {
        this.view = n;
        this.ID = ID;
    }

    public Node getView () {
        return view;
    }

    public String getID() {
        return ID;
    }
}
