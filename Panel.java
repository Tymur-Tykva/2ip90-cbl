import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {
    public Panel() {
        setPreferredSize(new Dimension(605, 605));

    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        // set the forloop for the checkpatter
        int sidePanel = 605;
        int sqr = 605 / 11;
        int x = 0;
        int y = 0;
        for (int col = 0; col < 11; col++) {
            for (int row = 0; row < 11; row++) {
                if (((row % 2 == 0) && (col % 2 == 0)) || (!(row % 2 == 0) && !(col % 2 == 0))) {
                    g2D.setColor(new Color(138, 233, 131));
                    g2D.fillRect(x + (row * sqr), y + (col * sqr), 55, 55);
                } else {
                    g2D.setColor(new Color(101, 196, 99));
                    g2D.fillRect(x + (row * sqr), y + (col * sqr), 55, 55);
                }

            }

        }

        // set a 2d array for containing apples
        int[][] applesArray = new int[11][11];
        applesArray[0][0] = 0;
        applesArray[1][0] = 1;
        applesArray[2][0] = 2;
        applesArray[3][0] = 3;

        // draw the red apples according to the 2d array
        int sideApple = 45;
        int padding = 5;

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                // cases for each apple
                switch (applesArray[i][j]) {
                    // Red apples == 1
                    case 1 -> {
                        g2D.setColor(Color.RED);
                        g2D.fillOval(i * sqr + padding, j * sqr + padding, sideApple, sideApple);
                    }
                    // Black apples == 2
                    case 2 -> {
                        g2D.setColor(Color.BLACK);
                        g2D.fillOval(i * sqr + padding, j * sqr + padding, sideApple, sideApple);
                    }
                    // Yellow apples == 3
                    case 3 -> {
                        g2D.setColor(Color.YELLOW);
                        g2D.fillOval(i * sqr + padding, j * sqr + padding, sideApple, sideApple);
                    }
                    // Empty square == 0
                    default -> {
                    }
                }
            }
        }
    }
}