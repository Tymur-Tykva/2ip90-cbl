package ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.*;
import logic.StateManager;

public class PanelTutorial extends JPanel {
    StateManager stateManager;

    public PanelTutorial(StateManager stateManager) {
        this.stateManager = stateManager;

        setPreferredSize(new Dimension(605, 605));
        setBackground(new Color(147, 109, 62));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0));
        setVisible(true);

        JPanel topPanelTutorial = new JPanel();
        topPanelTutorial.setVisible(true);
        topPanelTutorial.setBackground(null);
        topPanelTutorial.setPreferredSize(new Dimension(605, 400));
        topPanelTutorial.setLayout(null);

        Font fontText = new Font("Serif", Font.BOLD, 15);
        Color colorText = new Color(255, 253, 208);

        // Set the Pause description
        JLabel label1 = new JLabel("Press button / P to pause");
        label1.setFont(fontText);
        label1.setForeground(colorText);
        label1.setBounds(420, 0, 200, 40);
        topPanelTutorial.add(label1);

        // Set the Retry
        JLabel labelScore = new JLabel("Press R to retry");
        labelScore.setFont(fontText);
        labelScore.setForeground(colorText);
        labelScore.setBounds(15, 0, 200, 40);
        topPanelTutorial.add(labelScore);

        // Set the Controls
        JLabel labelControls = new JLabel("Controls:");
        labelControls.setFont(new Font("Serif", Font.BOLD, 20));
        labelControls.setForeground(colorText);
        labelControls.setBounds(30, 200, 200, 40);
        topPanelTutorial.add(labelControls);

        // Set the "Tutorial"
        JLabel tutorial = new JLabel("TUTORIAL");
        tutorial.setFont(new Font("Serif", Font.BOLD, 30));
        tutorial.setForeground(colorText);
        tutorial.setBounds(219, 100, 200, 50);
        topPanelTutorial.add(tutorial);

        // Set the purpose
        JLabel purpose = new JLabel("Try to reach the highest score!");
        purpose.setFont(new Font("Serif", Font.BOLD, 17));
        purpose.setForeground(colorText);
        purpose.setBounds(188, 140, 300, 50);
        topPanelTutorial.add(purpose);

        // Set the Types of Apples
        JLabel typesApple = new JLabel("Types of Apples:");
        typesApple.setFont(new Font("Serif", Font.BOLD, 15));
        typesApple.setForeground(colorText);
        typesApple.setBounds(400, 200, 200, 50);
        topPanelTutorial.add(typesApple);

        // Set the fricking images of Apples (this took way too long :( )
        URL imageUrlRedApple = getClass().getResource("/ui/redAppleImage.png");
        URL imageUrlYellowApple = getClass().getResource("/ui/yellowAppleImage.png");
        URL imageUrlBlackApple = getClass().getResource("/ui/blackAppleImage.png");
        ImageIcon imageIconRed = new ImageIcon(imageUrlRedApple);
        ImageIcon imageIconYellow = new ImageIcon(imageUrlYellowApple);
        ImageIcon imageIconBlack = new ImageIcon(imageUrlBlackApple);
        JLabel labelRedApple = new JLabel(imageIconRed);
        JLabel labelYellowApple = new JLabel(imageIconYellow);
        JLabel labelBlackApple = new JLabel(imageIconBlack);
        labelRedApple.setBounds(400, 250, imageIconRed.getIconWidth(), imageIconRed.getIconHeight());
        labelYellowApple.setBounds(450, 250, imageIconYellow.getIconWidth(), imageIconYellow.getIconHeight());
        labelBlackApple.setBounds(500, 250, imageIconBlack.getIconWidth(), imageIconBlack.getIconHeight());

        topPanelTutorial.add(labelRedApple);
        topPanelTutorial.add(labelYellowApple);
        topPanelTutorial.add(labelBlackApple);

        // Set the image of the controls
        URL imageUrlControls = getClass().getResource("/ui/Controls.png");
        ImageIcon imageIconControls = new ImageIcon(imageUrlControls);
        JLabel labelCont = new JLabel(imageIconControls);
        labelCont.setBounds(30, 250, imageIconControls.getIconWidth(), imageIconControls.getIconHeight());
        topPanelTutorial.add(labelCont);

        // Create the prompt panel.
        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Set the prompt text.
        JLabel promptText = new JLabel("Press any key to continue...");
        promptText.setFont(new Font("Serif", Font.BOLD, 20));
        promptText.setForeground(colorText);

        // Add the prompt text to the prompt panel.
        promptPanel.add(promptText);

        // Add all child panels to the tutorial panel.
        add(topPanelTutorial, BorderLayout.NORTH);
        add(promptPanel, BorderLayout.SOUTH);

        // Add a keyboard listener.
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                stateManager.setPaused(false);
            }
        });
    }

}