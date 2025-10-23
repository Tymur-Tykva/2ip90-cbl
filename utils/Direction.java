package utils;

import java.awt.Point;

// The possible directions of the snake.
public enum Direction {
    U, D, L, R;

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

    public static Direction betweenTwoPoints(Point first, Point second) {
        // positive x = left, positive y = down

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