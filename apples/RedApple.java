package apples;

import java.awt.Color;
import java.awt.Point;
import logic.StateManager;

public class RedApple extends Apple {
    public RedApple(Point position) {
        super(position);
        this.color = new Color(255, 59, 59);
    }

    public RedApple(StateManager stateManager) {
        super(stateManager);
        this.color = new Color(255, 59, 59);
    }

    @Override
    public void eat(StateManager stateManager) {
        stateManager.growSnake();
        stateManager.addScore();
        stateManager.getApples().remove(this);
    }
}
