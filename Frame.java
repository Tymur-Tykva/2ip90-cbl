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
        setSize(600, 400); // TODO: Change to be dynamically calculated from the panel size.
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new Panel();
        gameLoop = new GameLoop();
    }

    /*
     * Initialise the UI layout.
     */
    public void init() {

    }
}
