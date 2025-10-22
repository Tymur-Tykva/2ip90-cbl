package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PauseMenuButton extends JButton {

    public PauseMenuButton(String text) {
        super(text);
        // Intiliaze the customs for the buttons
        Font fontButton = new Font("Serif", Font.BOLD, 25);
        Color colorButton = new Color(200, 163, 117);
        Color colorTextButton = new Color(101, 67, 33);
        LineBorder thickBorder = new LineBorder(new Color(101, 67, 33), 3, false);
        EmptyBorder paddingBorder = new EmptyBorder(7, 10, 7, 10);
        CompoundBorder compoundBorder = new CompoundBorder(thickBorder, paddingBorder);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setFont(fontButton);
        setForeground(colorTextButton);
        setBackground(colorButton);
        setBorder(compoundBorder);
        setPreferredSize(new Dimension(180, 50));

    }

}