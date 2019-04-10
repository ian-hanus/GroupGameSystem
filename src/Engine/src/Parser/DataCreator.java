<<<<<<< HEAD:src/Engine/src/Parser/DataCreator.java
package Parser;
=======

package Main;
>>>>>>> f69bbaed5ff52acfe6b33c6cec697d06d4358748:src/Engine/src/DataManager/Main/DataCreator.java

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DataCreator implements DataInterface {
    private Gson myGson;

    public DataCreator(){
        myGson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveGame(String fileName, GameInformation gameInformation){
        try {
            myGson.toJson(gameInformation, new FileWriter("./" + fileName));
        } catch (IOException e) {
            System.out.println("Unable to save game to JSON file.");
        }
    }

    public GameInformation loadGame(String fileName){
        try {
            GameInformation gameInformation = myGson.fromJson(new FileReader(fileName), GameInformation.class);
            return gameInformation;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load Parser.GameInformation from JSON file " + fileName);
        }
        return new GameInformation(null, null, null);
    }
}
