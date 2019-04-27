package GameCenter.gameData;

import org.json.JSONObject;

import java.io.File;

public class DataStruct {
    private String name, imagePath, description, sourcePath, rating;

    DataStruct(String n, String i, String d, String s, String r) {
        name = n;
        imagePath = i;
        description = d;
        sourcePath = s;
        rating = r;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public double getRating() {
        return Double.parseDouble(rating);
    }

    public void setRating(double value) {
        rating = String.valueOf(value);
    }
}
