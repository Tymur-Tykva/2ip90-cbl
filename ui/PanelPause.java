package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import logic.InputBuffer;
import logic.RetryHandler;
import logic.StateManager;

public class PanelPause extends JPanel {
    StateManager stateManager;
    InputBuffer inputBuffer;
    RetryHandler retryHandler;

    /*
     * Set the constructor of PanelPause
     */
    public PanelPause(StateManager stateManager) {
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
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        textPanel.setBackground(null);

        // Initialize the font for text
        Font fontText = new Font("Serif", Font.BOLD, 80);
        Color colorText = new Color(255, 253, 208);

        // Set Pause Text
        JLabel pause = new JLabel("Paused");
        // add(pause, BorderLayout.NORTH);
        pause.setFont(fontText);
        pause.setForeground(colorText);

        /*
         * Set the buttons
         */
        // Initialize continue button
        MenuButton continueButton = new MenuButton("Continue");
        continueButton.addActionListener((ActionEvent e) -> {
            inputBuffer.togglePause();
        });

        // Initialize Retry button
        MenuButton retryButton = new MenuButton("Retry");
        retryButton.addActionListener((ActionEvent e) -> {
            inputBuffer.setRetry(true);
        });

        // Initialize continue button
        MenuButton exitButton = new MenuButton("Exit");
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        // Add the "Paused" text to the textPanel
        textPanel.add(pause);

        // Add the buttons to the buttonPanel
        buttonPanel.add(continueButton);
        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);

        // Add the buttonPanel to the PauseMenu
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
}
