public class GameLoop implements Runnable {
    private static final int FPS = 2;
    private static final long INTERVAL_NS = 1_000_000_000L / FPS; // this is 1 second in nanosecods

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
        long previousTime = System.nanoTime();
        long lag = 0L;

        // System.out.println("=== Loop Start ===");
        // System.out.println("interval: " + INTERVAL_NS);

        while (running) {
            // Calculate the elapsed time since last update.
            long currentTime = System.nanoTime();
            long elapsed = currentTime - previousTime;

            // System.out.println("=== Loop ===");
            // System.out.println("elapsed: " + elapsed);

            previousTime = currentTime;
            lag += elapsed;

            // Execute state updates while allowed.
            while (lag >= INTERVAL_NS) {
                stateManager.update();
                lag -= INTERVAL_NS;
            }

            if (stateManager.isGameOver()) {
                // TODO: Gracefully handle game over.
                running = false;
                break;
            }

            // Repaint the panel.
            panel.repaint();

            // Sleep remaining time unless game over.
            long nextFrameTime = previousTime + INTERVAL_NS;
            long sleepTime = nextFrameTime - System.nanoTime();

            if (sleepTime > 0) {
                long sleepMs = sleepTime / 1_000_000L;
                int sleepNs = (int) (sleepTime % 1_000_000L);

                // System.out.println("=== Sleep ===");
                // System.out.println("sleep: " + sleepMs + "ms " + sleepNs + "ns");

                try {
                    Thread.sleep(sleepMs, sleepNs);
                } catch (InterruptedException e) {
                    // If exception during sleep, log it and exit the thread.
                    e.printStackTrace();
                    thread.interrupt();
                }
            }
        }
    }
}
