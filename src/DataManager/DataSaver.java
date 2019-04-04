package DataManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class DataSaver {
    public DataSaver(String fileName, Collection<Object> objects){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            gson.toJson(objects, new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Unable to save game to JSON file.");
        }
    }
}
