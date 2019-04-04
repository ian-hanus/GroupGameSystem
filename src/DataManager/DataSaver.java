package DataManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;

public class DataSaver {

    public DataSaver(String fileName, Collection<Object> objects){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(objects);
    }
}
