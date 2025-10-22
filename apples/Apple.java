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
    protected Point position;
    protected Color color = Color.RED;
    protected int expiresIn = -1; // If set to -1, the expiry effect is disabled.

    /* --------------- Constructors --------------- */
    public Apple(Point position) {
        this.position = position;
    }

    /**
     * Spawns the apple at a random available space on the board. If strict is true,
     * the apple will only spawn if there are at least 'n' available spaces: meaning
     * not directly around the snake's or when the game is close to ending.
     * 
     * Available space requirements are specified in the Config class.
     * 
     * @param stateManager The StateManager instance.
     * @param strict       If true, the apple will only spawn if there are at least
     *                     'n' available spaces.
     */
    public Apple(StateManager stateManager, boolean strict) {
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

        // Find all spaces not containing apples.
        for (Apple apple : stateManager.getApples()) {
            Point point = apple.getPosition();
            availableSpaces.remove(point);
        }

        // If there are insufficient availale spaces as specified by the config, and
        // strict is true, return.

        if (strict && availableSpaces.size() < Config.APPLE_AVAILABLE_SPACES) {
            return;
        }

        // If sufficient spaces are available, remove those around the snake's head.
        // At least one of the remove() calls will always be redundant, but it's less
        // complex to than finding and ignoring the second element in the deque.
        if (availableSpaces.size() > 3) {
            Point head = stateManager.getSnake().peekFirst();

            availableSpaces.remove(new Point(head.x - 1, head.y));
            availableSpaces.remove(new Point(head.x + 1, head.y));
            availableSpaces.remove(new Point(head.x, head.y - 1));
            availableSpaces.remove(new Point(head.x, head.y + 1));
        }

        // Choose a random available space. Seed is the current system time, salted with
        // a random number.
        int positionIndex = stateManager.getRandom().nextInt(availableSpaces.size());
        this.position = (Point) availableSpaces.toArray()[positionIndex];
    }

    /**
     * Call the constructor with strict set to false.
     */
    public Apple(StateManager stateManager) {
        this(stateManager, false);
    }

    /* ------------------ Public ------------------ */
    /**
     * Empty method to be overriden by child classes.
     * Triggered when the StateManager finds that the snake has eaten the apple.
     * The apple is then removed by the StateManager.
     */
    public void eat(StateManager stateManager) {
        stateManager.getApples().remove(this);
    }

    /**
     * Updates the state of the apple. Currently only used to tick the 'expiresIn'
     * field, and trigger the onExpire() method on expiry.
     */
    public void update(StateManager stateManager) {
        if (expiresIn == -1) {
            return;
        }

        expiresIn -= 1;

        if (expiresIn == 0) {
            expiresIn = -1;
            onExpire(stateManager);
        }
    }

    public void onExpire(StateManager stateManager) {
    }

    /* ------------------ Getters ----------------- */
    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}