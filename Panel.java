import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {
    public Panel() {
        setPreferredSize(new Dimension(605, 605));

    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        // set the forloop for the checkpatter
        int side = 605;
        int width = 605 / 11;
        int x = 0;
        int y = 0;
        for (int col = 0; col < 11; col++) {
            for (int row = 0; row < 11; row++) {
                if (((row % 2 == 0) && (col % 2 == 0)) || (!(row % 2 == 0) && !(col % 2 == 0))) {
                    g2D.setColor(new Color(138, 233, 131));
                    g2D.fillRect(x + (row * width), y + (col * width), 55, 55);
                } else {
                    g2D.setColor(new Color(101, 196, 99));
                    g2D.fillRect(x + (row * width), y + (col * width), 55, 55);
                }

            }

        }

    }
}
