package utils;

import java.awt.Point;
import java.util.ArrayList;

public class Config {
    // Grid
    public static final int GRID_WIDTH = 11;
    public static final int GRID_HEIGHT = 11;
    // Snake
    public static final Point INITIAL_SNAKE_POSITION = new Point(4, 5);
    public static final int INITIAL_SNAKE_LENGTH = 3;
    // Apples
    public static final ArrayList<Point> INITIAL_APPLE_POSITIONS = new ArrayList<Point>() {
        {
            add(new Point(6, 5));
            add(new Point(6, 4));
        }
    };
}