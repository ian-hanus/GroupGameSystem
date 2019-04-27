package GameCenter.gameData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * To add new Data, instead of modifying the string, we just write a new .json file
 */
public class DataWriter {
    private Gson myGson;
    private final String filePath = "data/player_data.json";
    private File jsonFile = new File(filePath);

    public DataWriter(){
        try {
            myGson = new GsonBuilder().setPrettyPrinting().create();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void writeRating (String rating, int gameIndex) throws FileNotFoundException {
        JSONObject obj = new JSONObject(new Scanner(new File(filePath)).useDelimiter("\\Z").next());

        var games = obj.getJSONArray("games");
        int counter = 0;
        for(Object o : games) {
            var game = (JSONObject) o;
            if (counter == gameIndex) {
                game.put("rating", rating);
            }
            counter++;
        }

        JsonParser parser = new JsonParser();
        JsonElement je = parser.parse(obj.toString());
        String gameString = myGson.toJson(je);
        System.out.println(gameString);

        if(jsonFile.exists()){
            jsonFile.delete();
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(filePath);
                writer.write(gameString);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFavorite (String favorite) throws FileNotFoundException {
        JSONObject obj = new JSONObject(new Scanner(new File(filePath)).useDelimiter("\\Z").next());
        var games = obj.getJSONArray("games");
        for (Object o : games) {
            var game = (JSONObject) o;
            game.put("favorite", favorite);
        }
    }

}


