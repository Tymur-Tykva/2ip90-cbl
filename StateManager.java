import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

public class StateManager {
    private static final Point INITIAL_SNAKE_POSITION = new Point(4, 4);
    private static final int INITIAL_SNAKE_LENGTH = 3;
    private static final Direction INITIAL_SNAKE_DIRECTION = Direction.R;

    private Deque<Point> snake; // Front of the queue is the head.

    public StateManager() {
        // Create a dequeue representing the snake, and create the initial points.
        this.snake = new ArrayDeque<>();

        // Add the initial point, and then add the body of the snake behind it.
        Point snakePoint = new Point(INITIAL_SNAKE_POSITION.x, INITIAL_SNAKE_POSITION.y);
        for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
            this.snake.add(snakePoint);
            snakePoint = updateWithDirection(snakePoint, INITIAL_SNAKE_DIRECTION.GetOpposite());
        }
    }

    public Deque<Point> getSnake() {
        return snake;
    }

    /*
     * Update the position of a point based on a given direction. Used to update the
     * position of the snake.
     */
    private Point updateWithDirection(Point position, Direction direction) {
        int x = position.x;
        int y = position.y;

        switch (direction) {
            case U:
                y -= 1;
                break;
            case D:
                y += 1;
                break;
            case L:
                x -= 1;
                break;
            case R:
                x += 1;
                break;
            // Unreachable since only 4 possible directions; all cases considered.
            default:
                break;
        }

        Point newPosition = new Point(x, y);
        return newPosition;
    }

    // TODO: Add method to validate if a point is in bounds.
}
