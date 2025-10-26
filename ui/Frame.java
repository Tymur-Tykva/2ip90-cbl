package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import logic.GameLoop;
import logic.InputBuffer;
import logic.RetryHandler;
import logic.StateManager;

/**
 * The Frame. Builds out the UI and logic structure, and starts the game loop.
 * Similarly allows the game loop to be restarted.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class Frame extends JFrame {
    // Container for the main panel slot.
    private JPanel mainPanelContainer;
    // Logic components.
    private JLabel scoreLabel;
    private InputBuffer inputBuffer;
    private StateManager stateManager;
    // UI components.
    private Panel panelMain;
    private PanelPause pauseMenu;
    private PanelGameOver gameOverMenu;
    private PanelTutorial tutorialMenu;
    // Game loop.
    private GameLoop gameLoop;

    /**
     * Create the frame, and instance the UI and logic components.
     */
    public Frame() {
        // Set the frame's parameters.
        super("Snake Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(450, 100);

        // Create the container for the main panel, and init the layout (required for
        // the GameLoop).
        this.mainPanelContainer = new JPanel();
        mainPanelContainer.setLayout(new CardLayout());

        // Create the retry handler for the pause menu and keybind.
        // Resets the game loop and state manager; passed into UI components.
        RetryHandler retryHandler = new RetryHandler() {
            @Override
            public void retry() {
                if (gameLoop == null) {
                    return;
                }

                // Have to create a new thread, otherwise stop() will prevent start() from
                // running.
                new Thread(() -> {
                    gameLoop.stop();
                    gameLoop.start();
                }).start();
            }
        };

        // Init logic components.
        this.scoreLabel = new JLabel("0");
        this.inputBuffer = new InputBuffer();
        this.stateManager = new StateManager(inputBuffer, retryHandler);

        // Init UI components.
        this.panelMain = new Panel(stateManager);
        this.pauseMenu = new PanelPause(stateManager);
        this.gameOverMenu = new PanelGameOver(stateManager);
        this.tutorialMenu = new PanelTutorial(stateManager);

        // Init the game loop.
        CardLayout cl = (CardLayout) (mainPanelContainer.getLayout());
        this.gameLoop = new GameLoop(stateManager,
                panelMain, pauseMenu, gameOverMenu,
                tutorialMenu, cl,
                scoreLabel);
    }

    /**
     * Initialises the UI layout and starts the game.
     */
    public void init() {
        // Set the layout.
        setLayout(new BorderLayout());

        // Create and style panelTop.
        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(605, 70));
        panelTop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelTop.setBackground(new Color(147, 109, 62));
        panelTop.setLayout(new BorderLayout());

        // Create and style panelScore.
        JPanel panelScore = new JPanel();
        panelScore.setPreferredSize(new Dimension(80, 30));
        panelScore.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelScore.setBackground(new Color(200, 163, 117));
        panelScore.setLayout(new BorderLayout());

        // Create and style the score labels.
        Color colorBrown = new Color(101, 67, 33);
        JLabel scoreTitle = new JLabel("score:");

        scoreTitle.setForeground(colorBrown);
        scoreLabel.setForeground(colorBrown);

        // Add the score labels to their container, and then to panelTop.
        panelScore.add(scoreTitle, BorderLayout.CENTER);
        panelScore.add(scoreLabel, BorderLayout.EAST);

        panelTop.add(panelScore, BorderLayout.WEST);

        // Create and add the pause button.
        PauseButton pause = new PauseButton("");
        panelTop.add(pause, BorderLayout.EAST);

        // Set the pause button listener; toggles the pause state on press.
        pause.addActionListener((ActionEvent e) -> {
            inputBuffer.togglePause();
        });

        // Create the mainPanelContainer.
        mainPanelContainer.setPreferredSize(new Dimension(605, 605));
        mainPanelContainer.setBackground(new Color(147, 109, 62));

        // Add all the child panels to the mainPanelContainer.
        // On first launch, 'tutorialMenu' is shown in the GameLoop.
        mainPanelContainer.add(panelMain, "panelMain");
        mainPanelContainer.add(pauseMenu, "pauseMenu");
        mainPanelContainer.add(gameOverMenu, "gameOverMenu");
        mainPanelContainer.add(tutorialMenu, "tutorialMenu");

        // Add the top and main panels to the frame.
        add(panelTop, BorderLayout.NORTH);
        add(mainPanelContainer, BorderLayout.CENTER);

        // Display the frame.
        pack();
        setVisible(true);

        // Start the gameloop.
        gameLoop.start();
    }

}