package logic;

import apples.Apple;
import apples.RedApple;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import utils.Config;
import utils.Direction;

public class StateManager {
    private InputBuffer inputBuffer;

    private Deque<Point> snake; // Front of the queue is the head.
    private ArrayList<Apple> apples;

    private Direction snakeDirection = Direction.R;
    private boolean growSnake = false; // When true, snake will grow next update.

    private boolean gameOver = false;

    /* ---------------- Constructor --------------- */
    public StateManager(InputBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;

        this.apples = new ArrayList<Apple>();
        this.snake = new ArrayDeque<Point>();

        // Add the 'head' point of the snake, and then the body of the snake behind it.
        Point snakePoint = (Point) Config.INITIAL_SNAKE_POSITION.clone();
        for (int i = 0; i < Config.INITIAL_SNAKE_LENGTH; i++) {
            this.snake.add(snakePoint);
            snakePoint = updateWithDirection(snakePoint, snakeDirection.getOpposite());
        }

        // Add the initial apples.
        for (Point position : Config.INITIAL_APPLE_POSITIONS) {
            this.apples.add(new RedApple(position));
        }
    }

    /* ------------------ Public ------------------ */
    public void update() {
        if (inputBuffer.isPaused()) {
            return;
        }

        // System.out.println("=== Update ===");
        // System.out.print("snake: ");
        // for (Point point : snake) {
        // System.out.print(point + " ");
        // }
        // System.out.println();
        // System.out.print("apples: ");
        // for (Apple apple : apples) {
        // System.out.print(apple.getPosition() + " ");
        // }
        // System.out.println();
        // System.out.println("growSnake: " + growSnake);

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
        } else {
            growSnake = false;
        }

        // Check for final collisions.
        if (isFinalColliding()) {
            System.out.println("Game over");
            gameOver = true;
        }

        // Check for apple collisions.
        Apple apple = isAppleColliding();

        if (apple != null) {
            System.out.println("Apple collision: " + apple.getPosition());
            apple.eat(this);
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

    /* ------------------ Setters ----------------- */
    public void growSnake() {
        this.growSnake = true;
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
