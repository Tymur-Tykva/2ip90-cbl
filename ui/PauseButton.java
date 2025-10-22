package ui;

import java.awt.*;
import javax.swing.*;

/*
     * Makes the design of the pause button
     */
public class PauseButton extends JButton {

    int width = 20;
    int height = 20;

    public PauseButton(String text) {
        super(text);
        super.setSize(new Dimension(width, height));
        super.setBackground(new Color(200, 163, 117));
        
        
    }

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