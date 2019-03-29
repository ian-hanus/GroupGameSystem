
public class DataManager{

    public static Game loadGame(String file){
        return new Game;
    }

    public static void saveGame(List<Block> blockList, List<Enemy> enemyList){

    }

    //Example 3: Save a game to a JSON file
    public void saveGame(Game myGame) {
        Gson gson = new Gson();
        Game savedGame = myGame;
        gson.toJson(myGame)
    }

}