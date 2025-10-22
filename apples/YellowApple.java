package apples;

import java.awt.Color;
import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import logic.StateManager;
import utils.Direction;

public class YellowApple extends Apple {
    /* --------------- Constructors --------------- */
    /**
     * Create an instance of YellowApple with a given position.
     * 
     * @param position Position of the apple.
     */
    public YellowApple(Point position) {
        super(position);
        this.color = new Color(236, 255, 54);
    }

    /**
     * Create an instance of YellowApple at a random available space on the board,
     * with strict set to true.
     * 
     * @param stateManager The StateManager instance.
     */
    public YellowApple(StateManager stateManager) {
        super(stateManager, true);
        this.color = new Color(236, 255, 54);
    }

    /* ------------------ Public ------------------ */
    @Override
    public void eat(StateManager stateManager) {
        // Flip the snake: head becomes the tail, and vice versa.
        System.out.println("== Yellow Apple ==");

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
