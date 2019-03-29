package Player;

/**
 * Menu user can interact with while in-game with capability to load/save game and confugure certain settings.
 */
public interface GameMenu {
    /**
     * Resets the current game to the beginning of the game.
     */
    void newGame();

    /**
     * Loads a previously-saved game .json file.
     * @param fileName name of save
     */
    void loadGame(String fileName);

    /**
     * Saves the current progress up to last checkpoint as .json file.
     */
    void saveGame();

    /**
     * Resets the current game to the last checkpoint by loading a .json file.
     */
    void restart();

    /**
     * Exits the game and returns to main menu
     */
    void returnToMainMenu();
}