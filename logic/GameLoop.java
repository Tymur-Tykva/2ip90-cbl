package logic;

import java.awt.CardLayout;
import ui.Panel;
import ui.PanelGameOver;
import ui.PanelPause;

public class GameLoop implements Runnable {
    private static final int FPS = 4;
    private static final long INTERVAL_NS = 1_000_000_000L / FPS; // this is 1 second in nanosecods

    // UI components.
    private Panel panel;
    private PanelPause pauseMenu;
    private PanelGameOver gameOverMenu;
    private CardLayout cl; // Card layout for the mainPanelContainer.

    private StateManager stateManager;

    private Thread thread;
    private boolean running;

    public GameLoop(StateManager stateManager,
            Panel panel, PanelPause pauseMenu, PanelGameOver gameOverMenu,
            CardLayout cl) {
        // Get the UI components.
        this.panel = panel;
        this.pauseMenu = pauseMenu;
        this.gameOverMenu = gameOverMenu;
        this.cl = cl;

        // Initialize the logic components.
        this.stateManager = stateManager;
        this.running = false;
    }

    public void start() {
        // Reset the state manager.
        stateManager.reset();

        // Ensure the main panel is visible and focused.
        cl.show(panel.getParent(), "panelMain");
        panel.requestFocus();

        // Create and start the thread that executes the game loop.
        this.running = true;

        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        // System.out.println("Stopping");

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

            previousTime = currentTime;
            lag += elapsed;

            // Execute state updates while allowed.
            while (lag >= INTERVAL_NS) {
                // System.out.println("Update: " + lag);
                stateManager.update();
                lag -= INTERVAL_NS;
            }

            if (stateManager.isGameOver()) {
                cl.show(gameOverMenu.getParent(), "gameOverMenu");
                gameOverMenu.updateGameOverMessage();
                gameOverMenu.requestFocus();

                running = false;
                break;
            }

            // If the pause state has changed, update the display accordingly.
            boolean pauseChanged = stateManager.pauseChanged();
            boolean isPaused = stateManager.isPaused();

            if (pauseChanged) {
                if (isPaused) {
                    cl.show(pauseMenu.getParent(), "pauseMenu");
                    pauseMenu.requestFocus();
                } else {
                    // If unpaused, display panelMain and bring focus to it.
                    cl.show(panel.getParent(), "panelMain");
                    panel.requestFocus();
                }
            }

            // Repaint the panel if unpaused.
            if (!isPaused) {
                panel.repaint();
            }

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
