package auth;

import javafx.scene.text.Text;

import java.util.*;

public abstract class ObjectProperties {
    protected List<String> propertyNames;
    protected List<Text> textValues;
    protected Map<String, String> propertyValues;
    protected Map<String, String> responses;

    public ObjectProperties(){
        propertyNames = new ArrayList<>();
        propertyValues = new HashMap<>();
        responses = new HashMap<>();
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

    public abstract void setValues();
}
