//Part of Example 5: Make the hero jump
public class Hero extends Character {
    public void jump() {
        setCharacterState(JUMPING);    // change the state of the hero to be jumping, represented by the int constant JUMPING (allows Player to change the hero sprite during jumps)
        setYVelocity(JUMP_SPEED);   // jumping just means suddenly moving upwards at a specified speed
    }
}