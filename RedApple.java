import java.awt.Color;
import java.awt.Point;

public class RedApple extends Apple {
    Color color = Color.RED;

    public RedApple(Point position) {
        super(position);
    }

    public RedApple(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void eat(StateManager stateManager) {
        stateManager.growSnake();
    }
}
