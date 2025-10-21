package apples;

import java.awt.Color;
import java.awt.Point;
import logic.StateManager;

public class BlackApple extends Apple {
    public BlackApple(Point position) {
        super(position);
        color = new Color(64, 64, 64);
    }

    public BlackApple(StateManager stateManager) {
        super(stateManager);
        color = new Color(64, 64, 64);
    }

    @Override
    public void eat(StateManager stateManager) {
        stateManager.setGameOver(true); // Kill the snake
        stateManager.getApples().remove(this);
    }
}
