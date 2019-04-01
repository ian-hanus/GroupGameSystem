//Part of Example 5: Make the hero jump
public class Controller {
    public void processUserInput(KeyCode pressed) {
        if (pressed.equals(JUMP_BUTTON)) {    // JUMP_BUTTON is a constant that represents the binded jump key
            hero.jump();
        }

        // this method will have additional logic for other key presses
    }
}