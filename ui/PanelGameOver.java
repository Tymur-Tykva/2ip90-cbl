package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import logic.InputBuffer;
import logic.RetryHandler;
import logic.StateManager;

/**
 * The game over menu, displayed when the snake dies. Displays the "Game Over!"
 * text and an appropriate death message, and prompts the user to either retry
 * or exit.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class PanelGameOver extends JPanel {
    StateManager stateManager;
    InputBuffer inputBuffer;
    RetryHandler retryHandler;

    private JLabel gameOverMessageLabel;

    /**
     * Constructor of PanelGameOver. Initializes all of the UI components and
     * constructs the actual UI.
     */
    public PanelGameOver(StateManager stateManager) {
        // Get all the logic components
        this.stateManager = stateManager;
        this.inputBuffer = stateManager.getInputBuffer();
        this.retryHandler = stateManager.getRetryHandler();

        // Set own properties
        setPreferredSize(new Dimension(605, 605));
        setBackground(new Color(147, 109, 62));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(170, 0, 150, 0));

        // Create the textPanel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(null);

        // Initialize the font for the title and message text
        Font fontTitle = new Font("Serif", Font.BOLD, 80);
        Font fontText = new Font("Serif", Font.BOLD, 20);
        Color colorText = new Color(255, 253, 208);

        // Set the game over text
        JLabel gameOver = new JLabel("Game Over!");
        gameOver.setFont(fontTitle);
        gameOver.setForeground(colorText);
        gameOver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the game over message
        this.gameOverMessageLabel = new JLabel();
        gameOverMessageLabel.setFont(fontText);
        gameOverMessageLabel.setForeground(colorText);
        gameOverMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        updateGameOverMessage(); // Fetches the default game over message initially

        // Add the "Game Over!" text, and the death message to the textPanel.
        // Box layout used for vertical alignment.
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(gameOver);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(gameOverMessageLabel);
        textPanel.add(Box.createVerticalGlue());

        // Initialize Retry button
        MenuButton retryButton = new MenuButton("Retry");
        retryButton.addActionListener((ActionEvent e) -> {
            retryHandler.retry();
        });

        // Initialize continue button
        MenuButton exitButton = new MenuButton("Exit");
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        // Create the buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(null);

        // Add the buttons to the buttonPanel
        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);

        // Add the panels to self
        add(buttonPanel, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.NORTH);

        // Add keyboard listener
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    stateManager.getRetryHandler().retry();
                }
            }
        });
    }

    /**
     * Method to re-fetch the game over message from the state manager and update
     * the label.
     * 
     * Required because when the game over message is first fetched it's the default
     * one, and it is not automatically re-fetched when it is updated in the
     * StateManager.
     */
    public void updateGameOverMessage() {
        String gameOverMessage = stateManager.getGameOverMessage();

        this.gameOverMessageLabel.setText(gameOverMessage);
    }
}
