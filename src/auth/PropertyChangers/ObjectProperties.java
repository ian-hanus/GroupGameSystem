package auth.PropertyChangers;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

public abstract class ObjectProperties {
    protected List<String> propertyNames;
    protected Map<String, String> propertyValues;
    protected Map<String, String> responses;
    protected Map<String, TextField> textFields;
    protected Map<String, String> hotkeyMappings;
    protected String title;

    public ObjectProperties(String objectName){
        propertyNames = new ArrayList<>();
        propertyValues = new HashMap<>();
        responses = new HashMap<>();
        textFields = new HashMap<>();
        hotkeyMappings = new HashMap<>();
        title = objectName;
    }

    public List<String> getPropertyNames(){
        return Collections.unmodifiableList(propertyNames);
    }

    public Map<String, String> getResponses(){
        return Collections.unmodifiableMap(responses);
    }

    public Map<String, String> getPropertyValues(){
        return Collections.unmodifiableMap(propertyValues);
    }

    public void setValues(){
        for(String s:propertyNames){
            propertyValues.put(s, textFields.get(s).getText());
        }
    }

    private List<HBox> getTextBoxes(){
        List<HBox> objectRows = new ArrayList<>();
        for(String name:propertyNames){
            HBox textRow = new HBox();
            Text propertyLabel = new Text(name);
            propertyLabel.setFill(Color.WHITE);
            TextField propertyField = new TextField(name);
            textFields.put(name, propertyField);
            CheckBox activateComponent = new CheckBox();
            textRow.getChildren().addAll(activateComponent, propertyLabel, propertyField);
            textRow.getStyleClass().add("properties-row");
            objectRows.add(textRow);
        }
        return objectRows;
    }

    public VBox getVisualization(){
        VBox totalProperties = new VBox();
        VBox visual = new VBox();
        ScrollPane properties = new ScrollPane();
        properties.getStyleClass().add("properties-scrollpane");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("properties-label");
        List<HBox> propertyRows = getTextBoxes();
        for(HBox h:propertyRows){
            visual.getChildren().add(h);
        }
        properties.setContent(visual);
        totalProperties.getChildren().addAll(titleLabel, properties);
        return totalProperties;
    }
}
