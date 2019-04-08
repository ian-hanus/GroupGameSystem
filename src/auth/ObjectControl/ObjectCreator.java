package auth.ObjectControl;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

public class ObjectCreator {
    private Map<String, String> myResponses;
    private Map<String, TextField> myTextFields;
    private Map<String, CheckBox> myCheckBoxes;
    private Map<String, String> myDisplayStrings;
    private ObjectManager myManager;

    public ObjectCreator(ObjectManager objectManager){
        myDisplayStrings = new HashMap<>();
        String[] componentTags = new String[]{"Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7",
                "Test8", "Test9", "Test10"};
        String[] componentDisplay = new String[]{"Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7",
                "Test 8", "Test 9", "Test 10"};
        for(int k = 0; k < componentDisplay.length; k++){
            myDisplayStrings.put(componentTags[k], componentDisplay[k]);
        }
        myResponses = new HashMap<>();
        myTextFields = new HashMap<>();
        myCheckBoxes = new HashMap<>();
        myManager = objectManager;
    }

    public Map<String, String> getResponses(){
        return Collections.unmodifiableMap(myResponses);
    }

    private List<HBox> getTextBoxes(){
        List<HBox> objectRows = new ArrayList<>();
        for(String key:myDisplayStrings.keySet()){
            HBox textRow = new HBox();
            Text propertyLabel = new Text(myDisplayStrings.get(key));
            propertyLabel.setFill(Color.WHITE);
            TextField propertyField = new TextField(key);
            myTextFields.put(key, propertyField);
            CheckBox activateComponent = new CheckBox();
            textRow.getChildren().addAll(activateComponent, propertyLabel, propertyField);
            textRow.getStyleClass().add("properties-row");
            objectRows.add(textRow);
            myCheckBoxes.put(key, activateComponent);
        }
        return objectRows;
    }

    public VBox getView(){
        VBox totalProperties = new VBox();
        VBox visual = new VBox();
        ScrollPane properties = new ScrollPane();
        properties.getStyleClass().add("properties-scrollpane");

        Label titleLabel = new Label("Object Creator");
        titleLabel.getStyleClass().add("properties-label");

        List<HBox> propertyRows = getTextBoxes();
        for(HBox h:propertyRows){
            visual.getChildren().add(h);
        }

        HBox buttonRow = new HBox();
        buttonRow.getStyleClass().add("properties-row");
        Button saveButton = new Button("Save New Object");
        saveButton.getStyleClass().add("properties-button");
        saveButton.setOnAction(e -> saveObject());
        Button hotkeyButton = new Button("Hotkey Mappings");
        hotkeyButton.getStyleClass().add("properties-button");
        buttonRow.getChildren().addAll(hotkeyButton, saveButton);

        properties.setContent(visual);
        totalProperties.getChildren().addAll(titleLabel, properties, buttonRow);
        return totalProperties;
    }

    private void saveObject(){
        Map<String, Integer> objectComponents = new HashMap<>();
        for(String key:myCheckBoxes.keySet()){
            if(myCheckBoxes.get(key).isSelected()){
                objectComponents.put(key, Integer.parseInt(myTextFields.get(key).getText()));
            }
        }
        myManager.writeUniqueObject(objectComponents);
    }
}
