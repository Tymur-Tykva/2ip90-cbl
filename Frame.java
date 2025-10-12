import javax.swing.*;
import java.awt.*;

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
        setSize(600, 400); // TODO: Change to be dynamically calculated from the panel size.
        setLayout(new GridLayout(3, 1));

        // Create test labels to test the layout.
        JLabel label1 = new JLabel("Top label", JLabel.CENTER);
        label1.setSize(600, 50);
        JLabel label2 = new JLabel("Bottom label", JLabel.CENTER);
        label2.setSize(600, 50);

        // Add elements to the frame.
        add(label1);
        add(panel);
        add(label2);

        setVisible(true);
    }
}
