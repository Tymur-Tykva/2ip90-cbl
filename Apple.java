import java.awt.Point;

/*
 * General parent class for all apples. Defines base methods.
 */
public class Apple {
    private Point position;

    public Apple(Point position) {
        this.position = position;
    }

    public Apple(StateManager stateManager) {
        // TODO: Implement apple spawning at random position not on the snake.
    }

    public Point getPosition() {
        return position;
    }

    /*
     * Empty method to be overriden by child classes.
     * Triggered when the StateManager finds that the snake has eaten the apple.
     * The apple is then removed by the StateManager.
     */
    public void eat(StateManager stateManager) {
    }
}