import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class StateManager {
    private InputBuffer inputBuffer;

    private static final Point INITIAL_SNAKE_POSITION = new Point(4, 4);
    private static final int INITIAL_SNAKE_LENGTH = 3;

    private Deque<Point> snake; // Front of the queue is the head.
    private ArrayList<Apple> apples;

    private Direction snakeDirection = Direction.R;
    private boolean growSnake = false; // When true, snake will grow next update.

    private boolean gameOver = false;

    /* ---------------- Constructor --------------- */
    public StateManager(InputBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;

        this.apples = new ArrayList<>();
        this.snake = new ArrayDeque<>();

        // Add the 'head' point of the snake, and then the body of the snake behind it.
        Point snakePoint = new Point(INITIAL_SNAKE_POSITION.x, INITIAL_SNAKE_POSITION.y);
        for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
            this.snake.add(snakePoint);
            snakePoint = updateWithDirection(snakePoint, snakeDirection.GetOpposite());
        }
    }

    /* ------------------ Public ------------------ */
    public void update() {
        System.out.println("=== Update ===");
        System.out.println("snake: ");
        for (Point point : snake) {
            System.out.print(point + " ");
        }

        // Update stored snake direction.
        Direction inputDirection = inputBuffer.getDirection();
        if (inputDirection != null) {
            snakeDirection = inputDirection;
        }

        // Update the snake's position.
        Point head = snake.peekFirst();
        Point newHead = updateWithDirection(head, snakeDirection);
        snake.addFirst(newHead);

        // Remove the tail of the snake if not growing.
        if (!growSnake) {
            snake.removeLast();
        }

        // Check for final collisions.
        if (isFinalColliding()) {
            System.out.println("Game over");
            gameOver = true;
        }

        // Check for apple collisions.
        Apple apple = isAppleColliding();

        if (apple != null) {
            // TODO: Handle the apple collision.
            System.out.println("Apple collision");
        }
    }

    /* ------------------ Getters ----------------- */
    public Deque<Point> getSnake() {
        return snake;
    }

    public ArrayList<Apple> getApples() {
        return apples;
    }

    public boolean isGameOver() {
        return gameOver;
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

    private boolean isFinalColliding() {
        return isSnakeSelfColliding() || !isInBounds(snake.peekFirst());
    }

    private Apple isAppleColliding() {
        Point head = snake.peekFirst();

        for (Apple apple : apples) {
            if (apple.getPosition().equals(head)) {
                return apple;
            }
        }

        return null;
    }

    private boolean isSnakeSelfColliding() {
        Set<Point> snakePoints = new HashSet<Point>();

        for (Point point : snake) {
            snakePoints.add(point);
        }

        return snakePoints.size() != snake.size();
    }

    private boolean isInBounds(Point position) {
        return position.x >= 0 && position.x < Config.GRID_WIDTH
                && position.y >= 0 && position.y < Config.GRID_HEIGHT;
    }
}
