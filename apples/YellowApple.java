package apples;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import logic.StateManager;
import utils.Direction;

/**
 * A yellow apple. When eaten, the snake grows by one, and the score is
 * increased by one.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class YellowApple extends Apple {
    /* --------------- Constructors --------------- */
    /**
     * Create an instance of YellowApple with a given position.
     * 
     * @param position Position of the apple.
     */
    public YellowApple(Point position) {
        super(position);
        this.imageUrl = getClass().getResource("/ui/yellowAppleImage.png");
    }

    /**
     * Create an instance of YellowApple at a random available space on the board,
     * with strict set to true.
     * 
     * @param stateManager The StateManager instance.
     */
    public YellowApple(StateManager stateManager) {
        super(stateManager, true);
        this.imageUrl = getClass().getResource("/ui/yellowAppleImage.png");
    }

    /* ------------------ Public ------------------ */
    /**
     * When the apple is eaten, the snake grows by one, the score is increased by
     * one, and the snake is flipped: the head becomes the tail, and vice versa.
     * 
     * @param stateManager The StateManager instance.
     */
    @Override
    public void eat(StateManager stateManager) {
        // Flip the snake: head becomes the tail, and vice versa.
        Deque<Point> snake = new LinkedList<>(stateManager.getSnake());
        Deque<Point> newSnake = new LinkedList<Point>();

        for (Point point : snake) {
            newSnake.addFirst(point);
        }

        // Get the last two points of the old snake (the head and second-to-last of
        // new) to calculate the new direction of movement.
        Point oldTail = snake.pollLast();
        Point oldSecondLast = snake.pollLast();

        Direction newDirection = Direction.betweenTwoPoints(oldTail, oldSecondLast);
        stateManager.setSnake(newSnake, newDirection);

        // Clear the input direction buffer.
        stateManager.clearInputDirectionBuffer();

        // Grow the snake and remove the apple.
        stateManager.growSnake();
        stateManager.addScore();
        stateManager.getApples().remove(this);
    }
}
