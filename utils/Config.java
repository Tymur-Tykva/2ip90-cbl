package utils;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Class containing all the static configs for the game.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class Config {
    /* ------------------- Grid ------------------- */
    public static final int GRID_SIDE = 11;

    /* ------------------- Snake ------------------ */
    public static final Point INITIAL_SNAKE_POSITION = new Point(4, 5);
    public static final int INITIAL_SNAKE_LENGTH = 3;

    /* ------------------ Apples ------------------ */
    public static final ArrayList<Point> INITIAL_APPLE_POSITIONS = new ArrayList<Point>() {
        {
            add(new Point(6, 5));
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

    /* ----------------- Game Over ---------------- */
    public static final String[] DEATH_SELF_MESSAGES = {
            "Try avoiding your own body.",
            "Next time, turn away from yourself.",
            "Try not panic pressing the buttons."
    };

    public static final String[] DEATH_BORDER_MESSAGES = {
            "Try avoiding borders.",
            "The walls should be quite easy to avoid...",
            "Tip: don't eat yellow apples when right next to the border."
    };

    public static final String[] DEATH_BLACK_APPLE_MESSAGES = {
            "Try avoiding the black apples.",
            "The color black does usually mean death.",
            "Wait for a bit before eating the black apples, they might change."
    };
}