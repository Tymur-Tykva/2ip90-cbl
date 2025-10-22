package logic;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import utils.Direction;

public class InputBuffer {
    private static final int MAX_BUFFER_SIZE = 4; // Prevent queueinig up too many inputs.

    private volatile Queue<Direction> directionBuffer; // Buffer of directions.
    private volatile boolean paused; // Indicate if the pause button was pressed.

    public InputBuffer() {
        this.directionBuffer = new LinkedList<>();
        this.paused = false;
    }

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
                this.paused = !this.paused;
                break;

            default:
                break;
        }
    }

    public Direction peekDirection() {
        return directionBuffer.peek();
    }

    public Direction getDirection() {
        return directionBuffer.poll();
    }

    public boolean isPaused() {
        return paused;
    }

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
