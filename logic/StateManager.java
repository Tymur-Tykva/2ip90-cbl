package logic;

import apples.Apple;
import apples.BlackApple;
import apples.RedApple;
import apples.YellowApple;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import utils.Config;
import utils.Direction;

public class StateManager {
    private InputBuffer inputBuffer;

    private Deque<Point> snake; // Front of the queue is the head.
    private ArrayList<Apple> apples;

    private Direction snakeDirection = Direction.R;
    private boolean growSnake = false; // When true, snake will grow next update.
    private int score = 0;

    private boolean gameOver = false;

    /* ---------------- Constructor --------------- */
    public StateManager(InputBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;
        this.reset();
    }

    /* ------------------ Public ------------------ */
    public void reset() {
        this.apples = new ArrayList<Apple>();
        this.snake = new ArrayDeque<Point>();

        // Add the 'head' point of the snake, and then the body of the snake behind it.
        Point snakePoint = (Point) Config.INITIAL_SNAKE_POSITION.clone();
        for (int i = 0; i < Config.INITIAL_SNAKE_LENGTH; i++) {
            this.snake.addLast(snakePoint);
            snakePoint = updateWithDirection(snakePoint, snakeDirection.getOpposite());
        }

        // Add the initial apples.
        for (Point position : Config.INITIAL_APPLE_POSITIONS) {
            this.apples.add(new RedApple(position));
        }
    }

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

        // Update and spawn apples.
        updateApples();
        spawnApples();
    }

    /* ------------------ Getters ----------------- */
    public Deque<Point> getSnake() {
        return snake;
    }

    public Direction getSnakeDirection() {
        return snakeDirection;
    }

    public ArrayList<Apple> getApples() {
        return apples;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    /* ------------------ Setters ----------------- */
    public void growSnake() {
        this.growSnake = true;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void addScore() {
        this.score += 1;
    }

    // public void addScore(int score) {
    // this.score += score;
    // }

    // public void setScore(int score) {
    // this.score = score;
    // }

    public void setSnake(Deque<Point> snake) {
        this.snake = snake;
    }

    public void setSnake(Deque<Point> snake, Direction direction) {
        this.snake = snake;
        this.snakeDirection = direction;
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

    /**
     * Spawns apples based on the score and the amount of available spaces on the
     * board.
     * 
     * The target apple amounts are specified in each if statement, and the
     * breakpoints are specified in the config.
     */
    private void spawnApples() {
        int availableSpaces = (Config.GRID_WIDTH * Config.GRID_HEIGHT) - snake.size();
        boolean hasAvailableSpaces = availableSpaces >= Config.APPLE_AVAILABLE_SPACES;

        if (!hasAvailableSpaces) {
            // Aim to have 1 apple on the board:
            // - 1 Red

            if (apples.size() == 0) {
                apples.add(new RedApple(this));
            }
        } else if (score >= Config.SCORE_BREAKPOINTS[1]) {
            // Aim to have 2 apples on the board:
            // - 1 Black
            // - 1 Red/Yellow (50/50 chance of either)
            // If the amount of available spaces is insufficient, spawn 2 red/yellow apples
            // instead.

            boolean hasBlack = false;
            boolean hasRedOrYellow = false;

            for (Apple apple : apples) {
                if (apple instanceof BlackApple) {
                    hasBlack = true;
                } else if (apple instanceof RedApple || apple instanceof YellowApple) {
                    hasRedOrYellow = true;
                }
            }

            while (apples.size() < 2) {
                if (!hasBlack) {
                    apples.add(new BlackApple(this));
                    hasBlack = true;
                    continue;
                }

                if (!hasRedOrYellow) {
                    Random random = new Random(System.currentTimeMillis() + 14574682L);
                    float randomChoice = random.nextFloat();

                    Apple newApple = null;
                    if (randomChoice <= 0.5f) {
                        newApple = new RedApple(this);
                    } else {
                        newApple = new YellowApple(this);
                    }

                    apples.add(newApple);

                    if (hasAvailableSpaces) {
                        hasRedOrYellow = true;
                    }
                    continue;
                }
            }

        } else if (score >= Config.SCORE_BREAKPOINTS[0]) {
            // Aim to have 2 apples on the board:
            // - 1 Black
            // - 1 Red

            boolean hasBlack = false;
            boolean hasRed = false;

            for (Apple apple : apples) {
                if (apple instanceof BlackApple) {
                    hasBlack = true;
                } else if (apple instanceof RedApple) {
                    hasRed = true;
                }
            }

            while (apples.size() < 2) {
                if (!hasBlack) {
                    apples.add(new BlackApple(this));
                    hasBlack = true;
                    continue;
                }

                if (!hasRed) {
                    apples.add(new RedApple(this));
                    hasRed = true;
                    continue;
                }
            }
        } else {
            // Aim to have 1 apple on the board:
            // - 1 Red

            if (apples.size() == 0) {
                apples.add(new RedApple(this));
            }
        }
    }

    private void updateApples() {
        // Create a copy of the apples list to avoid ConcurrentModificationException
        // when apples are added/removed during update() calls
        ArrayList<Apple> applesCopy = new ArrayList<>(apples);

        for (Apple apple : applesCopy) {
            apple.update(this);
        }
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
