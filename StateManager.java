import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class StateManager {
    private InputBuffer inputBuffer;

    private static final Point INITIAL_SNAKE_POSITION = new Point(4, 4);
    private static final int INITIAL_SNAKE_LENGTH = 3;
    private static final Direction INITIAL_SNAKE_DIRECTION = Direction.R;

    private Deque<Point> snake; // Front of the queue is the head.
    private ArrayList<Apple> apples;

    /* ---------------- Constructor --------------- */
    public StateManager(InputBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;

        this.apples = new ArrayList<>();
        this.snake = new ArrayDeque<>();

        // Add the 'head' point of the snake, and then the body of the snake behind it.
        Point snakePoint = new Point(INITIAL_SNAKE_POSITION.x, INITIAL_SNAKE_POSITION.y);
        for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
            this.snake.add(snakePoint);
            snakePoint = updateWithDirection(snakePoint, INITIAL_SNAKE_DIRECTION.GetOpposite());
        }
    }

    /* ------------------ Public ------------------ */
    public Deque<Point> getSnake() {
        return snake;
    }

    public ArrayList<Apple> getApples() {
        return apples;
    }

    public void update() {
        Direction direction = inputBuffer.getDirection();
    }

    /* ------------------ Private ----------------- */
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
