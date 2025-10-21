package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelPause extends JPanel {

    public PanelPause() {
        setPreferredSize(new Dimension(605, 605));
        setBackground(new Color(147, 109, 62));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));

        // Initialize the font for buttons
        Font fontText = new Font("Serif", Font.BOLD, 30);
        Color colorText = new Color(255, 253, 208);

        // Set Pause Text
        JLabel pause = new JLabel("Paused");
        add(pause, BorderLayout.NORTH);
        pause.setFont(fontText);
        pause.setForeground(colorText);

        // Intiliaze the customs for the buttons
        Font fontButton = new Font("Serif", Font.BOLD, 25);
        Color colorButton = new Color(200, 163, 117);
        Color colorTextButton = new Color(101, 67, 33);
        LineBorder thickBorder = new LineBorder(new Color(101, 67, 33), 3, false);
        EmptyBorder paddingBorder = new EmptyBorder(7, 10, 7, 10);
        CompoundBorder compoundBorder = new CompoundBorder(thickBorder, paddingBorder);

        /*
         * Set the buttons
         */

        // Set continue button
        JButton continueButton = new JButton("Continue");
        add(continueButton);
        continueButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        continueButton.setPreferredSize(new Dimension(120, 50));
        continueButton.setFont(fontButton);
        continueButton.setForeground(colorTextButton);
        continueButton.setBackground(colorButton);
        continueButton.setBorder(compoundBorder);

        // Set Retry button
        JButton retryButton = new JButton("Retry");
        add(retryButton);
        retryButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        retryButton.setPreferredSize(new Dimension(120, 50));
        retryButton.setFont(fontButton);
        retryButton.setForeground(colorTextButton);
        retryButton.setBackground(colorButton);
        retryButton.setBorder(compoundBorder);

        // Set continue button
        JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        exitButton.setPreferredSize(new Dimension(120, 50));
        exitButton.setFont(fontButton);
        exitButton.setForeground(colorTextButton);
        exitButton.setBackground(colorButton);
        exitButton.setBorder(compoundBorder);

    }
}
