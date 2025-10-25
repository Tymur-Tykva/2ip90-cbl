package logic;

import java.awt.CardLayout;
import javax.swing.JLabel;
import ui.Panel;
import ui.PanelGameOver;
import ui.PanelPause;
import ui.PanelTutorial;

/**
 * Creates and starts the game loop thread. The thread controls the frame times,
 * and by extension updates the state, handles switching between panels in the
 * mainPanelContainer (see Frame), and repainting the Panel.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class GameLoop implements Runnable {
    private static final int FPS = 4;
    private static final long INTERVAL_NS = 1_000_000_000L / FPS; // this is 1 second in nanosecods

    // Main panel components.
    private Panel panel;
    private PanelPause pauseMenu;
    private PanelGameOver gameOverMenu;
    private PanelTutorial tutorialMenu;
    private CardLayout cl; // Card layout for the mainPanelContainer.

    // Other UI components.
    private JLabel scoreLabel;

    // Logic components.
    private StateManager stateManager;

    // Game loop properties.
    private Thread thread;
    private boolean running;
    private boolean tutorialDismissed;

    /**
     * Creates and readies an instance of the GameLoop, ready to be started with the
     * start() method.
     * 
     * @param stateManager The StateManager instance.
     * @param panel        The Panel instance.
     * @param pauseMenu    The PanelPause instance.
     * @param gameOverMenu The PanelGameOver instance.
     * @param tutorialMenu The PanelTutorial instance.
     * @param cl           The CardLayout instance.
     * @param scoreLabel   The JLabel instance.
     */
    public GameLoop(StateManager stateManager,
            Panel panel, PanelPause pauseMenu, PanelGameOver gameOverMenu, PanelTutorial tutorialMenu,
            CardLayout cl, JLabel scoreLabel) {
        // Get the UI components.
        this.panel = panel;
        this.pauseMenu = pauseMenu;
        this.gameOverMenu = gameOverMenu;
        this.tutorialMenu = tutorialMenu;
        this.cl = cl;

        this.scoreLabel = scoreLabel;

        // Initialize the logic components.
        this.stateManager = stateManager;
        this.running = false;
        this.tutorialDismissed = false;
    }

    /**
     * Starts the game loop thread:
     * - Resets the state manager.
     * - Makes the main panel visible, and focuses it.
     * - Creates and starts the thread that executes the game loop.
     */
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

    /**
     * Stops the game loop thread: sets the running flag to false and stops the
     * thread.
     */
    public void stop() {
        // Set the running flag to false to stop the game loop and stop the thread.
        this.running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dictates what happens in the game loop thread every update. This method is
     * responsible for updating the state of the game, repainiting the panel, and
     * handling the tutorial, pause, and game over states.
     */
    @Override
    public void run() {
        long previousTime = System.nanoTime();
        long lag = 0L;

        // If the tutorial hasn't been dismissed on this run, pause state updates and
        // show the tutorial panel.
        if (!tutorialDismissed) {
            stateManager.setPaused(true);

            cl.show(panel.getParent(), "tutorialMenu");
            tutorialMenu.requestFocus();
        }

        while (running) {
            // Calculate the elapsed time since last update.
            long currentTime = System.nanoTime();
            long elapsed = currentTime - previousTime;

            previousTime = currentTime;
            lag += elapsed;

            // Execute state updates while allowed.
            while (lag >= INTERVAL_NS) {
                // System.out.println("Update: " + lag);
                stateManager.update();
                lag -= INTERVAL_NS;
            }

            // Dismiss the tutorial if the game is unpaused.
            if (!tutorialDismissed && !stateManager.isPaused()) {
                tutorialDismissed = true;

                cl.show(panel.getParent(), "panelMain");
                panel.requestFocus();
            }

            // If the game is over, display the game over menu and break the loop.
            if (stateManager.isGameOver()) {
                cl.show(gameOverMenu.getParent(), "gameOverMenu");
                gameOverMenu.updateGameOverMessage();
                gameOverMenu.requestFocus();

                running = false;
                break;
            }

            // Update the score label.
            String score = String.valueOf(stateManager.getScore());
            scoreLabel.setText(score);

            // If the pause state has changed, update the display accordingly. If the
            // tutorial hasn't yet been dismissed, this update is ignored.
            boolean pauseChanged = stateManager.pauseChanged();
            boolean isPaused = stateManager.isPaused();

            if (pauseChanged && tutorialDismissed) {
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
