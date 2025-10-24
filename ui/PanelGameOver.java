package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import logic.InputBuffer;
import logic.RetryHandler;
import logic.StateManager;

public class PanelGameOver extends JPanel {
    StateManager stateManager;
    InputBuffer inputBuffer;
    RetryHandler retryHandler;

    private JLabel gameOverMessageLabel;

    /*
     * Set the constructor of PanelGameOver
     */
    public PanelGameOver(StateManager stateManager) {
        this.stateManager = stateManager;
        this.inputBuffer = stateManager.getInputBuffer();
        this.retryHandler = stateManager.getRetryHandler();

        setPreferredSize(new Dimension(605, 605));
        setBackground(new Color(147, 109, 62));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(170, 0, 150, 0));

        // Set the buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(null);

        // Set the textPanel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(null);

        // Initialize the font for text
        Font fontTitle = new Font("Serif", Font.BOLD, 80);
        Font fontText = new Font("Serif", Font.BOLD, 20);
        Color colorText = new Color(255, 253, 208);

        // Set Game Over Text
        JLabel gameOver = new JLabel("Game Over!");
        gameOver.setFont(fontTitle);
        gameOver.setForeground(colorText);
        gameOver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the game over message
        this.gameOverMessageLabel = new JLabel();
        gameOverMessageLabel.setFont(fontText);
        gameOverMessageLabel.setForeground(colorText);
        gameOverMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        updateGameOverMessage();

        /*
         * Set the buttons
         */
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

        // Add the "Game Over!" text to the textPanel.
        // Box layout used for vertical alignment.
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(gameOver);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(gameOverMessageLabel);
        textPanel.add(Box.createVerticalGlue());

        // Add the buttons to the buttonPanel
        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);

        // Add the buttonPanel to the GameOverMenu
        add(buttonPanel, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.NORTH);

        // Add keyboard listener
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                inputBuffer.handleEvent(e);
            }
        });
    }

    public void updateGameOverMessage() {
        String gameOverMessage = stateManager.getGameOverMessage();
        // System.out.println(gameOverMessage);

        this.gameOverMessageLabel.setText(gameOverMessage);
    }
}
