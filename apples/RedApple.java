package apples;

import java.awt.Color;
import java.awt.Point;
import logic.StateManager;

public class RedApple extends Apple {
    Color color = new Color(255, 59, 59);

    public RedApple(Point position) {
        super(position);
    }

    public RedApple(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void eat(StateManager stateManager) {
        stateManager.growSnake();
        stateManager.getApples().remove(this);
    }
}
