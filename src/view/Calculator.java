package view;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    public Calculator() {
        setSize(300, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(new Display(), BorderLayout.NORTH);
        add(new Keyboard(), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
