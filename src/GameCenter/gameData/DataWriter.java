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
    public void writeRating (String rating, int gameIndex) throws FileNotFoundException {
        JSONObject obj = new JSONObject(new Scanner(new File(filePath)).useDelimiter("\\Z").next());
        var games = obj.getJSONArray("games");
        int counter = 0;
        for(Object o : games) {
            if (counter == gameIndex) {
                var game = (JSONObject) o;
                game.put("rating", rating);
            }
            counter++;
        }
    }
}
