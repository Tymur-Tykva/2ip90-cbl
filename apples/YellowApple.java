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
        Deque<Point> snake = stateManager.getSnake();
        Deque<Point> newSnake = new LinkedList<Point>();
        Direction newDirection = stateManager.getSnakeDirection().getOpposite();

        newSnake.addLast(snake.peekFirst());

        for (Point point : snake) {
            newSnake.addFirst(point);
        }

        stateManager.setSnake(newSnake, newDirection);

        // Increment the score and remove the apple.
        stateManager.addScore();
        stateManager.getApples().remove(this);
    }
}
