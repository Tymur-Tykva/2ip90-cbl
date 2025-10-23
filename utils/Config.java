package utils;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Class containing all the static configs for the game.
 */
public class Config {
    /* ------------------- Grid ------------------- */
    public static final int GRID_WIDTH = 11;
    public static final int GRID_HEIGHT = 11;

    /* ------------------- Snake ------------------ */
    public static final Point INITIAL_SNAKE_POSITION = new Point(4, 5);
    public static final int INITIAL_SNAKE_LENGTH = 3;

    /* ------------------ Apples ------------------ */
    public static final ArrayList<Point> INITIAL_APPLE_POSITIONS = new ArrayList<Point>() {
        {
            add(new Point(6, 5));
            add(new Point(6, 4));
        }
    };
    public static final int BLACK_APPLE_EXPIRES_IN = 10;
    // Amount of available spaces required for apples in 'strict' mode to spawn.
    public static final int APPLE_AVAILABLE_SPACES = 8;
    // Score 'breakpoints', used for apple spawning rules.
    public static final int[] SCORE_BREAKPOINTS = { 6, 12 };
    // Score breakpoint at which the odds of a black apple expiring into a yellow
    // increase.
    public static final float BLACK_TO_YELLOW_LATE_BREAKPOINT = Config.SCORE_BREAKPOINTS[1];
    // Chances of a black apple expiring into a yellow apple before/after the
    // breakpoint.
    public static final float INITIAL_BLACK_TO_YELLOW_CHANCE = 0.2f;
    public static final float LATE_BLACK_TO_YELLOW_CHANCE = 0.8f;
}