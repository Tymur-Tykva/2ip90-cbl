package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import logic.GameLoop;
import logic.InputBuffer;
import logic.RetryHandler;
import logic.StateManager;

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
    // Game loop.
    private GameLoop gameLoop;

    /*
     * Create a frame with default parameters.
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

        // Init the game loop.
        CardLayout cl = (CardLayout) (mainPanelContainer.getLayout());
        this.gameLoop = new GameLoop(stateManager, panelMain, pauseMenu, gameOverMenu, cl, scoreLabel);
    }

    /*
     * Initialise the UI layout.
     */
    public void init() {
        // Set the layout.
        setLayout(new BorderLayout());

        // Set the layout of panelTop.
        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(605, 70));
        panelTop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelTop.setBackground(new Color(147, 109, 62));
        panelTop.setLayout(new BorderLayout());

        // Set the layout of panelScore.
        JPanel panelScore = new JPanel();
        panelScore.setPreferredSize(new Dimension(80, 30));
        panelScore.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelScore.setBackground(new Color(200, 163, 117));
        panelScore.setLayout(new BorderLayout());

        // Set score labels and panel.
        Color colorBrown = new Color(101, 67, 33);
        JLabel scoreTitle = new JLabel("score:");

        scoreTitle.setForeground(colorBrown);
        scoreLabel.setForeground(colorBrown);

        panelScore.add(scoreTitle, BorderLayout.CENTER);
        panelScore.add(scoreLabel, BorderLayout.EAST);

        panelTop.add(panelScore, BorderLayout.WEST);

        // Set pause button.
        PauseButton pause = new PauseButton("");
        panelTop.add(pause, BorderLayout.EAST);

        // Set the pause button listener
        pause.addActionListener((ActionEvent e) -> {
            inputBuffer.togglePause();
        });

        // Set the mainPanelContainer.
        mainPanelContainer.setPreferredSize(new Dimension(605, 605));
        mainPanelContainer.setBackground(new Color(147, 109, 62));

        // Adding all of the panels to the frame.
        add(panelTop, BorderLayout.NORTH);
        add(mainPanelContainer, BorderLayout.CENTER);

        mainPanelContainer.add(panelMain, "panelMain");
        mainPanelContainer.add(pauseMenu, "pauseMenu");
        mainPanelContainer.add(gameOverMenu, "gameOverMenu");

        // Display the frame.
        pack();
        setVisible(true);

        // Start the gameloop.
        gameLoop.start();
    }

}