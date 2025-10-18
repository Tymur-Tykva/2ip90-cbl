import java.awt.*;
import java.util.ArrayList;
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
        for (int col = 0; col < 11; col++) {
            for (int row = 0; row < 11; row++) {
                if (((row % 2 == 0) && (col % 2 == 0)) || (!(row % 2 == 0) && !(col % 2 == 0))) {
                    g2D.setColor(new Color(138, 233, 131));
                    g2D.fillRect(row * sqr, col * sqr, 55, 55);
                } else {
                    g2D.setColor(new Color(101, 196, 99));
                    g2D.fillRect(row * sqr, col * sqr, 55, 55);
                }

            }

        }

        //set a 2d array list for containing apples
        ArrayList<Point> appleLocations = new ArrayList<>();
        int sideApple = 45;
        int padding = 5;

        //set initial red apple
        appleLocations.add(new Point(7,5));

        //initialize the colors of the different apples
        Color red = new Color(255, 59, 59);
        Color black = new Color(64, 64, 64);
        Color yellow = new Color(236, 255, 54);

        //print out all of the apples from the array list
        for (Point point : appleLocations) {
            Color appleColor = red;
            g2D.setColor(appleColor);
            g2D.fillOval(point.x * sqr + padding, point.y * sqr + padding, sideApple, sideApple);
        }

    }
}