package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import logic.GameLoop;
import logic.InputBuffer;
import logic.RetryHandler;
import logic.StateManager;

public class Frame extends JFrame {
    private InputBuffer inputBuffer;
    private Panel panelMain;
    private StateManager stateManager;
    private GameLoop gameLoop;
    private PanelPause pauseMenu;
    private JPanel mainPanelContainer;
    private RetryHandler retryHandler;

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
        this.retryHandler = new RetryHandler() {
            @Override
            public void retry() {
                if (gameLoop == null) {
                    return;
                }

                System.out.println("Retrying");

                // Have to create a new thread, otherwise stop() will prevent start() from
                // running.
                new Thread(() -> {
                    gameLoop.stop();
                    gameLoop.start();
                }).start();
            }
        };

        // Init logic components.
        this.inputBuffer = new InputBuffer();
        this.stateManager = new StateManager(inputBuffer, retryHandler);

        // Init UI components.
        this.pauseMenu = new PanelPause(stateManager);
        this.panelMain = new Panel(stateManager);

        // Init the game loop.
        CardLayout cl = (CardLayout) (mainPanelContainer.getLayout());
        this.gameLoop = new GameLoop(stateManager, cl, panelMain, pauseMenu);
    }

    /*
     * Initialise the UI layout.
     */
    public void init() {
        // Set the layout.
        setLayout(new BorderLayout());

        // Set the layout of panelTop
        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(605, 70));
        panelTop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelTop.setBackground(new Color(147, 109, 62));
        panelTop.setLayout(new BorderLayout());

        // Set the layout of panelScore
        JPanel panelScore = new JPanel();
        panelScore.setPreferredSize(new Dimension(80, 30));
        panelScore.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelScore.setBackground(new Color(200, 163, 117));
        panelScore.setLayout(new BorderLayout());

        // Set score
        JLabel score = new JLabel("score:");
        panelScore.add(score, BorderLayout.CENTER);
        Integer a = 0;
        JLabel numberScore = new JLabel(a.toString());
        panelScore.add(numberScore, BorderLayout.EAST);
        Color colorBrown = new Color(101, 67, 33);
        score.setForeground(colorBrown);
        numberScore.setForeground(colorBrown);

        panelTop.add(panelScore, BorderLayout.WEST);

        // Set pause button
        PauseButton pause = new PauseButton("");
        panelTop.add(pause, BorderLayout.EAST);
        pause.addActionListener((ActionEvent e) -> {
            inputBuffer.togglePause();
        });

        // Set the mainPanelContainer
        mainPanelContainer.setPreferredSize(new Dimension(605, 605));
        mainPanelContainer.setBackground(new Color(147, 109, 62));

        // Adding all of the panels to the frame
        add(panelTop, BorderLayout.NORTH);

        /*
         * add(panelBot, BorderLayout.SOUTH);
         */
        add(mainPanelContainer, BorderLayout.CENTER);

        mainPanelContainer.add(panelMain, "panelMain");
        mainPanelContainer.add(pauseMenu, "pauseMenu");

        // Display the frame.
        pack();
        setVisible(true);

        // Start the gameloop.
        gameLoop.start();
    }
}
