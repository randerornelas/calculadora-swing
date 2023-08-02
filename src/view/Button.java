package view;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text, Color color) {
        setText(text);
        setForeground(Color.WHITE);
        setBackground(color);
        setFont(new Font("courier", Font.PLAIN, 16));
        setBorder(BorderFactory.createLineBorder(new Color(26, 32, 49)));
    }
}
