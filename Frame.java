import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
    private Panel panel;
    private GameLoop gameLoop;

    /*
     * Create a frame with default parameters.
     */
    public Frame() {
        // Set the frame's parameters.
        super("SOME TITLE");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new Panel();
        gameLoop = new GameLoop();
    }

    /*
     * Initialise the UI layout.
     */
    public void init() {
        // Set the layout.
        setLayout(new BorderLayout());

        // Create test labels to test the layout.
        JLabel label1 = new JLabel("Top label", JLabel.CENTER);
        label1.setPreferredSize(new Dimension(600, 100));
        JLabel label2 = new JLabel("Bottom label", JLabel.CENTER);
        label2.setPreferredSize(new Dimension(600, 50));

        // Add elements to the frame.
        add(label1, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(label2, BorderLayout.SOUTH);

        // Display the frame.
        pack();
        setVisible(true);

        // TODO: Start the game loop.
    }
}
