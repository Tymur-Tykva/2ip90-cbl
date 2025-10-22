package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import logic.GameLoop;
import logic.InputBuffer;
import logic.StateManager;

public class Frame extends JFrame implements PanelPauseListener {
    private InputBuffer inputBuffer;
    private Panel panelMain;
    private StateManager stateManager;
    private GameLoop gameLoop;
    private PanelPause pauseMenu;
    private JPanel mainPanelContainer;
    private CardLayout cardLayout;
    private boolean isPanelMainVis = true;

    /*
     * Create a frame with default parameters.
     */
    public Frame() {
        // Set the frame's parameters.
        super("Snake Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(450, 100);

        this.inputBuffer = new InputBuffer();
        this.stateManager = new StateManager(inputBuffer);
        this.panelMain = new Panel(inputBuffer, stateManager);
        this.gameLoop = new GameLoop(panelMain, stateManager);
        this.pauseMenu = new PanelPause(this);
        this.mainPanelContainer = new JPanel();
        this.cardLayout = new CardLayout();

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

        // Set the pause button listener
        pause.addActionListener((ActionEvent e) -> {
            if (isPanelMainVis) {
                this.cardLayout.show(mainPanelContainer, "pauseMenu");
            } else {
                this.cardLayout.show(mainPanelContainer, "panelMain");
            }
            isPanelMainVis = !isPanelMainVis;


            //TODO: Tymur must add the stop game method and start game method


        });

        // Set the mainPanelContainer
        mainPanelContainer.setLayout(this.cardLayout);
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

        gameLoop.start();
    }


    // override the onContinueClicked, so it switches from pause menu to main panel
    @Override
    public void onContinueClicked() {
        cardLayout.show(mainPanelContainer, "panelMain");
        this.isPanelMainVis = true;
    }

    // override the onRetryClicked, so it restarts the game
    @Override
    public void onRetryClicked() {
        
        //TODO: Tymur must add the restart the game method

    }

    // override the onExitClicked, so it closes the game
    @Override
    public void onExitClicked() {
        System.exit(0);

    }
}