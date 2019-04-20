package Player.src.Features.ScrollableWindows;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A Heads Up Display utility with customizable data.
 * Declare the name of each data type for display purposes,
 * and provide an array of corresponding data Object to display next to each name.
 * Update the HUD by providing another array of data Objects.
 * In a game loop class, we recommend implementing a getHUDValues method and calling HUD.update(getHUDValues)
 * at the end of each iteration.
 *
 * @author Hunter Gregory
 * @author Carter Gay
 */
public class HUD {
    private String[] myNames;
    private HBox myHBox;
    private ScrollPane myScrollPane;
    private Label myLabel;

    public HUD(double width, double height, String[] names, Object[] values) throws IncompatibleArgumentLengthException {
        myNames = names;
        myHBox = new HBox();
        myLabel = new Label();
        createScrollPane(width, height);
        myHBox.getChildren().add(myScrollPane);
        update(values);
    }

    public Node getNode() {
        return myHBox;
    }

    public void update(Object[] values) throws IncompatibleArgumentLengthException {
        if (values.length != myNames.length)
            throw new IncompatibleArgumentLengthException();
        clearText();
        for (int k=0; k<myNames.length; k++) {
            addText(myNames[k] + ": " + values[k].toString());
        }
    }

    private void createScrollPane(double width, double height) {
        myScrollPane = new ScrollPane(myLabel);
        setDimensions(myScrollPane, width, height);
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setDisable(false);
    }

    private void setDimensions(Region region, double width, double height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
        region.setMinWidth(width);
        region.setMaxWidth(width);
    }

    private void clearText() {
        myLabel.setText("");
    }

    private void addText(String newString) {
        myLabel.setText(myLabel.getText() + newString + "\n");
    }
}
