package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Button subclass which styles the buttons used in the pause and game over
 * menus.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class MenuButton extends JButton {
    /**
     * Creates a new instance of MenuButton with some given text.
     * 
     * @param text The text to display in the button.
     */
    public MenuButton(String text) {
        super(text);
        // Intiliaze the customs for the buttons
        Font fontButton = new Font("Serif", Font.BOLD, 25);
        Color colorButton = new Color(200, 163, 117);
        Color colorBrown = new Color(101, 67, 33);
        LineBorder thickBorder = new LineBorder(new Color(101, 67, 33), 3, false);
        EmptyBorder paddingBorder = new EmptyBorder(7, 10, 7, 10);
        CompoundBorder compoundBorder = new CompoundBorder(thickBorder, paddingBorder);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setFont(fontButton);
        setForeground(colorBrown);
        setBackground(colorButton);
        setBorder(compoundBorder);
        setPreferredSize(new Dimension(180, 50));

    }

}