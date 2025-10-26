package ui;

import apples.Apple;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import logic.InputBuffer;
import logic.StateManager;
import utils.Config;

/**
 * The panel on which the actual game is rendered. Every update, the panel
 * paints the board, apples, and snake.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class Panel extends JPanel {
    StateManager stateManager;
    InputBuffer inputBuffer;

    /**
     * Creates a new panel instance, and fetches the state manager (used to get the
     * position of the snake's body and the apples), and the input buffer (used to
     * handle user keyboard inputs).
     * 
     * @param stateManager The game's unique StateManager instance.
     */
    public Panel(StateManager stateManager) {
        this.stateManager = stateManager;
        this.inputBuffer = stateManager.getInputBuffer();

        setPreferredSize(new Dimension(605, 605));

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                inputBuffer.handleEvent(e);
            }
        });
    }

    /**
     * Implementation of the paint method, allowing the game to be rendered manually
     * on the panel. Handles drawing the board, apples, and snake all in one method.
     */
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        // Draw the checkerboard background.
        int sidePanel = 605;
        int sqr = sidePanel / Config.GRID_SIDE;
        for (int col = 0; col < Config.GRID_SIDE; col++) {
            for (int row = 0; row < Config.GRID_SIDE; row++) {
                if (((row % 2 == 0) && (col % 2 == 0)) || (!(row % 2 == 0) && !(col % 2 == 0))) {
                    g2D.setColor(new Color(125, 189, 91));
                    g2D.fillRect(row * sqr, col * sqr, 55, 55);
                } else {
                    g2D.setColor(new Color(107, 166, 77));
                    g2D.fillRect(row * sqr, col * sqr, 55, 55);
                }

            }
        }

        // Create a copy of the apples list to avoid ConcurrentModificationException.
        ArrayList<Apple> apples = new ArrayList<>(stateManager.getApples());

        int sideApple = 45;
        int paddingApple = 5;

        // Paint all of the apples from the array list.
        for (Apple apple : apples) {
            Point point = apple.getPosition();
            URL imageUrl = apple.getImageUrl();
            Image appleImage = new ImageIcon(imageUrl).getImage();

            g2D.drawImage(appleImage,
                    point.x * sqr + paddingApple,
                    point.y * sqr + paddingApple,
                    sideApple, sideApple,
                    null);
        }

        // Create a copy of the snake's points to avoid ConcurrentModificationException.
        Deque<Point> snake = new LinkedList<>(stateManager.getSnake());

        int sideSnake = 28;
        int paddingSnake = 14;

        Color colorHead = new Color(36, 59, 93);
        Color colorBody = new Color(76, 100, 138);

        Point previousPoint = null;

        // Paint the snake
        for (Point point : snake) {
            // Draw the main body part (the one in the actual cell).
            if (point == snake.getFirst()) {
                g2D.setColor(colorHead);
            } else {
                g2D.setColor(colorBody);
            }

            g2D.fillRect(
                    point.x * sqr + paddingSnake,
                    point.y * sqr + paddingSnake,
                    sideSnake, sideSnake);

            // If not the first part (head), draw the connecting body part (midpoint between
            // the current and previous parts).
            if (previousPoint != null) {
                float midX = (((float) previousPoint.x + (float) point.x) / 2f)
                        * sqr + paddingSnake;
                float midY = (((float) previousPoint.y + (float) point.y) / 2f)
                        * sqr + paddingSnake;
                g2D.fillRect(Math.round(midX), Math.round(midY), sideSnake, sideSnake);
            }

            // Update the previous point.
            previousPoint = point;
        }
    }
}