package utils;

import java.awt.Point;

/**
 * Enum representing the 4 possible directions of the snake.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public enum Direction {
    U, D, L, R;

    /**
     * Returns the opposite of the own direction.
     */
    public Direction getOpposite() {
        switch (this) {
            case U:
                return D;
            case D:
                return U;
            case L:
                return R;
            case R:
                return L;
            // Unreachable since only 4 possible directions; all cases considered.
            default:
                return null;
        }
    }

    /**
     * Returns the direction between two points: the direction of 'first' from
     * 'second'. So if 'second' is to the right of 'first' (xDiff < 0), then the
     * output will be L.
     *
     * @param first  The first point.
     * @param second The second point.
     * @return The direction between the two points.
     */
    public static Direction betweenTwoPoints(Point first, Point second) {
        int xDiff = first.x - second.x;
        int yDiff = first.y - second.y;

        if (xDiff == 0) {
            if (yDiff > 0) {
                return D;
            } else {
                return U;
            }
        } else if (yDiff == 0) {
            if (xDiff > 0) {
                return R;
            } else {
                return L;
            }
        }

        throw new IllegalArgumentException("Points not U/D/L/R from each other.");
    }
}