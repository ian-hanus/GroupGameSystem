
package Engine.src.DataManager;

public interface DataInterface {
    void saveGame(String fileName, DataManager.GameInformation gameInformation);

    DataManager.GameInformation loadGame(String fileName);
}
