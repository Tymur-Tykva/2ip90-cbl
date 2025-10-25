package logic;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import utils.Direction;

/**
 * Handles all types of input events (keyboard inputs, result of UI button
 * presses, etc.). Direction inputs are buffered in a queue of limited size (see
 * MAX_BUFFER_SIZE), and the pause and retry states are tracked in booleans.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class InputBuffer {
    private static final int MAX_BUFFER_SIZE = 4; // Prevent queueinig up too many inputs.

    private volatile Queue<Direction> directionBuffer;
    private volatile boolean togglePause; // Toggles the pause state on the state manager.
    private volatile boolean retry; // Resets the thread and state manager on next update if true.

    /* ---------------- Constructor --------------- */
    /**
     * Intializes the input buffer with default values.
     */
    public InputBuffer() {
        this.directionBuffer = new LinkedList<>();
        this.togglePause = false;
        this.retry = false;
    }

    /* ------------------ Public ------------------ */
    /**
     * Handles keyboard events:
     * - Direction keys: directions are added to the direction buffer via
     * addDirection().
     * - Pause keys: the pause state is toggled.
     * - Reset keys: the retry state is set to true.
     * 
     * @param keyEvent KeyEvent from the upstream listener on a UI component.
     */
    public void handleEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            // Direction keys.
            case KeyEvent.VK_UP:
                this.addDirection(Direction.U);
                break;
            case KeyEvent.VK_DOWN:
                this.addDirection(Direction.D);
                break;
            case KeyEvent.VK_LEFT:
                this.addDirection(Direction.L);
                break;
            case KeyEvent.VK_RIGHT:
                this.addDirection(Direction.R);
                break;

            // Pause button.
            case KeyEvent.VK_P:
            case KeyEvent.VK_ESCAPE:
                this.togglePause();
                break;

            // Reset button.
            case KeyEvent.VK_R:
                this.retry = true;
                break;

            default:
                break;
        }
    }

    /**
     * Resets the direction buffer to an empty state.
     */
    public void clearDirectionBuffer() {
        this.directionBuffer.clear();
    }

    /**
     * Toggles the paused state.
     */
    public void togglePause() {
        this.togglePause = !this.togglePause;
    }

    /* ------------------ Getters ----------------- */
    /**
     * Gets the first direction in the direction buffer. This direction is then
     * removed from the buffer. If the buffer is empty, returns null.
     * 
     * @return Last direction in the buffer, or null.
     */
    public Direction getDirection() {
        return directionBuffer.poll();
    }

    /**
     * Returns whether the pause state should be toggled in the next update. This
     * method resets the togglePause state for the next update.
     * 
     * @return Whether the pause state should be toggled.
     */
    public boolean getTogglePause() {
        boolean pauseState = togglePause;
        togglePause = false;

        return pauseState;
    }

    /**
     * Returns whether the game should be reset in the next update. This method then
     * resets the retry state for the next update.
     * 
     * @return Whether the game should be reset next update.
     */
    public boolean getRetry() {
        boolean resetState = retry;
        retry = false;

        return resetState;
    }

    /* ------------------ Setters ----------------- */
    /**
     * Sets togglePause to the passed value.
     * 
     * @param togglePause The value that togglePause should be set to.
     */
    public void setTogglePause(boolean togglePause) {
        this.togglePause = togglePause;
    }

    /**
     * Sets retry to the passed value.
     * 
     * @param retry The value that retry should be set to.
     */
    public void setRetry(boolean retry) {
        this.retry = retry;
    }

    /* ------------------ Private ----------------- */
    /**
     * Adds a direction to the direction buffer. If the buffer is full, the oldest
     * direction is removed.
     * 
     * @param direction The direction to add to the buffer.
     */
    private void addDirection(Direction direction) {
        // Do not queue inputs if the game is paused.
        // if (this.paused) {
        // return;
        // }

        // Remove the oldest input if the buffer is full.
        if (this.directionBuffer.size() >= MAX_BUFFER_SIZE) {
            this.directionBuffer.poll();
        }

        this.directionBuffer.add(direction);
    }
}
