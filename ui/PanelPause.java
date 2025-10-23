package ui;

import java.awt.*;
import javax.swing.*;

public class PanelPause extends JPanel {

    /*
     * Set the constructor of PanelPause
     */
    public PanelPause() {
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
        

        // Initialize Retry button
        PauseMenuButton retryButton = new PauseMenuButton("Retry");
       

        // Initialize continue button
        PauseMenuButton exitButton = new PauseMenuButton("Exit");
       

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
