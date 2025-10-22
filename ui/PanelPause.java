package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PanelPause extends JPanel {

    /*
     * Set the constructor of PanelPause
     */
    public PanelPause(PanelPauseListener listener) {
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
        PauseMenuButton continueButton = new PauseMenuButton("Continue");
        // Set the continue button listener
        continueButton.addActionListener((ActionEvent e) -> {
            // When clicked, call the method on the listener object
            listener.onContinueClicked();
        });

        // Initialize Retry button
        PauseMenuButton retryButton = new PauseMenuButton("Retry");
        // Set the retry button listener
        retryButton.addActionListener((ActionEvent e) -> {
           
            listener.onRetryClicked();
        });

        // Initialize continue button
        PauseMenuButton exitButton = new PauseMenuButton("Exit");
        // Set the exit button listener
        exitButton.addActionListener((ActionEvent e) -> {
            // When clicked, call the method on the listener object
            listener.onExitClicked();
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

    }
}
