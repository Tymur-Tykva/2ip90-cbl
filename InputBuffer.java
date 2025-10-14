import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class InputBuffer {
    private static final int MAX_BUFFER_SIZE = 4; // Prevent queueinig up too many inputs.

    private Queue<Direction> directionBuffer; // Buffer of directions.
    private boolean paused; // Indicate if the pause button was pressed.

    public InputBuffer() {
        this.directionBuffer = new LinkedList<>();
        this.paused = false;
    }

    public void add(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            // Direction keys.
            case KeyEvent.VK_UP:
                this.directionBuffer.add(Direction.U);
                break;
            case KeyEvent.VK_DOWN:
                this.directionBuffer.add(Direction.D);
                break;
            case KeyEvent.VK_LEFT:
                this.directionBuffer.add(Direction.L);
                break;
            case KeyEvent.VK_RIGHT:
                this.directionBuffer.add(Direction.R);
                break;

            // Pause button.
            case KeyEvent.VK_P | KeyEvent.VK_ESCAPE:
                this.paused = !this.paused;
                break;

            default:
                break;
        }
    }
}
