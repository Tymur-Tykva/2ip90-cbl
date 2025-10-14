public class GameLoop implements Runnable {
    private static final int FPS = 2;
    private static final long INTERVAL_NS = 1_000_000_000L / FPS;

    private Panel panel; // Used to triger repaint().
    private StateManager stateManager; // Used to update the game state every update.
    private Thread thread;
    private boolean running; // Used to stopo the game while loop.

    public GameLoop(Panel panel, StateManager stateManager) {
        this.panel = panel;
        this.stateManager = stateManager;
        this.running = false;
    }

    public void start() {
        this.running = true;
        // Create and start the thread that executes the game loop.
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        // Set the running flag to false to stop the game loop and stop the thread.
        this.running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }
}
