// The possible directions of the snake.
public enum Direction {
    U, D, L, R;

    public Direction GetOpposite() {
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
}