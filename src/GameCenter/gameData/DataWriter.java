package GameCenter.gameData;

import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataWriter {
    private final String filePath = "data/player_data.json";

    // TODO: only change rating of one game
    public void writeRating (String rating) throws FileNotFoundException {
        JSONObject obj = new JSONObject(new Scanner(new File(filePath)).useDelimiter("\\Z").next());
        var games = obj.getJSONArray("games");
        for(Object o : games) {
            var game = (JSONObject) o;
            game.put("rating", rating);
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
