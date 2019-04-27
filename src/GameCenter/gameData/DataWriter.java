package GameCenter.gameData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * To add new Data, instead of modifying the string, we just write a new .json file
 */
public class DataWriter {
    private Gson myGson;
    private final String filePath = "data/player_data.json";

    public DataWriter(){
        try {
            myGson = new GsonBuilder().setPrettyPrinting().create();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void writeRating (String rating, int gameIndex) throws FileNotFoundException {
        JSONObject obj = new JSONObject(new Scanner(new File(filePath)).useDelimiter("\\Z").next());
        var gamesList = new ArrayList<DataStruct>();

        var games = obj.getJSONArray("games");
        int counter = 0;
        for(Object o : games) {
            var game = (JSONObject) o;
            if (counter == gameIndex) {
                game.put("rating", rating);
            }
            counter++;
        }

        String gameString = games.toString();
        System.out.println(gameString);

        try {
            myGson.toJson(gameString, new FileWriter(filePath));
        } catch (IOException e) {
            System.out.println("Unable to save game to JSON file.");
        }
    }
}


