package logic;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import utils.Direction;

public class InputBuffer {
    private static final int MAX_BUFFER_SIZE = 4; // Prevent queueinig up too many inputs.

    private volatile Queue<Direction> directionBuffer; // Buffer of directions.
    private volatile boolean togglePause; // Toggles the pause state on the state manager.
    private volatile boolean retry; // Resets the thread and state manager on next update if true.

    /* ---------------- Constructor --------------- */
    public InputBuffer() {
        this.directionBuffer = new LinkedList<>();
        this.togglePause = false;
        this.retry = false;
    }

    /* ------------------ Public ------------------ */
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
                this.togglePause = !this.togglePause;
                break;

            // Reset button.
            case KeyEvent.VK_R:
                this.retry = true;
                break;

            default:
                break;
        }
    }

    public void clearDirectionBuffer() {
        this.directionBuffer.clear();
    }

    public void togglePause() {
        this.togglePause = !this.togglePause;
    }

    /* ------------------ Getters ----------------- */
    public Direction peekDirection() {
        return directionBuffer.peek();
    }

    public Direction getDirection() {
        return directionBuffer.poll();
    }

    public boolean getTogglePause() {
        boolean pauseState = togglePause;
        togglePause = false;

        return pauseState;
    }

    public boolean getRetry() {
        boolean resetState = retry;
        retry = false;

        return resetState;
    }

    /* ------------------ Setters ----------------- */
    public void setTogglePause(boolean togglePause) {
        this.togglePause = togglePause;
    }

    public void setRetry(boolean retry) {
        this.retry = retry;
    }

    /* ------------------ Private ----------------- */
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
