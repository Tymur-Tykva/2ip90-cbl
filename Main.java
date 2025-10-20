import javax.swing.SwingUtilities;
import ui.Frame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();
            frame.init();
        });
    }
}
