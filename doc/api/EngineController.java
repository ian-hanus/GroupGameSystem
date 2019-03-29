package Engine;

public interface EngineController {
    /**
     * Will check the code with a map of key inputs to actions and perform the necessary action - such as moving the
     * player character
     * @param code Key input used to address key strokes from the player that is outside of the game loop
     */
    void actHero(KeyCode code);
    /**
     * Updates all the GameObjects based on collisions, movement, and basic Physics
     * @return list of all the updated GameObjects
     */
    List<GameObjects> getNextState();
}