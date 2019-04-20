package Player.src.Features.ScrollableWindows;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Arrays;

public class HUD {
    private ArrayList<HUDItem> myItems;
    private HBox myHBox;
    private ScrollPane myScrollPane;
    protected Button myButton;

    public HUD(HUDItem[] hudItems) {
        myItems = new ArrayList(Arrays.asList(hudItems));
        myHBox = new HBox();
        myButton = new Button();
        //myButton.setEditable(false);
        myScrollPane = new ScrollPane(myButton);
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setDisable(false);
        myHBox.getChildren().add(myScrollPane);
        update();
    }

    public Node getMainComponent() {
        return myHBox;
    }

    public void update() {
        clearText();
        for (HUDItem item : myItems) {
            addText(item.getCurrentString());
        }
    }

    private void clearText() {
        myButton.setText("");
    }

    private void addText(String newString) {
        myButton.setText(newString);
    }
}
