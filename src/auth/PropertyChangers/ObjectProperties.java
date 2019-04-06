package auth.PropertyChangers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

public abstract class ObjectProperties {
    protected List<String> propertyNames;
    protected Map<String, String> propertyValues;
    protected Map<String, String> responses;
    protected Map<String, TextField> textFields;
    protected String title;

    public ObjectProperties(String objectName){
        propertyNames = new ArrayList<>();
        propertyValues = new HashMap<>();
        responses = new HashMap<>();
        textFields = new HashMap<>();
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
            TextField propertyField = new TextField(name);
            textFields.put(name, propertyField);
            textRow.getChildren().addAll(propertyLabel, propertyField);
            objectRows.add(textRow);
        }
        return objectRows;
    }

    public FlowPane getVisualization(){
        FlowPane visual = new FlowPane();
        Label titleLabel = new Label(title);
        visual.getChildren().add(titleLabel);
        List<HBox> propertyRows = getTextBoxes();
        for(HBox h:propertyRows){
            visual.getChildren().add(h);
        }
        return visual;
    }
}
