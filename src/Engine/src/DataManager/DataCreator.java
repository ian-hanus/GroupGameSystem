
package Engine.src.DataManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DataCreator implements DataManager.DataInterface {
    private Gson myGson;

    public DataCreator(){
        myGson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveGame(String fileName, DataManager.GameInformation gameInformation){
        try {
            myGson.toJson(gameInformation, new FileWriter("./" + fileName));
        } catch (IOException e) {
            System.out.println("Unable to save game to JSON file.");
        }
    }

    public DataManager.GameInformation loadGame(String fileName){
        try {
            DataManager.GameInformation gameInformation = myGson.fromJson(new FileReader(fileName), DataManager.GameInformation.class);
            return gameInformation;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load Engine.src.DataManager.GameInformation from JSON file " + fileName);
        }
        return new DataManager.GameInformation(null, null, null);
    }
}
