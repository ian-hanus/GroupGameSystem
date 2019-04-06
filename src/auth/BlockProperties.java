package auth;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

public class BlockProperties extends ObjectProperties {
    private static final String DEFAULT_STYLESHEET = "../res/authoring.css";
    private Map<String, TextField> myTextFields;

    public BlockProperties(String objectName){
        super();
        propertyNames.addAll(Arrays.asList(new String[]{"X-Coordinate, Y-Coordinate, Z-Index, Image File Name"}));
        myTextFields = new HashMap<>();
    }

    private List<HBox> getTextBoxes(){
        List<HBox> objectRows = new ArrayList<>();
        for(String name:propertyNames){
            HBox textRow = new HBox();
            textRow.getStyleClass().add("text-label");
            Text propertyLabel = new Text(name);
            TextField propertyField = new TextField(name);
            myTextFields.put(name, propertyField);
            textRow.getChildren().addAll(propertyLabel, propertyField);
            objectRows.add(textRow);
        }
        return objectRows;
    }

    public void setValues(){
        for(String s:propertyNames){
            propertyValues.put(s, myTextFields.get(s).getText());
        }
    }
}
