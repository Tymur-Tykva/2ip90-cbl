package ui;

import java.awt.*;
import javax.swing.*;

/**
 * Button subclass which styles the pause button shown in the top panel.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class PauseButton extends JButton {
    int width = 20;
    int height = 20;

    /**
     * Constructs the button and sets its properties.
     *
     * @param text The text to display in the button. Used with this parameter set
     *             to empty.
     */
    public PauseButton(String text) {
        super(text);
        super.setSize(new Dimension(width, height));
        super.setBackground(new Color(200, 163, 117));

    }

    /**
     * Overrides the paintComponent method to style the button with the two vertical
     * rectangles.
     */
    @Override
    protected void paintComponent(Graphics g) {
        int widthButton = this.getWidth();
        int heightButton = this.getHeight();
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        g2D.setColor(new Color(101, 67, 33));
        g2D.fillRect(widthButton / 3, 5, 3, heightButton - 10);
        g2D.fillRect(2 * (widthButton / 3) - 3, 5, 3, heightButton - 10);
    }

}