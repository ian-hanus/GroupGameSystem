package auth.ObjectControl;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class ObjectCreator {
    private Map<String, String> myResponses;
    private Map<String, TextField> myTextFields;
    private Map<String, CheckBox> myCheckBoxes;
    private Map<String, String> myDisplayStrings;
    private ObjectManager myManager;
    private FileChooser myImageChooser;
    private String myImageName;

    public ObjectCreator(ObjectManager objectManager){
        myImageChooser = new FileChooser();
        myImageChooser.setTitle("Choose a file for turtle image");
        myImageChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        myImageChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG image file", "*.png", "*.jpg"));
        myDisplayStrings = new TreeMap<>();
        var resource = ResourceBundle.getBundle("Components");
        for(var key:Collections.list(resource.getKeys())){
            myDisplayStrings.put(key, resource.getString(key));
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
            TextField propertyField = new TextField();
            myTextFields.put(key, propertyField);
            CheckBox activateComponent = new CheckBox();
            textRow.getChildren().addAll(activateComponent, propertyLabel, propertyField);
            if(key.equals("ImageFile")){
                Button loadImageButton = new Button("Choose Image");
                loadImageButton.setOnAction(e -> chooseImage());
                textRow.getChildren().remove(propertyField);
                textRow.getChildren().add(loadImageButton);
            }
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
        Map<String, String> objectComponents = new HashMap<>();
        for(String key:myCheckBoxes.keySet()){
            if(myCheckBoxes.get(key).isSelected()){
                if(!key.equals("ImageFile")) {
                    objectComponents.put(key, myTextFields.get(key).getText());
                }
                else{
                    objectComponents.put(key, myImageName);
                }
            }
        }
        myManager.writeUniqueObject(objectComponents);
    }

    private String chooseImage(){
        File imageFile = myImageChooser.showOpenDialog(new Stage());
        myImageName = imageFile.toString();
        System.out.println(myImageName);
        return imageFile.toString();
    }
}
