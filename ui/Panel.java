package ui;

import apples.Apple;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;
import logic.InputBuffer;
import logic.RetryHandler;
import logic.StateManager;

public class Panel extends JPanel {
    StateManager stateManager;
    InputBuffer inputBuffer;
    RetryHandler retryHandler;

    public Panel(StateManager stateManager) {
        this.stateManager = stateManager;
        this.inputBuffer = stateManager.getInputBuffer();
        this.retryHandler = stateManager.getRetryHandler();

        setPreferredSize(new Dimension(605, 605));

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                inputBuffer.handleEvent(e);
            }
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        // set the forloop for the checkpatter
        int sidePanel = 605;
        int sqr = 605 / 11;
        for (int col = 0; col < 11; col++) {
            for (int row = 0; row < 11; row++) {
                if (((row % 2 == 0) && (col % 2 == 0)) || (!(row % 2 == 0) && !(col % 2 == 0))) {
                    g2D.setColor(new Color(125, 189, 91));
                    g2D.fillRect(row * sqr, col * sqr, 55, 55);
                } else {
                    g2D.setColor(new Color(107, 166, 77));
                    g2D.fillRect(row * sqr, col * sqr, 55, 55);
                }

            }
        }

        // set a 2d array list for containing apples
        ArrayList<Apple> apples = new ArrayList<>(stateManager.getApples());
        int sideApple = 45;
        int paddingA = 8;

        // paint all of the apples from the array list
        for (Apple apple : apples) {
            Point point = apple.getPosition();
            Color appleColor = apple.getColor();

            g2D.drawImage(redAppleImage.getImage(), point.x * sqr + paddingA, point.y * sqr + 15, 45, 45, null);

            

        }

        // Initialize deque for the snake's body
        Deque<Point> snake = new LinkedList<>(stateManager.getSnake());
        int paddingS = 14;
        int sideSnake = 28;

        Color colorHead = new Color(36, 59, 93);
        Color colorBody = new Color(76, 100, 138);

        Point previousPoint = null;

        // paint body and head
        for (Point point : snake) {
            if (point == snake.getFirst()) {
                g2D.setColor(colorHead);
            } else {
                g2D.setColor(colorBody);
            }
            g2D.fillRect(point.x * sqr + paddingS, point.y * sqr + paddingS, sideSnake, sideSnake);
            if (previousPoint != null) {
                float midX = (((float) previousPoint.x + (float) point.x) / 2f) * sqr + paddingS;
                float midY = (((float) previousPoint.y + (float) point.y) / 2f) * sqr + paddingS;
                g2D.fillRect(Math.round(midX), Math.round(midY), sideSnake, sideSnake);
            }
            previousPoint = point;
        }
    }
}