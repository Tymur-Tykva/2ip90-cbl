package apples;

import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import logic.StateManager;
import utils.Config;

/*
 * General parent class for all apples. Defines base methods.
 */
public class Apple {
    private Point position;
    private Color color = Color.RED;

    /* --------------- Constructors --------------- */
    public Apple(Point position) {
        this.position = position;
    }

    public Apple(StateManager stateManager) {
        // Find an available space for the apple.
        Set<Point> availableSpaces = new HashSet<Point>();

        // Find all spaces not containing the snake body.
        for (int x = 0; x < Config.GRID_WIDTH; x++) {
            for (int y = 0; y < Config.GRID_HEIGHT; y++) {
                Point point = new Point(x, y);
                if (!stateManager.getSnake().contains(point)) {
                    availableSpaces.add(point);
                }
            }
        }

        // If sufficient spaces are available, remove those around the snake's head.
        // One of the remove() calls will always be redundant, but it's less complex to
        // than finding and ignoring the second element in the deque.
        if (availableSpaces.size() > 3) {
            Point head = stateManager.getSnake().peekFirst();

            availableSpaces.remove(new Point(head.x - 1, head.y));
            availableSpaces.remove(new Point(head.x + 1, head.y));
            availableSpaces.remove(new Point(head.x, head.y - 1));
            availableSpaces.remove(new Point(head.x, head.y + 1));
        }

        // Choose a random available space. Seed is the current system time, salted with
        // a random number.
        Random random = new Random(System.currentTimeMillis() + 5129875L);
        int positionIndex = random.nextInt(availableSpaces.size());
        this.position = (Point) availableSpaces.toArray()[positionIndex];
    }

    /* ------------------ Public ------------------ */
    /*
     * Empty method to be overriden by child classes.
     * Triggered when the StateManager finds that the snake has eaten the apple.
     * The apple is then removed by the StateManager.
     */
    public void eat(StateManager stateManager) {
    }

    /* ------------------ Getters ----------------- */
    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}