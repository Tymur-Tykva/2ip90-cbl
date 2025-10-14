import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class InputBuffer {
    private static final int MAX_BUFFER_SIZE = 4; // Prevent queueinig up too many inputs.
    private static final Set<Integer> VALID_KEY_CODES = Set.of(
            // Directions.
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT,
            // Pause.
            KeyEvent.VK_P);

    private Queue<Integer> buffer; // Buffer of keycodes.

    public InputBuffer() {
        this.buffer = new LinkedList<>();
    }

    public void add(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();

        // Exit if keycode is invalid.
        if (!VALID_KEY_CODES.contains(keyCode)) {
            return;
        }

        // If the buffer is full, remove the oldest element.
        if (buffer.size() == MAX_BUFFER_SIZE) {
            buffer.poll();
        }

        // Add the new element to the buffer.
        buffer.add(keyCode);
    }
}
