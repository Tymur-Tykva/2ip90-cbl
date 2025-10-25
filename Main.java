import javax.swing.SwingUtilities;
import ui.Frame;

/**
 * Launches the game, which is abstracted by the Frame class.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();
            frame.init();
        });
    }
}
