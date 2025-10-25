package apples;

import java.awt.Point;
import logic.StateManager;

/**
 * A red apple. When eaten, the length of the snake increases by one, and the
 * score is similarly increased by one.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class RedApple extends Apple {
    /* --------------- Constructors --------------- */
    /**
     * Create an instance of RedApple with a given position.
     * 
     * @param position Position of the apple.
     */
    public RedApple(Point position) {
        super(position);
        this.imageUrl = getClass().getResource("/ui/redAppleImage.png");
    }

    /**
     * Create an instance of RedApple at a random available space on the board.
     * 
     * @param stateManager The StateManager instance.
     */
    public RedApple(StateManager stateManager) {
        super(stateManager);
        this.imageUrl = getClass().getResource("/ui/redAppleImage.png");
    }

    /* ------------------ Public ------------------ */
    @Override
    public void eat(StateManager stateManager) {
        // Grow the snake and remove the apple.
        stateManager.growSnake();
        stateManager.addScore();
        stateManager.getApples().remove(this);
    }
}
